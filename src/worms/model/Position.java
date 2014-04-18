package worms.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of positions involving an X coordinate and a Y coordinate.
 * 
 * @invar	The X coordinate of each position must be a valid coordinate.
 * 		  |	isValidCoordinate(getX())
 * @invar	The Y coordinate of each position must be a valid coordinate.
 * 		  |	isValidCoordinate(getY())
 * 
 * @version	1.1
 * @author 	Tom Gijselinck
 *
 */
@Value
public class Position {

	//CONTSTRUCTORS
	/**
	 * Initialize this new position with given X coordinate and given Y
	 * coordinate.
	 * 
	 * @param 	x
	 * 			The X coordinate for this new position.
	 * @param 	y
	 * 			The Y coordinate for this new position.
	 * @post	The new X coordinate of this new position is equal to the given
	 * 			X coordinate.
	 * 		  |	new.getX() == x
	 * @post	The new Y coordinate of this new position is equal to the given
	 * 			Y coordinate.
	 * 		  |	new.getY() == y
	 * @throws	IllegalCoordinateException(x, this)
	 * 			The given coordinate is not a valid coordinate for any position.
	 * 		  |	! isValidCoordinate(x)
	 * @throws	IllegalCoordinateException(y, this)
	 * 			The given coordinate is not a valid coordinate for any position.
	 * 		  |	! isValidCoordinate(y)
	 */
	public Position(double x, double y) {
		if (! isValidCoordinate(x)) {
			throw new IllegalArgumentException();
		}
		if (! isValidCoordinate(y)) {
			throw new IllegalArgumentException();
		}
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Variable referencing a position at the origin (0,0).
	 * 
	 * @return	The position ORIGIN is equal to a position initialized with x 0,
	 * 			and with y 0.
	 * 		  |	ORIGIN.equals(new Position(0,0))
	 */
	public final static Position ORIGIN = new Position(0, 0);
	
	//X COORDINATE
	/**
	 * Return the X coordinate of this position.
	 */
	@Basic
	public double getX() {
		return this.x;
	}
	
	/**
	 * Variable registering the X coordinate of this position.
	 */
	private double x;
	
	//Y COORDINATE
	/**
	 * Return the Y coordinate of this position.
	 */
	@Basic
	public double getY() {
		return this.y;
	}
	
	/**
	 * Variable registering the Y coordinate of a position.
	 */
	private double y;
	
	/**
	 * Check whether the given coordinate is a valid coordinate for all 
	 * positions.
	 * 
	 * @param 	coordinate
	 * 			The coordinate to check.
	 * @return	True if and only if the coordinate is a valid number.
	 * 		  |	result ==
	 * 		  |	 Double.isNaN(coordinate) == false
	 */
	public static boolean isValidCoordinate(double coordinate) {
		return (Double.isNaN(coordinate) == false);
	}
	
	/**
	 * Compute the position as the X coordinate being the X coordinate of this 
	 * position incremented by dx and the Y coordinate being the Y coordinate 
	 * of this position incremented by dy.
	 * 
	 * @param 	dx
	 * 			The distance to add to the X coordinate of this position.
	 * @param 	dy
	 * 			The distance to add to the Y coordinate of this position.
	 * @return	The resulting position is equal to a position with X coordinate 
	 * 		 	as the X coordinate of this position incremented by dx and Y 
	 * 			coordinate as the Y coordinate of this position incremented by
	 * 			dy.
	 * 		  |	result.equals(
	 * 		  |		new Position(this.getX() + dx, this.getY() + dy))
	 * @throws	IllegalArgumentException
	 * 			The resulting position has illegal coordinates.
	 * 		  |	 ( (! isValidCoordinate(dx))
	 * 		  |	|| (! isValidCoordinate(dy)) )
	 */
	public Position translate(double dx, double dy) 
			throws IllegalArgumentException {
		if ( (! isValidCoordinate(dx))
	  	  || (! isValidCoordinate(dy)) ) {
			throw new IllegalArgumentException();
		}
		return new Position(getX() + dx, getY() + dy);
	}
	
	/**
	 * 
	 * @param otherPosition
	 * @return
	 */
	//TODO add documentation
	public double getDistanceFrom(Position otherPosition) {
		double dx = otherPosition.getX() - getX();
		double dy = otherPosition.getY() - getY();
		return (Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2)));
	}
	
	/**
	 * Check whether this position is equal to the given object.
	 * 
	 * @return	True if and only if the given object is effective, if this 
	 * 			position and the given object belong to the same class and if
	 * 			this position and the other object interpreted as a position
	 * 			have equal X coordinates and equal Y coordinated.
	 * 		  |	result ==
	 * 		  |		( (other != null)
	 * 		  |	   && (this.getClass() == other.getClass())
	 * 		  |    && (this.getX() == other.getX())
	 * 		  |	   && (this.getY() == other.getY()) )
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null) 
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Position otherPosition = (Position) other;
		return
				( this.getX() == otherPosition.getX())
			   && (this.getY() == otherPosition.getY());
	}
	
	/**
	 * Return the hash code of this position.
	 */
	@Override
	public int hashCode() {
		int hash = 7;
		hash = (int) (31 * hash + x);
		hash = (int) (31 * hash + y);
		return hash;
	}
	
	@Override
	public String toString() {
		return ("(" + getX() + ", " + getY() + ")");
	}
	
}
