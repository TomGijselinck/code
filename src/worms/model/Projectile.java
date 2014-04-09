package worms.model;

import be.kuleuven.cs.som.annotate.*;


/**
 * ...
 * 
 * @invar	Each projectile has a proper world attached to it.
 * 		  |	hasProperWorld()
 * 	 
 * @author 	Tom Gijselinck
 * @version	1.0
 *
 */
public class Projectile {
	
	//CONSTRUCTORS
	
	public Projectile(double mass) {}
	
	
	
	
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
	public void terminate() {}
	
	/**
	 * Check whether this projectile is terminated.
	 */
	public boolean isTerminated() { return isTerminated;}
	
	/**
	 * Variable registering whether this projectile is terminated.
	 */
	private boolean isTerminated;
	
	
	
	
	/**
	 * Return the position of this projectile.
	 */
	@Basic
	public Position getPosition() { return new Position(0, 0);}
	
	
	
	
	/**
	 * Return the orientation of this projectile.
	 */
	@Basic
	public double getDirection() { return 0;}
	
	
	
	
	/**
	 * Return the mass of this projectile.
	 */
	@Basic
	public double getMass() { return 0;}
	
	
	
	
	/**
	 * Return the radius of this projectile.
	 */
	@Basic
	public double getRadius() { return 0;}
	
	
	
	
	//ASSOCIATIONS	
	/**
	 * Return the world where this projectile is active in.
	 */
	@Basic
	public World getWorld() { return world;}
	
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
	public void setWorld(World world) {}
	
	/**
	 * Variable referencing the world where this projectile is in.
	 */
	private World world;
	
	
	
		
	public void jump() {}
	
	public double jumpTime() { return 0;}
	
	public Position jumpStep(double timeInterval) { return new Position(0, 0);}
	
}
