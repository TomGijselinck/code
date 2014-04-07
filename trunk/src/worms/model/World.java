package worms.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * ...
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
	private int getNoHorizontalPixels() { return 1;}
	
	/**
	 * Return the number of vertical pixels of the passable map of this world.
	 * 
	 * @return	...
	 * 		  |	result ==
	 * 		  |		getPassableMap()[0].length
	 */
	private int getNoVerticalPixels() { return 1;}
	
	
	
	
	/**
	 * Checks whether the given location is passable. If this is not true, the
	 * given location is impassable.
	 * 
	 * @return	...
	 * 		  |	let
	 * 		  |		passableMap = getPassableMap()
	 * 		  |		&& x = (int) location.getX() / getWidth() * getNoHorizontalPixels()
	 * 		  |		&& y = (int) location.getY() / getHeight() * getNoVerticalPixels()
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
	
	
	
	
	public void startGame() {}
	
}
