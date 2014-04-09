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
	public void terminate() {}
	
	public boolean isTerminated() { return isTerminated;}
	
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
	
	
	
	
	/**
	 * Variable referencing the world where this projectile is in.
	 */
	private World world;
	
	/**
	 * Return the world where this projectile is active in.
	 */
	@Basic
	public World getWorld() { return world;}
	
	/**
	 * Check whether this projectile has a proper world attached to it.
	 * 	Check for consistency of mutual reference implied by bidirectional
	 * 	association.
	 */
	public boolean hasProperWorld() { return true;}
	
	/**
	 * Set the world where this projectile is active in to the given world.
	 * 
	 * @param 	world
	 * 			...
	 * @post	...
	 * 		  |	this.getWorld() == world
	 */
	public void setWorld(World world) {}
	
	
	
		
	public void jump() {}
	
	public double jumpTime() { return 0;}
	
	public Position jumpStep(double timeInterval) { return new Position(0, 0);}
	
}
