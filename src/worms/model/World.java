package worms.model;


/**
 * @author 	Tom Gijselinck
 * @version	1.0
 *
 */
public class World {
	
	//CONSTRUCTORS
	public World(double width, double height, boolean[][] passableMap) {}
	
	public double getWidth() { return 0;}
	private void setWidth() {}
	
	public static double getWidthUpperBound() { return 0;}
	
	public double getHeight() { return 0;}
	private void setHeight() {}
	
	public static double getheighthUpperBound() { return 0;}
	
	public boolean isPassableTerrain() { return true;}
	public boolean isAdjacentToPassableTerrain() { return true;}
	
	public void startGame() {}
	
}
