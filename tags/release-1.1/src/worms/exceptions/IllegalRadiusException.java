package worms.exceptions;
import worms.model.Worm;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of exceptions signaling illegal radii for worms.
 *   Each illegal radius exception involves the illegal radius and the worm.
 * 
 * @version 1.0
 * @author Tom Gijselinck
 *
 */
public class IllegalRadiusException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2848642114310028649L;


	/**
	 * Initialize this new illegal radius exception with given radius and given
	 * worm.
	 * 
	 * @param 	radius
	 * 			The radius for this new illegal radius exception.
	 * @param 	worm
	 * 			The worm for this new illegal radius exception.
	 * @post	The radius of this new illegal radius exception is equal to the
	 * 			given radius.
	 * 		  |	new.getRadius() == radius
	 * @post	The worm for this new illegal radius exception is equal to the
	 * 			given worm.
	 * 		  |	new.getWorm() == worm
	 * @effect	This new illegal radius exception is further initialized as a
	 * 			new runtime exception involving no diagnostic message and no
	 * 			cause.
	 * 		  |	super()
	 */
	public IllegalRadiusException(double radius, Worm worm) {
		this.radius = radius;
		this.worm = worm;
	}
	
	
	/**
	 * Return the radius of this illegal radius exception.
	 */
	@Basic @Raw @Immutable
	public double getRadius() {
		return radius;
	}
	
	/**
	 * Variable registering the radius of this illegal radius exception.
	 */
	private final double radius;
	
	
	/**
	 * Return the worm of this illegal radius exception.
	 */
	@Basic @Raw @Immutable
	public Worm getWorm() {
		return worm;
	}
	
	/**
	 * Variable referencing the worm of this illegal radius exception.
	 */
	private final Worm worm;

}
