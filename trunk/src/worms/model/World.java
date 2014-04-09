package worms.model;

import java.util.Set;

import be.kuleuven.cs.som.annotate.*;

/**
 * ...
 * 	 
 * @invar	Each world has a proper projectile.
 * 		  |	//TODO formal specification check
 * 		  |	hasProperProjectile()
 * 
 * @author 	Tom Gijselinck
 * @version	1.0
 *
 */
public class World {
	
	//CONSTRUCTORS
	
	/**
	 * ...
	 * 
	 * @param 	width
	 * 			...
	 * @param 	height
	 * 			...
	 * @param 	passableMap
	 * 			...
	 * @post	...
	 * 		  |	new.getWidth() == width
	 * @post	...
	 * 		  |	new.getHeight() == height
	 * @post	...
	 * 		  |	new.getPassableMap() == passableMap
	 * @throws	IllegalArgumentException
	 * 		  	...
	 * 		  |	! isValidWidth(width)
	 * @throws	IllegalArgumentException
	 * 		  	...
	 * 		  |	! isValidHeight(height)
	 */
	public World(double width, double height, boolean[][] passableMap) {}
	
	
	
	
	//DESTRUCTOR
	/**
	 * Terminate this world.
	 * 
	 * @post	...
	 * 		  |	new.isTerminated()
	 * @post	This world no longer references a projectile as the projectile
	 * 		  	to which it is attached.
	 * 		  |	new.getProjectile() == null
	 * @post	If this world was not already terminated, the projectile to
	 * 			which this world was attached no longer has a world attached to
	 * 			it.
	 * 		  |	if (! this.isTerminated())
	 * 		  |		then (! (new.getProjectile()).hasWorld() )
	 */
	public void terminate() {}
	
	/**
	 * Check whether this world is terminated.
	 */
	public boolean isTerminated() { return isTerminated;}
	
	/**
	 * Variable registering whether this world is terminated.
	 */
	private boolean isTerminated;
	
	
	
	
	/**
	 * Return the width of this world.
	 */
	@Basic
	public double getWidth() { return 0;}
	
	/**
	 * Checks whether the given width is a valid width for any world.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		width <= World.getWidthUpperBound()
	 * 		  |		&& width > 0
	 */
	public boolean isValidWidth() { return true;}
	
	
	
	
	/**
	 * Return the width upper bound for any world.
	 */
	@Basic
	public static double getWidthUpperBound() { return 0;}
	
	
	
	
	/**
	 * Return the height of this world.
	 */
	@Basic
	public double getHeight() { return 0;}
	
	/**
	 * Checks whether the given height is a valid height for any world.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		height <= World.getHeightUpperBound()
	 * 		  |		&& height > 0
	 */
	public boolean isValidHeight() { return true;}
	
	
	
	
	/**
	 * Return the height upper bound for any world.
	 */
	@Basic
	public static double getheighthUpperBound() { return 0;}
	
	
	
	
	/**
	 * Return the passable map of this world.
	 */
	public boolean[][] getPassableMap() { return new boolean[1][1];}
	
	/**
	 * Return the number of horizontal pixels of the passable map of this world.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		getPassableMap().length
	 */
	public int getNoHorizontalPixels() { return 1;}
	
	/**
	 * Return the number of vertical pixels of the passable map of this world.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		getPassableMap()[0].length
	 */
	public int getNoVerticalPixels() { return 1;}
	
	
	
	
	/**
	 * Return the horizontal scale of this world in map pixels per worm-meter.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		getNoHorizontalPixels() / getWidth()
	 */
	public double getHorizontalScale() { return 0;}
	
	/**
	 * Return the vertical scale of this world in map pixels per worm-meter.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		getNoVerticalPixels() / getHeight()
	 */
	public double getVerticalScale() { return 0;}
	
	
	
	
	/**
	 * Checks whether the given location is passable. If this is not true, the
	 * given location is impassable.
	 * 
	 * @return	...
	 * 		  |	let
	 * 		  |		passableMap = getPassableMap()
	 * 		  |		&& x = (int) location.getX() * getHorizontalScale()
	 * 		  |		&& y = (int) location.getY() * getVerticalScale()
	 * 		  |	in
	 * 		  |		passableMap[x][y] == true
	 */
	public boolean isPassable(Position location) { return true;}
	
	/**
	 * Checks whether the given location is passable and adjacent to an
	 * impassable location.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		isPassable(location)
	 * 		  |		&& isPassable(location.translate(
	 * 		  |				getWidth() / getNoHorizontalPixels(),
	 * 		  |			 	getHeight() / getNoVerticalPixels() ) )
	 */
	public boolean isAdjacent(Position location) { return true;}
	
	
	
	
	//ASSOCIATIONS
	/**
	 * Return the live projectile of this world. 
	 */
	@Basic
	public Projectile getProjectile() { return projectile;}
	
	/**
	 * Checks whether this world can have the given projectile as its 
	 * projectile.
	 * 
	 * @param	projectile
	 * 			...
	 * @return	...
	 * 		  |	if (isTerminated())
	 * 		  |		then result == (projectile == null)
	 * 		  |	else result ==
	 * 		  |		( ( (projectile != null)
	 * 		  |	     && (! projectile.isTerminated()) )
	 * 		  |    || (projectile == null) )
	 */
	public boolean canHaveAsProjectile(Projectile projectile) { return true;}
	
	/**
	 * Check whether this world has a proper projectile attached to it.
	 * 	Check for consistency of mutual reference implied by bidirectional
	 * 	association.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		( canHaveAsProjectile(getProjectile())
	 * 		  |	   && ( (getProjectile() == null)
	 * 		  |		 ||	(getProjectile().getWorld() == this) ) )
	 */
	public boolean hasProperProjectile() { return true;}
	
	/**
	 * Set the projectile which is active in this world to the given projectile.
	 * 
	 * @param 	projectile
	 * 			...
	 * @post	...
	 * 		  |	new.getProjectile() == projectile
	 */
	public void setProjectile(Projectile projectile) {}
	
	/**
	 * Variable referencing the live projectile in this world. 
	 */
	private Projectile projectile;
	
	
	
	
	/**
	 * Check whether this world has the given worm as one of the worms attached
	 * to it.
	 * 
	 * @param 	worm
	 * 			...
	 */
	@Basic
	public boolean hasAsWorm(Worm worm) { return true;}
	
	/**
	 * Check whether this world can have the given worm as one of its worms.
	 * 
	 * @param 	worm
	 * 			...
	 * @return	...
	 * 		  |	if (worm == null)
	 * 		  |		then result == false
	 * 			Otherwise, true if and only if this world is not yet terminated
	 * 			or the given worm is also terminated.
	 * 		  |	else result ==
	 * 		  |		( (! this.isTerminated())
	 * 		  |	   || worm.isTerminated() )
	 */
	public boolean canHaveAsWorm(Worm worm) { return true;}
	
	/**
	 * Check whether this world has proper worms attached to it.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		for each worm in Worm:
	 * 		  |			( if (this.hasAsWorm(worm))
	 * 		  |				then canHaveAsWorm(worm)
	 * 		  |				  && (worm.getWorld() == this) )
	 */
	public boolean hasProperWorms() { return true;}
	
	/**
	 * Return a set collecting all worms associated with this world.
	 * 
	 * @return	...
	 * 		  |	result != null
	 * @return	...
	 * 		  |	for each worm in Worm:
	 * 		  |		result.contains(worm) ==
	 * 		  |		this.hasAsWorm(worm)
	 */
	public Set<Worm> getAllWorms() {return null;}
	
	/**
	 * Add the given worm to the set of worms attached to this world.
	 * 
	 * @param 	worm
	 * 			...
	 * @post	...
	 * 		  |	new.hasAsWorm(worm)
	 * @post	...
	 * 		  |	(new worm).getWorld() == this
	 * @throws	IllegalArgumentException
	 * 			...
	 * 		  |	! canHaveAsWorm(worm)
	 * @throws	IllegalArgumentException
	 * 			The given worm is already attached to some world.
	 * 		  |	 ( (worm != null)
	 * 		  | && (worm.getWorld() != null) )
	 */
	public void addAsWorm(Worm worm) {}
	
	/**
	 * Remove the givenworm from the set of worms attached to this world.
	 * 
	 * @param 	worm
	 * 			...
	 * @post	...
	 * 		  |	! new.hasAsWorm(worm)
	 * @post	...
	 * 		  |	if (this.hasAsWorm(worm))
	 * 		  |		then ((new worm).getWorld() == null)
	 */
	public void removeAsWorm(Worm worm) {}
	
	
	
	
	public void startGame() {}
	
}
