package worms.exceptions;
import worms.model.Worm;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of exceptions signaling illegal steps for worms.
 *   Each illegal steps exception involves the direction in which the illegal
 *   step is to be taken and the worm.
 * 
 * @version 1.0
 * @author Tom Gijselinck
 *
 */
public class IllegalStepException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4512416122830071005L;


	/**
	 * Initialize this new illegal steps exception with given steps 
	 * and given worm.
	 * 
	 * @param 	steps
	 * 			The steps for this new illegal steps exception.
	 * @param 	worm
	 * 			The worm for this new illegal steps exception.
	 * @post	The steps of this new illegal steps exception is 
	 * 			equal to the given steps.
	 * 		  |	new.getSteps() == steps
	 * @post	The worm for this new illegal steps exception is equal to 
	 * 			the	given worm.
	 * 		  |	new.getWorm() == worm
	 * @effect	This new illegal steps exception is further initialized as
	 * 			a new runtime exception involving no diagnostic message and no
	 * 			cause.
	 * 		  |	super()
	 */
	public IllegalStepException(double direction, Worm worm) {
		this.direction = direction;
		this.worm = worm;
	}
	
	
	/**
	 * Return the direction of this illegal steps exception.
	 */
	@Basic @Raw @Immutable
	public double getDirection() {
		return direction;
	}
	
	/**
	 * Variable registering the direction of this illegal steps exception.
	 */
	private final double direction;
	
	
	/**
	 * Return the worm of this illegal steps exception.
	 */
	@Basic @Raw @Immutable
	public Worm getWorm() {
		return worm;
	}
	
	/**
	 * Variable referencing the worm of this illegal steps exception.
	 */
	private final Worm worm;

}
