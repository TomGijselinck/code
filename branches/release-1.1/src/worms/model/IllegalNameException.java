package worms.model;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of exceptions signaling illegal names for worms.
 *   Each illegal name exception involves the illegal name and the worm.
 * 
 * @version 1.0
 * @author Tom Gijselinck
 *
 */
public class IllegalNameException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3771390456173703501L;


	/**
	 * Initialize this new illegal name exception with given name and given
	 * worm.
	 * 
	 * @param 	name
	 * 			The name for this new illegal name exception.
	 * @param 	worm
	 * 			The worm for this new illegal name exception.
	 * @post	The name of this new illegal name exception is equal to the
	 * 			given name.
	 * 		  |	new.getName() == name
	 * @post	The worm for this new illegal name exception is equal to the
	 * 			given worm.
	 * 		  |	new.getWorm() == worm
	 * @effect	This new illegal name exception is further initialized as a
	 * 			new runtime exception involving no diagnostic message and no
	 * 			cause.
	 * 		  |	super()
	 */
	public IllegalNameException(String name, Worm worm) {
		this.name = name;
		this.worm = worm;
	}
	
	
	/**
	 * Return the name of this illegal name exception.
	 */
	@Basic @Raw @Immutable
	public String getName() {
		return name;
	}
	
	/**
	 * Variable registering the name of this illegal name exception.
	 */
	private final String name;
	
	
	/**
	 * Return the worm of this illegal name exception.
	 */
	@Basic @Raw @Immutable
	public Worm getWorm() {
		return worm;
	}
	
	/**
	 * Variable referencing the worm of this illegal name exception.
	 */
	private final Worm worm;

}
