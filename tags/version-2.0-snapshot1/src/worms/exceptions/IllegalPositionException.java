package worms.exceptions;
import worms.model.Position;
import worms.model.Worm;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of exceptions signaling illegal positions for worms.
 *   Each illegal position exception involves the illegal position and the worm.
 * 
 * @version 1.0
 * @author Tom Gijselinck
 *
 */
public class IllegalPositionException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8263716032809099440L;


	/**
	 * Initialize this new illegal position exception with given position and
	 * given worm.
	 * 
	 * @param 	position
	 * 			The position for this new illegal position exception.
	 * @param 	worm
	 * 			The worm for this new illegal position exception.
	 * @post	The position of this new illegal position exception is equal to
	 * 			the given position.
	 * 		  |	new.getPosition() == position
	 * @post	The worm for this new illegal position exception is equal to the
	 * 			given worm.
	 * 		  |	new.getWorm() == worm
	 * @effect	This new illegal position exception is further initialized as a
	 * 			new runtime exception involving no diagnostic message and no
	 * 			cause.
	 * 		  |	super()
	 */
	public IllegalPositionException(Position position, Worm worm) {
		this.position = position;
		this.worm = worm;
	}
	
	
	/**
	 * Return the position of this illegal position exception.
	 */
	@Basic @Raw @Immutable
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Variable registering the position of this illegal position exception.
	 */
	private final Position position;
	
	
	/**
	 * Return the worm of this illegal position exception.
	 */
	@Basic @Raw @Immutable
	public Worm getWorm() {
		return worm;
	}
	
	/**
	 * Variable referencing the worm of this illegal position exception.
	 */
	private final Worm worm;

}
