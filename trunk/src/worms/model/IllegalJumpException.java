package worms.model;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of exceptions signaling illegal jumps for worms.
 *   Each illegal jump exception involves the worm.
 * 
 * @version 1.0
 * @author Tom Gijselinck
 *
 */
public class IllegalJumpException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2813676675935540573L;


	/**
	 * Initialize this new illegal jump exception with given
	 * worm.
	 * 
	 * @param 	worm
	 * 			The worm for this new illegal jump exception.
	 * @post	The worm for this new illegal jump exception is equal to the
	 * 			given worm.
	 * 		  |	new.getWorm() == worm
	 * @effect	This new illegal jump exception is further initialized as a
	 * 			new runtime exception involving no diagnostic message and no
	 * 			cause.
	 * 		  |	super()
	 */
	public IllegalJumpException(Worm worm) {
		this.worm = worm;
	}

	/**
	 * Return the worm of this illegal jump exception.
	 */
	@Basic @Raw @Immutable
	public Worm getWorm() {
		return worm;
	}
	
	/**
	 * Variable referencing the worm of this illegal jump exception.
	 */
	private final Worm worm;

}
