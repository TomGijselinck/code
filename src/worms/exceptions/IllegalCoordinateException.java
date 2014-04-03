package worms.exceptions;
import worms.model.Position;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of exceptions signaling illegal coordinates for positions.
 *   Each illegal coordinate exception involves the illegal coordinate and the 
 *   position.
 * 
 * @version 1.0
 * @author Tom Gijselinck
 *
 */
public class IllegalCoordinateException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8065459880740505311L;


	/**
	 * Initialize this new illegal coordinate exception with given coordinate 
	 * and given position.
	 * 
	 * @param 	coordinate
	 * 			The coordinate for this new illegal coordinate exception.
	 * @param 	position
	 * 			The position for this new illegal coordinate exception.
	 * @post	The coordinate of this new illegal coordinate exception is 
	 * 			equal to the given coordinate.
	 * 		  |	new.getCoordinate() == coordinate
	 * @post	The position for this new illegal coordinate exception is equal to 
	 * 			the	given position.
	 * 		  |	new.getPosition() == position
	 * @effect	This new illegal coordinate exception is further initialized as
	 * 			a new runtime exception involving no diagnostic message and no
	 * 			cause.
	 * 		  |	super()
	 */
	public IllegalCoordinateException(double coordinate, Position position) {
		this.coordinate = coordinate;
		this.position = position;
	}
	
	
	/**
	 * Return the coordinate of this illegal coordinate exception.
	 */
	@Basic @Raw @Immutable
	public double getCoordinate() {
		return coordinate;
	}
	
	/**
	 * Variable registering the coordinate of this illegal coordinate exception.
	 */
	private final double coordinate;
	
	
	/**
	 * Return the position of this illegal coordinate exception.
	 */
	@Basic @Raw @Immutable
	public Position getposition() {
		return position;
	}
	
	/**
	 * Variable referencing the position of this illegal coordinate exception.
	 */
	private final Position position;

}
