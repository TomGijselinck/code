package worms.model;

import be.kuleuven.cs.som.annotate.*;


/**
 * ...
 * 
 * @invar	Each projectile has a proper world attached to it.
 * 		  |	hasProperWorld()
 * @invar	...
 * 		  |	hasProperPosition()
 * 	 
 * @author 	Tom Gijselinck
 * @version	1.0
 *
 */
public class Projectile extends GameObject {
	
	//CONSTRUCTORS
	/**
	 * ...
	 * 
	 * @param 	mass
	 * 		  	...
	 * @param 	position
	 * 			...
	 * @effect	...
	 * 		  |	super(position, direction, radius, mass)
	 * @post	...
	 * 		  |	new.getDamage() == damage
	 * @post	...
	 * 		  |	new.getRadius() ==
	 * 		  |		Math.pow((0.75 * getMass()) / (Math.PI * getDensity()), 1/3)
	 */
	//TODO documentation aanvullen & checkers aanmaken voor argumenten
	public Projectile(Position position, double direction, double mass, 
			int damage, double launchForce) {
		super(position, direction, 1, mass);
		setRadius();
		this.damage = damage;
		this.launcheForce = launchForce;
	}
	
	
	
	
	//DESTRUCTOR
	/**
	 * Terminate this projectile.
	 * 
	 * @post	...
	 * 		  |	new.isTerminated()
	 * @post	This projectile no longer references a world as the world
	 * 		  	to which it is attached.
	 * 		  |	new.getWorld() == null
	 * @post	If this projectile was not already terminated, the world to
	 * 			which this projectile was attached no longer has a projectile
	 * 			attached to it.
	 * 		  |	if (! this.isTerminated())
	 * 		  |		then (! (new.getWorld()).hasProjectile() )
	 */
	@Override
	public void terminate() {
		if (! isTerminated()) {
			getWorld().setProjectile(null);
		}
		setWorld(null);
		super.terminate();
	}
	
	
	
	
	@Basic 
	@Immutable
	public int getDamage() {
		return damage;
	}
	
	private int damage;
	
	
	
	/**
	 * ...
	 * 
	 * @post	...
	 * 		  |	new.getRadius() ==
	 * 		  |		Math.pow((0.75 * getMass()) / (Math.PI * getDensity()), 1/3)
	 */
	public void setRadius() {
		double radius = Math.pow((0.75 * getMass()) / (Math.PI * getDensity()),
				1/3.0);
		setRadius(radius);
	}
	
	@Basic
	@Immutable
	public double getLaunchForce() {
		return launcheForce;
	}
	
	private double launcheForce;
	

	
	
	
	
	//ASSOCIATIONS
	/**
	 * Check whether this projectile can be attached to the given world.
	 * 
	 * @param 	world
	 * 			...
	 * @return	...
	 * 		  |	result ==
	 * 		  |	  ( (world == null)
	 * 		  |  || world.canHaveAsProjectile(this) )
	 */
	@Override
	public boolean canHaveAsWorld(World world) {
		return
			 ( (world == null) || world.canHaveAsProjectile(this));
	}
	
	/**
	 * Check whether this projectile has a proper world attached to it.
	 * 	Check for consistency of mutual reference implied by bidirectional
	 * 	association.
	 * 
	 * @return	...
	 * 		  |	result == 
	 * 		  |		( (getWorld() == null)
	 * 		  |	   || (getWorld().getProjectile() == this) )
	 */
	@Override
	public boolean hasProperWorld() {
		return 
			( (getWorld() == null)
		   || (getWorld().getProjectile() == this));
	}
	
	
	
	
	@Basic
	public double getDensity() {
		return density;
	}
	
	private static final double density = 7800; 
	
	
	
	
	//JUMP
	/**
	 * ...
	 * 
	 * @return	...
	 * 		  |	result == 
	 * 		  |		getWorld().isPassableForObject(getPosition(), getRadius())
	 */
	@Override
	public boolean canJump() {
		return getWorld().isPassableForObject(getPosition(), getRadius());
	}
	
	/**
	 * Returns the time in seconds this worm needs for jumping.
	 * 
	 * @param	dt
	 * 			The time step in which a worm will not completely move through
	 * 			a piece of impassable terrain.
	 * @return	If there exists a position in the jump trajectory of this worm 
	 * 			inside the world of this worm that is adjacent or creates a 
	 * 			partial overlap with a worm in the world of this projectile,  
	 * 			the resulting jump time is equal to the time until this worms 
	 * 			reaches that position.
	 * 		  |	let
	 * 		  |		jumpPosition = jumpStep(time, jumpSpeed())
	 * 		  |		R = getRadius()
	 * 		  |	in
	 * 		  |		if for some time in double:
	 * 		  |			if ( ( getWorld().isImpassableForObject(jumpPosition, R)
	 * 		  |			    || getWorld().overlaps(jumpPosition, R) )
	 * 		  |			  && getWorld().isInsideWorldBorders(jumpPosition) 
	 * 		  |			  && (for each t in 0..time where t < time:
	 *   	  |					getWorld().isPassable(jumpStep(t)
	 *   	  |				 && (! getWorld().isAdjacent(jumpPosition, R))	
	 *   	  |				 && (! getWorld().overlaps()))) )
	 * 		  |				then result == time
	 * 			Otherwise, if this worm leaves its game world, the resulting 
	 * 			jump time is equal to positive infinity (i.e. an infinite jump
	 * 			in an infinite and undefined space).
	 * 		  |	else if for some time in double:
	 * 		  |		if (! getWorld().isInsideWorldBorders(jumpPosition))
	 * 		  |			then result == Double.POSITIVE_INFINITY
	 */
	@Override
	public double jumpTime(double dt) {
		double jumpTime = 0;
		boolean jumping = true;
		boolean insideWorldBorders = true;
		Position jumpPosition = getPosition().translate(0, 0);
		World world = getWorld();
		double radius = getRadius();
		double jumpSpeed = jumpSpeed(getLaunchForce());
		
		while (jumping && insideWorldBorders) {
			jumpTime += dt;
			jumpPosition = jumpStep(jumpTime, jumpSpeed);
			if (world.isImpassableForObject(jumpPosition, radius)
			  || world.overlaps(jumpPosition, radius)) {
				jumping = false;
			}
			if (! world.isInsideWorldBorders(jumpPosition)) {
				insideWorldBorders = false;
			}
		}
		
		if (! insideWorldBorders) {
			jumpTime = Double.POSITIVE_INFINITY;
		}
		
		return jumpTime;
	}
	
	/**
	 * Returns the initial velocity this projectile has in m/s when jumping.
	 * 
	 * @return	The resulting speed of this game object is equal to the speed as
	 * 			a result of the given force in an interval of 
	 * 			0.5 seconds.
	 * 		  |	let 
	 * 		  |		g = GameObject.getGravityOfEarth()
	 * 		  | in
	 * 		  |		result ==
	 *   	  |			force / this.getMass() * 0.5
	 */
	@Override
	public double jumpSpeed(double force) {
		return force / getMass() * 0.5;
	}
	
}
