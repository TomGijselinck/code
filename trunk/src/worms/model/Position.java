package worms.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of positions involving an X coordinate and a Y coordinate expressed
 * in metres.
 * 
 * @version	1.0
 * @author 	Tom Gijselinck
 *
 */
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
		setX(x);
		setY(y);
	}
	
	/**
	 * Initialize this new position with the origin (0,0) as its position.
	 * 
	 * @effect	This new position is initialized with zero as its X coordinate
	 * 			and zero as its Y coordinate.
	 * 		  |	this(0,0)
	 */
	public Position() {}
	
	//X COORDINATE
	/**
	 * Return the X coordinate of this position.
	 */
	@Basic
	public double getX() {
		return this.x;
	}
	
	/**
	 * Set the X coordinate of this position to the given coordinate.
	 * 
	 * @param	x
	 * 			The new X coordinate for this position.
	 * @post	The new X coordinate of this position is equal to the given
	 * 			coordinate.
	 * 		  |	new.getX() == x
	 * @throws	IllegalCoordinateException(x, this)
	 * 			The given coordinate is not a valid coordinate for any position.
	 * 		  | ! isValidCoordinate(x)
	 */
	public void setX(double x) {
		if (! isValidCoordinate(x))
			throw new IllegalCoordinateException(x, this);
		this.x = x;
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
	 * Set the Y coordinate of this position to the given coordinate.
	 * 
	 * @param	y
	 * 			The new Y coordinate for this position.
	 * @post	The new Y coordinate of this position is equal to the given
	 * 			coordinate.
	 * 		  |	new.getY() == y
	 * @throws	IllegalCoordinateException(y, this)
	 * 			The given coordinate is not a valid coordinate for any position.
	 * 		  | ! isValidCoordinate(y)
	 */
	public void setY(double y) {
		if (! isValidCoordinate(y))
			throw new IllegalCoordinateException(y, this);
		this.y = y;
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
	 * Translate the X coordinate of this position by dx and the Y coordinate 
	 * of this position by dy.
	 * 
	 * @param 	dx
	 * 			The distance to move this position along the X axis.
	 * @param 	dy
	 * 			The distance to move this position along the Y axis.
	 * @post	The new position of this position is equal to the position 
	 * 			(x+dx, y+dy).
	 * 		  |	new.getX() = this.getX() + dx
	 * 		  |	new.getY() = this.getY() + dy
	 * @throws	IllegalCoordinateException(x+dx, this)
	 * 			The new X coordinate x+dx for this position is not a valid 
	 * 			coordinate for any position.
	 * 		  |	! isValidCoordinate(x+dx)
	 * @throws	IllegalCoordinateException(y+dy, this)
	 * 			The new Y coordinate y+dy for this position is not a valid 
	 * 			coordinate for any position.
	 * 		  |	! isValidCoordinate(y+dy)
	 */
	public void translate(double dx, double dy) {
		if (! isValidCoordinate(x+dx))
			throw new IllegalCoordinateException(x+dx, this);
		if (! isValidCoordinate(y+dy))
			throw new IllegalCoordinateException(y+dy, this);
		setX(getX() + dx);
		setY(getY() + dy);
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
}
