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
public class Projectile {
	
	//CONSTRUCTORS
	/**
	 * ...
	 * 
	 * @param 	mass
	 * 		  	...
	 * @post	...
	 * 		  |	new.getMass() == mass
	 * @post	...
	 * 		  |	! new.isTerminated()
	 */
	public Projectile(Position position, double direction, int mass,
			int radius, int damage) {
		this.position = position;
		this.direction = direction;
		this.mass = mass;
		this.radius = radius;
		this.damage = damage;
		this.isTerminated = false;
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
	public void terminate() {
		if (! isTerminated()) {
			getWorld().setProjectile(null);
		}
		setWorld(null);
		isTerminated = true;
	}
	
	/**
	 * Check whether this projectile is terminated.
	 */
	@Basic
	public boolean isTerminated() {
		return isTerminated;
	}
	
	/**
	 * Variable registering whether this projectile is terminated.
	 */
	private boolean isTerminated;
	
	
	
	
	/**
	 * Return the orientation of this projectile.
	 */
	@Basic
	public double getDirection() {
		return direction;
	}
	
	public void setDirection(double direction) {
		this.direction = direction;
	}
	
	private double direction;
	
	
	
	
	/**
	 * Return the mass of this projectile.
	 */
	@Basic 
	@Immutable
	public int getMass() {
		return mass;
	}
	
	private int mass;
	
	
	
	
	/**
	 * Return the radius of this projectile.
	 */
	@Basic 
	@Immutable
	public double getRadius() {
		return radius;
	}
	
	private double radius;
	
	
	
	
	@Basic 
	@Immutable
	public int getDamage() {
		return damage;
	}
	
	private int damage;
	
	
	
	
	//ASSOCIATIONS	
	/**
	 * Return the world where this projectile is active in.
	 */
	@Basic
	public World getWorld() {
		return world;
	}
	
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
	public boolean canHaveAsWorld(World world) {
		return
			 ( (world == null)
			|| world.canHaveAsProjectile(this)	);
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
	public boolean hasProperWorld() { return true;}
	
	/**
	 * Set the world where this projectile is active in to the given world.
	 * 
	 * @param 	world
	 * 			...
	 * @post	...
	 * 		  |	new.getWorld() == world
	 */
	public void setWorld(World world) {
		this.world = world;
	}
	
	/**
	 * Variable referencing the world where this projectile is in.
	 */
	private World world;
	
	/**
	 * Return the position of this projectile.
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Checks whether this projectile can have the given position as its 
	 * position.
	 * 
	 * @param	position
	 * 			The position to check.
	 * @return	True if the given position is not effective if this projectile  
	 * 			is terminated.
	 * 		  |	if (isTerminated())
	 * 		  |		then result == (position == null)
	 * 			Otherwise true if and only if the given position is effective.
	 * 		  |	else result == (position != null) 
	 */
	public boolean canHaveAsPosition(Position position) { return true;}
	
	/**
	 * Check whether this projectile has a proper position.
	 * 
	 * @return	True if and only if this projectile can have its position as its
	 * 			position.
	 * 		  |	result == canHaveAsPosition(getPosition()
	 */
	public boolean hasProperPosition() { return true;}
	
	/**
	 * Set the position of this projectile to the given position.
	 * 
	 * @param 	position
	 * 			The position to attach this projectile to.
	 * @post	This projectile references the given position as its position.
	 * 		  |	new.getPosition() == position
	 */
	public void setPosition(Position position) {}
	
	/**
	 * Variable referencing the position to which this projectile is attached.
	 */
	private Position position;
	
	
	
		
	public void jump() {}
	
	public double jumpTime() { return 0;}
	
	public Position jumpStep(double timeInterval) { return new Position(0, 0);}
	
}
