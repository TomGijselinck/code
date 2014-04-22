package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Model;
import be.kuleuven.cs.som.annotate.Raw;
import worms.exceptions.IllegalRadiusException;

/**
 * A class of game objects involving a position, a direction, a radius, a mass 
 * and a world.
 * 
 * @invar	The direction of each game object must be a valid direction for any 
 * 			game object.
 * 		  |	isValidDirection(getDirection())
 * @invar	Each game object can have its radius as its radius.
 * 		  |	canHaveAsRadius(getRadius())
 * @invar	Each game object has a proper position.
 * 		  |	hasProperPosition()
 * @invar	Each game object has a proper world.
 * 		  |	hasProperWorld()
 * 
 * @version	1.0
 * @author 	Tom Gijselinck
 *
 */

public abstract class GameObject {
	
	/**
	 * Initialize this new game object with given position, given direction and
	 * given radius.
	 * 
	 * @param 	position
	 * 			The position (x,y) for this new game object in a two dimensional
	 * 			space.
	 * @param 	direction
	 * 			The direction this new game object faces.
	 * @param 	radius
	 * 			The radius for this new game object.
	 * @param	mass
	 * 			The mass for this new game object.
	 * @pre		The given direction for this new game object is a valid 
	 * 			direction for any game object.
	 * 		  |	isValidDirection(direction)
	 * @post	The new position for this new game object is equal to the given
	 * 			position.
	 * 		  |	new.getPosition() == position
	 * @post	The new direction for this new game object is equal to the given
	 * 			direction.
	 * 		  |	new.getDirection() == direction
	 * @post	The new radius for this new game object is equal to the given 
	 * 			radius.
	 * 		  |	new.getRadius() = radius
	 * @post	The new mass for this new game object is equal to the given 
	 * 			mass.
	 * 		  |	new.getMass() == mass
	 * @post	This new game object is not terminated.
	 * 		  |	! new.isTerminated()
	 * @throws	IllegalArgumentException
	 * 			The given position for this new game object is not a valid 
	 * 			position for any game object.
	 * 		  |	! isValidPosition(position)
	 * @throws	IllegalArgumentException
	 * 			This new game object cannot have the given radius as its radius.
	 * 		  |	! canHaveAsRadius(radius)
	 * @throws	IllegalArgumentException
	 * 			This new game object cannot have the given mass as its mass.
	 * 		  |	! canHaveAsMass()
	 */
	@Model
	protected GameObject(Position position, double direction, double radius, 
			double mass) throws IllegalArgumentException {
		assert isValidDirection(direction);
		setPosition(position);
		setDirection(direction);
		setRadius(radius);
		setMass(mass);
		
	}
	
	
	
	
	//DESTRUCTOR
	/**
	 * Terminate this game object.
	 * 
	 * @post	This game object is terminated.
	 * 		  |	new.isTerminated()
	 */
	public void terminate() {
		isTerminated = true;
	}
	
	/**
	 * Check whether this game object is terminated.
	 */
	public boolean isTerminated() {
		return isTerminated;
	}
	
	/**
	 * Variable registering whether this game object is terminated.
	 */
	private boolean isTerminated;
	
	
	

	//DIRECTION RELATED METHODS (nominal)
	/**
	 * Return the direction of this game object expressed in radians.
	 *   The direction is the angle expressed in radians at which a game object 
	 *   is facing.
	 */
	@Basic 
	@Raw
	public double getDirection() {
		return this.direction;
	}
	
	/**
	 * Checks whether the given direction is a valid direction for any game 
	 * object.
	 * 
	 * @param 	direction
	 * 			The direction to check.
	 * @return	True if and only if the given direction is a valid number, not 
	 * 			less than the lower angle bound and smaller than the upper angle
	 * 			bound.
	 * 		  |	result == ( (direction.isNaN() == false)
	 * 		  |				&& (direction >= lowerAngleBound) 
	 * 		  |				&& (direction < upperAngleBound) )
	 */
	public static boolean isValidDirection(double direction) {
		return 	( (Double.isNaN(direction) == false)
				&& (direction >= getLowerAngleBound())
				&& (direction < getUpperAngleBound()) );
	}
	
	/**
	 * Return the upper angle bound for any game object.
	 */
	@Basic
	@Immutable
	public static double getUpperAngleBound() {
		return upperAngleBound;
	}

	/**
	 * Return the lower angle bound for any game object.
	 */
	@Basic
	@Immutable
	public static double getLowerAngleBound() {
		return lowerAngleBound;
	}
	
	/**
	 * Return the angle range any game object has.
	 * 
	 * @return	The resulting angle range is the range between the lower angle
	 * 			bound and the upper angle bound.
	 * 			| result == 
	 * 		    |		getUpperAngleRange() - getLowerAngleRange()
	 */
	public double getAngleRange() {
		return getUpperAngleBound() - getLowerAngleBound();
	}

	/**
	 * Variable registering the lower angle bound any game object can have as 
	 * a direction.
	 */
	private final static double lowerAngleBound = 0;
	
	/**
	 * Variable registering the upper angle bound any game object can have as 
	 * a direction.
	 */
	private final static double upperAngleBound = 2*Math.PI;
	
	/**
	 * Set the direction of this game object to the given direction.
	 * 
	 * @param	direction
	 * 			The new direction this game object faces.
	 * @pre		The given direction is a valid direction for any game object.
	 * 		  |	isValidDirection(direction)
	 * @post	The new direction of this game object is equal to the given
	 * 			direction.
	 * 		  |	new.getDirection() == direction
	 */
	@Raw
	public void setDirection(double direction) {
		assert isValidDirection(direction);
		this.direction = direction;
	}
	
	/**
	 * Variable referencing the direction of this game object.
	 */
	private double direction;
	
	
	
	
	//RADIUS RELATED METHODS (defensive)
	/**
	 * Return the radius of this game object expressed in metres.
	 *   The radius of a game object defines the circular shape of that game 
	 *   object.
	 */
	@Basic
	public double getRadius() {
		return this.radius;
	}
	
	/**
	 * Return a boolean reflecting whether this game object can have the given 
	 * radius as its radius.
	 * 
	 * @param 	radius
	 * 			The radius to check.
	 * @return	True if and only if the given radius is a valid number.
	 * 		  |	result ==
	 * 		  |	   	( (Double.isNaN(radius) == false)
	 * 		  |	   && (radius > 0) 
	 * 		  |	   && (radius < Double.POSITIVE_INFINITY) )
	 */
	public boolean canHaveAsRadius(double radius) {
		return
			( (Double.isNaN(radius) == false)
		   && (radius > 0) 
		   && (radius < Double.POSITIVE_INFINITY) );
	}
	
	/**
	 * Set the radius of this game object to the given radius.
	 * 
	 * @param 	radius
	 * 			The new radius for this game object.
	 * @post	The new radius of this game object is equal to the given radius.
	 * 		  |	new.getRadius() == radius
	 * @throws	IllegalArgumentException
	 * 			The given radius is not a valid radius for this game object.
	 * 		  |	! canHaveAsRadius(radius)
	 */
	public void setRadius(double radius) throws IllegalRadiusException {
		if (! canHaveAsRadius(radius)) 
			throw new IllegalArgumentException();
		this.radius = radius;
	}
	
	/**
	 * Variable registering the radius of this game object.
	 */
	private double radius;
	
	
	
	
	//MASS
	/**
	 * Return the mass of this game object expressed in kilograms.
	 */
	@Basic
	public double getMass() {
		return this.mass;
	}
	
	/**
	 * Check whether any game object can have the given mass as its mass.
	 * 
	 * @param 	mass
	 * 		  	The mass to check.
	 * @return	True if and only if the given mass is a valid number and if the
	 * 			given mass is greater than or equal to zero.
	 * 		  |	result ==
	 * 		  |		( (mass >= 0)
	 * 		  |	   && (! Double.isNaN(mass)) );
	 */
	public static boolean isValidMass(double mass) {
		return ( (mass >= 0)
			  && (! Double.isNaN(mass))	);
	}
	
	/**
	 * Set the mass of this game object to the given mass.
	 */
	public void setMass(double mass) {
		if (! isValidMass(mass)) throw new IllegalArgumentException();
		this.mass = mass;
	}
	
	/**
	 * Variable registering the mass of this game object.
	 */
	private double mass;
	
	
	
	
	//JUMPING (defensive)
	/**
	 * Jump this game object from its current position.
	 *   
	 * @post	If the jump time of this game object is infinite, this game 
	 * 			object will jump outside the world of this game object and thus 
	 * 			this game object will be terminated.
	 * 		  |	if (jumpTime() == infinity) then this.terminate()
	 * 			ELse if the jump time of this game object is equal to zero, this 
	 * 			game object will not jump.
	 * 		  |	if (jumpTime() == 0) 
	 * 		  |		then new.getPosition() == this.getPosition()
	 * 			Otherwise the new position of this game object is equal to the 
	 * 			first position on its jump trajectory that is adjacent to 
	 * 			impassable terrain and at least at a distance equal to its 
	 * 			radius from its current position.
	 *   	  |	for each t in 0..jumpTime():
	 *   	  |		if (t == jumpTime())
	 *   	  |			then ( (new.getPosition() == jumpStep(t))
	 *   	  |				&& getWorld().isAdjacent(jumpStep(t)) )
	 *   	  |		else if (t < jumpTime())
	 *   	  |			then getWorld().isPassable(jumpStep(t))
	 * @throws	IllegalArgumentException
	 *   		...
	 *   	  |	! canJump()
	 */
	public void jump(double timeStep) throws IllegalArgumentException {
		if (! canJump())
			throw new IllegalArgumentException();
		double jumpTime = jumpTime(timeStep);
		if (jumpTime == Double.POSITIVE_INFINITY) {
			terminate();
		} else if (jumpTime == 0) {
			// game object will not jump
		} else if ((jumpTime > 0) && (jumpTime < Double.POSITIVE_INFINITY)) {
			Position finalDestination = jumpStep(jumpTime,
					jumpSpeed(getLaunchForce()));
			setPosition(finalDestination);
		}
	}
	
	/**
	 * Checks whether this game object can jump.
	 */
	public abstract boolean canJump();
	
	/**
	 * Returns the time in seconds this game object needs for jumping.
	 * 
	 * @param	dt
	 * 			The time step in which a game object will not completely move 
	 * 			through a piece of impassable terrain.
	 */
	public abstract double jumpTime(double dt);
	
	/**
	 * Returns the in-flight position of a jumping game object after a given 
	 * time interval after launch.
	 * 
	 * @pre		The given time interval is less than or equal to the time this
	 * 			game object needs to perform a jump.
	 * 		  |	timeInterval <= jumpTime()
	 * @return	The resulting position is equal to the initial position of this
	 * 			game object translated by the distance covered in the given time
	 * 			interval at the given initial speed of this game object.
	 * 		  |	result ==
	 * 		  |		getPosition.translate(
	 * 		  |			jumpSpeed() * cos(getDirection()) * timeInterval,
	 * 		  |			jumpSpeed() * sin(getDirection())
	 * 		  |				- 0.5 * getGravityOfEarth() * timeInterval^2 )
	 */
	public Position jumpStep(double timeInterval, double initialSpeed) {
		double g = getGravityOfEarth();
		double deltaX = initialSpeed * Math.cos(getDirection()) * timeInterval;
		double deltaY = initialSpeed * Math.sin(getDirection()) * timeInterval
				- 0.5 * g * Math.pow(timeInterval, 2);
		Position flightPosition = getPosition().translate(deltaX, deltaY);				
		return flightPosition;
	}
	
	/**
	 * Return the initial velocity this game object has in m/s when jumping.
	 */
	protected abstract double jumpSpeed(double launchForce);
	
	
	
	
	
	/**
	 * Return the launch force of this game object.
	 */
	@Basic
	@Immutable
	public abstract double getLaunchForce();
	
	
	
	
	/**
	 * Return the gravity of the earth.
	 */
	public static double getGravityOfEarth() {
		return gravityOfEarth;
	}
	
	/**
	 * Variable registering the gravity of the Earth in m/s² referring to the
	 * acceleration the earth gives to game objects on or near its surface.
	 */
	private static final double gravityOfEarth = 9.80665;
	
	
	
	
	//ASSOCIATIONS
	/**
	 * Return the position of this game object.
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Checks whether this game object can have the given position as its 
	 * position.
	 * 
	 * @param	position
	 * 			The position to check.
	 * @return	True if the given position is not effective for any game object 
	 * 			that is terminated.
	 * 		  |	if (isTerminated())
	 * 		  |		then result == (position == null)
	 * 			Otherwise true if and only if the given position is effective 
	 * 			and is located inside the world of this game object.
	 * 		  |	else result == (position != null)	 
	 */
	public boolean canHaveAsPosition(Position position) {
		if (isTerminated()) {
			return (position == null);
		} else {
			return (position != null);
		}
	}
	
	/**
	 * Check whether this game object has a proper position.
	 * 
	 * @return	True if and only if this game object can have its position as
	 * 			its position.
	 * 		  |	result == isValidPosition(getPosition())
	 */
	public boolean hasProperPosition() {
		return canHaveAsPosition(getPosition());
	}
	
	/**
	 * Set the position of this game object to the given position.
	 * 
	 * @param 	position
	 * 			The position to attach this game object to.
	 * @post	This game object references the given position as its position.
	 * 		  |	new.getPosition() == position
	 * @throws	IllegalArgumentException
	 * 			The given position for this game object is not a valid position 
	 * 			for any game object.
	 * 		  |	! isValidPosition(position)
	 */
	void setPosition(Position position) {
		if (! canHaveAsPosition(position))
			throw new IllegalArgumentException();
		this.position = position;
	}
	
	/**
	 * Variable referencing the position to which this game object is attached.
	 */
	private Position position;
	
	
	
	
	/**
	 * Return the world where this game object is active in.
	 */
	@Basic
	public World getWorld() {
		return world;
	}
	
	/**
	 * ...
	 * @return	...
	 * 		  |	result == getWorld() != null
	 */
	public boolean hasWorld() {
		return getWorld() != null;
	}
	
	/**
	 * Check whether this game object can be attached to the given world.
	 * 
	 * @param 	world
	 * 			The world to attach this game object to.
	 */
	public abstract boolean canHaveAsWorld(World world);
	
	/**
	 * Check whether this game object has a proper world attached to it.
	 * 	Check for consistency of mutual reference implied by bidirectional
	 * 	association and additional restrictions on associated objects.
	 */
	public abstract boolean hasProperWorld();
	
	/**
	 * Set the world where this game object is attached to, to the given world.
	 * 
	 * @param 	world
	 * 			The world to which this game object must be attached.
	 * @post	This game object is attached to the given world.
	 * 		  |	new.getWorld() == world
	 * @throws	IllegalArgumentException
	 * 			...
	 * 		  |	(! canHaveAsWorld(world))
	 */
	public void setWorld(World world) {
		if (! canHaveAsWorld(world)) throw new IllegalArgumentException();
		this.world = world;
	}
	
	/**
	 * Variable referencing the world where this game object is attached to.
	 */
	private World world;

}
