package worms.model;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of exceptions signaling illegal steps for worms.
 *   Each illegal steps exception involves the illegal steps and the 
 *   worm.
 * 
 * @version 1.0
 * @author Tom Gijselinck
 *
 */
public class IllegalStepsException extends RuntimeException {

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
	public IllegalStepsException(double steps, Worm worm) {
		this.steps = steps;
		this.worm = worm;
	}
	
	
	/**
	 * Return the steps of this illegal steps exception.
	 */
	@Basic @Raw @Immutable
	public double getsteps() {
		return steps;
	}
	
	/**
	 * Variable registering the steps of this illegal steps exception.
	 */
	private final double steps;
	
	
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
