package worms.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * ...
 * 
 * @invar	...
 * 		  |	isValidWidth(getWidth())
 * @invar	...
 * 		  |	isValidHeight(getHeight())
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
	 */
	public boolean isValidWidth() { return true;}
	
	/**
	 * Set the width of this world to the given width.
	 * 
	 * @param	width
	 * 			...
	 * @throws	IllegalArgumentException
	 * 			...
	 * 		  |	! isValidWidth(width)
	 */
	private void setWidth(double width) {}
	
	
	
	
	/**
	 * Return the width upper bound for any world.
	 */
	@Basic
	public static double getWidthUpperBound() { return 0;}
	
	
	
	
	/**
	 * Return the height of this worm.
	 */
	@Basic
	public double getHeight() { return 0;}
	
	/**
	 * Checks whether the given height is a valid height for any world.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		height <= World.getHeightUpperBound()
	 */
	public boolean isValidHeight() { return true;}
	
	/**
	 * Set the height of this world to the given height.
	 * 
	 * @param	height
	 * 			...
	 * @throws	IllegalArgumentException
	 * 			...
	 * 		  |	! isValidHeight(height)
	 */
	private void setHeight() {}
	
	
	
	
	/**
	 * Return the height upper bound for any world.
	 */
	@Basic
	public static double getheighthUpperBound() { return 0;}
	
	
	
	
	/**
	 * Return the passable map of this world.
	 */
	private boolean[][] getPassableMap() { return new boolean[1][1];}
	
	
	
	
	/**
	 * Checks whether the given location is passable.
	 * 
	 * @return	...
	 * 		  |	let
	 * 		  |		passableMap = getPassableMap()
	 * 		  |		&& x = location.getX()
	 * 		  |		&& y = location.getY()
	 * 		  |	in
	 * 		  |		passableMap[x][y] == true
	 */
	public boolean isPassableTerrain(Position location) { return true;}
	
	/**
	 * Checks whether the given location is passable and adjacent to an
	 * impassable location.
	 * 
	 * @return	...
	 * 		  |	let 
	 */
	public boolean isAdjacent() { return true;}
	
	
	
	
	public void startGame() {}
	
}
