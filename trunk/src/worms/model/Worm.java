 package worms.model;

import java.util.ArrayList;
import java.util.List;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class of worms as special kinds of game objects involving as additional 
 * properties action points, hit points, weapons and a name.
 * 
 * @invar	The direction of each worm must be a valid direction for any worm.
 * 		  |	isValidDirection(getDirection())
 * @invar	Each worm can have its radius as its radius.
 * 		  |	canHaveAsRadius(getRadius())
 * @invar	The name of each worm must be valid name for any worm.
 * 		  |	isValidName(getName())
 * @invar	Each worm has a proper position.
 * 		  |	hasProperPosition()
 * @invar	Each worm has a proper world.
 * 		  |	hasProperWorld()
 * @invar	The weapons attached to each worm must be proper weapons for that
 * 			worm.
 * 		  |	hasProperWeapons()
 * 
 * @version 2.0
 * @author 	Tom Gijselinck
 *
 */

public class Worm extends GameObject {
	
	//CONTSTRUCTORS
	/**
	 * Initialize this new worm with given position, given direction, 
	 * given radius and given name.
	 * 
	 * @param 	position
	 * 			The position (x,y) for this new worm in a two dimensional space.
	 * @param 	direction
	 * 			The direction this new worm faces.
	 * @param 	radius
	 * 			The radius for this new worm.
	 * @param 	name
	 * 			The name for this new worm.
	 * @post	The new name for this new worm is equal to the given name.
	 * 		  |	new.getName() == name
	 * @post	The new current action points for this new worm is equal to the 
	 * 			action points maximum of this worm.
	 * 		  |	new.getCurrentActionPoints() == new.getActionPointsMaximum()
	 * @post	The new hit current points for this new worm is equal to the hit
	 * 			points maximum of this worm.
	 * 		  |	new.getCurrentHitPoints() == new.getHitPointsMaximum()
	 * @post	The weapons this new worm has are the Rifle and the Bazooka.
	 * 		  |	new.hasAsWeapon(Weapon.RIFLE) 
	 * 		  |	&& new.hasAsWeapon(Weapon.BAZOOKA)
	 * @post	The new active weapon of this new worm is the first weapon in
	 * 			the list of weapons attached to this worm.
	 * 		  |	getActiveWeapon() == weapons.get(0)
	 * @post	The new mass for this new worm is equal to the mass of a
	 * 			spherical body with the density of a worm.
	 * 		  |	new.getMass() ==
	 * 		  |		getWormDensity()*4/3*Math.PI*Math.pow(getRadius(), 3)
	 * @throws	IllegalArgumentException
	 * 		  	The given name for this new worm is not a valid name for any 
	 * 			worm.
	 * 		  |	! isValidName(name)
	 * @effect	This new worm is initialized as a game object with the given 
	 * 			position, the given direction and the given radius.
	 * 		  |	super(position, direction, radius, 1)
	 */
	@Raw
	public Worm(Position position, double direction, double radius, String name)
			throws IllegalArgumentException {
		super(position, direction, radius, 1);
		setMass();
		setName(name);
		setCurrentActionPoints(getActionPointsMaximum());
		setCurrentHitPoints(getHitPointsMaximum());
		addAsWeapon(new Rifle());
		addAsWeapon(new Bazooka());
		activeWeapon = weapons.get(0);
	}
	
	
	
	
	//POSITION RELATED METHODS (defensive)	
	/**
	 * Move this worm the given number of steps if the resulting position is
	 * passable and adjacent to impassable terrain.
	 * 
	 * @param 	steps
	 * 			The number of steps this worm has to move.
	 * @post	If there exist adjacent positions, the new position of this worm
	 * 			will be the position of those positions with the maximum 
	 * 			distance moved an the least divergence from the orientation of
	 * 			this worm.
	 * 		  |	let
	 * 		  |		t0 = getDirection()
	 * 		  |		x0 = this.getPosition().getX()
	 * 		  |		y0 = this.getPosition().getY()
	 * 		  |		x* = new.getPosition().getX()
	 * 		  |		y* = new.getPosition().getY()
	 * 		  |		x = position.getX()
	 * 		  |		y = position.getY()
	 * 		  |		position = new Position(x0 + r*cos(t), y0 + r*sin(t))
	 * 		  | in
	 * 		  |		if (for some t in (t0 - 0.7875)..(t0 + 0.7875):
	 * 		  |		  for some r in 0..getRadius():
	 * 		  |			isAdjacent(position) )
	 * 		  |		then new.getPosition() == position where 
	 * 		  |			for each position in isAdjacent(position):
	 * 		  |		  		( (sqrt((x0 - x*)^2 + (y0 - y*)^2)
	 * 		  |			  		>= sqrt((x0 - x)^2 + (y0 - y)^2))
	 * 		  |		   	   && (arctan((y0 - y*)/(x0 - x*))
	 * 		  |					<= arctan((y0 - y)/(x0 - x))) )
	 * 		  |			
	 * 			Else if there exist passable positions, but no adjacent 
	 * 			positions, this worm will move to the position with maximum 
	 * 			distance moved and the least divergence from the orientation of
	 * 			this worm, and will then fall (@effect).
	 * 		  |	else if (for some t in (t0 - 0.7875)..(t0 + 0.7875):
	 * 		  |		for some r in 0..getRadius():
	 * 		  |			isPassable(position) )
	 * 		  |	then new.getPosition() == position where 
	 * 		  |		for t in (t0 - 0.7875)..(t0 + 0.7875):
	 * 		  |			for r in 0..getRadius():
	 * 		  |				if (isPassable(position))
	 * 		  |					then ( (sqrt((x0 - x*)^2 + (y0 - y*)^2)
	 * 		  |			  				>= sqrt((x0 - x)^2 + (y0 - y)^2))
	 * 		  |		  				&& (arctan((y0 - y*)/(x0 - x*))
	 * 		  |							<= arctan((y0 - y)/(x0 - x))) )
	 * 			Otherwise, if no passable positions exist, this worm will not
	 * 			move.
	 * 		  |	else new.getPosition() = this.getPosition()
	 * @effect	This worm will fall if the position is not adjacent.
	 * 		  |	if (! getWorld().isAdjacent())
	 * 		  |		then fall()
	 * @throws	IllegalArgumentException
	 * 			This worm cannot move the given number of steps in the optimal
	 * 			direction.
	 * 		  |	let
	 * 		  |		slope = arctan((y0 - y*)/(x0 - x*))
	 * 		  |	in
	 * 		  |		(! canMove(slope))
	 */
	public void move(int steps) throws IllegalArgumentException {
		while ((steps > 0) && hasWorld()) {
			Position adjacentPosition = getOptimalPosition(this, true);
			if (adjacentPosition == null) {
				Position passablePosition = getOptimalPosition(this, false);
				if (passablePosition == null) {
					// unable to move
					steps = 0;
				} else {
					setPosition(passablePosition);
					fall();
				}
			} else {
				double slope = Math.atan(
						(this.getPosition().getY() - adjacentPosition.getY())
						/ (this.getPosition().getX() - adjacentPosition.getX()) );
				if (! canMove(slope)) throw new IllegalArgumentException();
				setPosition(adjacentPosition);
				int consumedActionPoints = getConsumedActionPoints(slope);
				decreaseActionPoints(consumedActionPoints);
			}
			
			steps -= 1;
		}
	}
	
	/**
	 * Let this worm fall until it hits impassabable terrain.
	 * 
	 * @post	If there exist an adjacent position straight below this worm, 
	 * 			the new position of this worm is the first adjacent position
	 * 			straight below this worm.
	 * 		  |	let
	 * 		  |		x0 = getPosition().getX()
	 * 		  |		y0 = getPosition().getY()
	 * 		  |		position = new Position(x0, y)
	 * 		  |	in
	 * 		  |		if (for some y in 0..y0:
	 * 		  |			isAdjacent(position) )
	 * 		  |		then (new.getPosition() == position) where
	 * 		  |			for each y in 0..y0 where isAdjacent(position):
	 * 		  |		   		y <= new.getPosition().getY()
	 * 			Else this worm will fall out of its world.
	 * 		  |	new.isTerminated()
	 * @post	The current	amount of action points has not changed.
	 * 		  |	new.getCurrentActionPoints() == this.getCurrentActionPoints()
	 */
	public void fall() {
		boolean falling = true;
		double radius = getRadius();
		double dy = radius/10;
		double travelledFallingMeters = 0;
		World world = getWorld();
		
		while (falling) {
			Position position = getPosition().translate(0, -dy);
			travelledFallingMeters += dy;
			if (! world.objectIsInsideWorldBorders(position, radius)) {
				getWorld().removeAsWorm(this);
				falling = false;
			} else if (getWorld().isAdjacent(position, getRadius())) {
				falling = false;
				setPosition(position);
				decreaseHitPoints(3 * (int) travelledFallingMeters + 1);
			} else {
				falling = true;
				setPosition(position);
			}
		}
	}
	
	/**
	 * Check whether this worm can fall.
	 * 
	 * @return	True if and only if this worm has a world and is not adjacent to 
	 * 			impassable terrain.
	 * 		  |	result ==
	 * 		  |	   ( hasWorld()
	 * 		  |	  && (! getWorld().isAdjacent(getPosition(), getRadius())) )
	 */
	public boolean canFall() {
		return 
			  (	hasWorld()
			 &&	(! getWorld().isAdjacent(getPosition(), getRadius())) );
	}
	
	/**
	 * ...
	 * 
	 * @param 	worm
	 * 			...
	 * @return	...
	 */
	private Position getOptimalPosition(Worm worm, boolean resultIsAdjacent) {
		double distance;
		double divergence;
		double maxDivergence = 0;
		double maxDistance = getRadius();
		double minDistance = maxDistance;
		double dr = maxDistance/58;
		double stepSize = 0.0175;
		double dtheta;
		Position optimalPosition;
		
		while (maxDivergence <= 0.7875) {
			distance = maxDistance;
			divergence = maxDivergence;
			
			while (distance > minDistance) {
				optimalPosition = getOptimalPositionFromPolCoo(divergence, 
						distance, resultIsAdjacent);
				if (optimalPosition != null) {
					return optimalPosition;
				}
				distance -= dr;
			}
			divergence = 0;
			
			dtheta = dr / distance;
			while (divergence <= maxDivergence) {
				optimalPosition = getOptimalPositionFromPolCoo(divergence, 
						distance, resultIsAdjacent);
				if (optimalPosition != null) {
					return optimalPosition;
				}
				divergence += dtheta;
			}
			
			maxDivergence += stepSize;
			minDistance -= dr;
		}
		
		return null;
	}
	
	/**
	 * ...
	 * 
	 * @param 	x0
	 * 			...
	 * @param 	y0
	 * 			...
	 * @param 	direction
	 * 			...
	 * @param 	divergence
	 * 			...
	 * @param 	distance
	 * 			...
	 * @return	...
	 */
	private Position getOptimalPositionFromPolCoo(double divergence, 
			double distance, boolean resultIsAdjacent) {
		double x0 = getPosition().getX();
		double y0 = getPosition().getY();
		double direction = getDirection();
		World world = getWorld();
		double radius = getRadius();
		Position left = new Position(
				x0 + distance * Math.cos(direction + divergence), 
				y0 + distance * Math.sin(direction + divergence) );
		Position right = new Position(
				x0 + distance * Math.cos(direction - divergence), 
				y0 + distance * Math.sin(direction - divergence) );
		
		if (resultIsAdjacent) {
			if (world.isAdjacent(left, radius)) {
				return left;
			}
			if (world.isAdjacent(right, radius)) {
				return right;
			} 
			return null;
		} else if (! resultIsAdjacent) {
			if (world.isPassableForObject(left, radius)) {
				return left;
			}
			if (world.isPassableForObject(right, radius)) {
				return right;
			}
			return null;
		}
		return null;
	}
	
	/**
	 * Return the action points needed to move one step in the given direction.
	 * 
	 * @param 	steps
	 * 			The number of steps needed to cover with action points.
	 * @param 	direction
	 * 			The direction in which the steps will be taken.
	 * @return	The resulting amount of action points is equal to 
	 * 			|cos(direction)| + |4 * sin(direction)|.
	 * 		  |	result == |cos(direction)| + |4 * sin(direction)|
	 */
	public int getConsumedActionPoints(double direction) {
		return (int) Math.ceil((Math.abs(Math
				.cos(direction)) + Math.abs(4 * Math.sin(direction))));
	}
	
	/**
	 * Check whether this worm can move one step in the given direction.
	 * 	Horizontal movement is less expensive than vertical movement.
	 * 
	 * @param	direction
	 * 			The direction in which to check whether this can move
	 * 			one step.
	 * @return	True if and only if this worm has enough action points to 
	 * 			move the given number of steps in the direction it is facing.
	 * 		  |	result ==
	 * 		  |	  ( (this.getCurrentActionPoints() >= 
	 * 		  |		  getConsumedActionPoints(direction) )
	 * 		  |	 && (hasProperWorld()) )
	 */
	public boolean canMove(double direction) {
		return ( (getCurrentActionPoints() >= getConsumedActionPoints(direction))
			  && hasProperWorld() );	
	}
	
	/**
	 * Jump this worm from the current position with respect to its direction
	 * and its current number of action points.
	 *   Jumping of a worm is an active movement and consumes all its remaining
	 *   current action points.
	 *   		
	 * @effect	This worm jumps as a game object.
	 * 		  |	super.jump()
	 * @post	If this worm has jumped, the new current action points of this 
	 * 			worm is equal to zero.
	 * 		  |	if (! this.getPosition().equals(new.getPosition()))
	 * 		  |		then new.getActionPoints() == 0
	 * @throws	IllegalArgumentException
	 * 			This worm cannot jump.
	 * 		  |	! canJump()
	 */
	@Override
	public void jump(double timeStep) 
			throws IllegalArgumentException, IllegalArgumentException {
		if (! canJump())
			throw new IllegalArgumentException();
		Position initialPosition = getPosition();
		super.jump(timeStep);
		if (! initialPosition.equals(getPosition())) {
			setCurrentActionPoints(0);
		}		
	}
	
	/**
	 * Checks whether this worm can jump.
	 * 
	 * @return	True if and only if the position of this worm is adjacent and
	 * 			this worm has current action points greater than zero.
	 * 		  |	result ==
	 * 		  |		( getWorld().isAdjacent(getPosition(), getRadius())
	 *   	  |	   && (this.getCurrentActionPoints() > 0) )
	 */
	@Override
	public boolean canJump() {
		return ( hasWorld()
			  && getWorld().isAdjacent(getPosition(), getRadius())
		      && (getCurrentActionPoints() > 0) );
	}
	
	/**
	 * Returns the time in seconds this worm needs for jumping.
	 * 
	 * @param	dt
	 * 			The time step in which a worm will not completely move through
	 * 			a piece of impassable terrain.
	 * @return	If there exists an adjacent position in the jump trajectory of
	 * 			this worm inside the world of this worm that is at least a 
	 * 			distance equal to its radius away from its original position,
	 * 			the resulting jump time is equal to the time until this worms 
	 * 			reaches that position.
	 * 		  |	let
	 * 		  |		jumpPosition = jumpStep(time, jumpSpeed())
	 * 		  |		R = getRadius()
	 * 		  |	in
	 * 		  |		if for some time in double:
	 * 		  |			if ( getWorld().isAdjacent(jumpPosition, R)
	 * 		  |			  && (jumpPosition.getDistanceFrom(getPosition())
	 * 		  |				> getRadius())
	 * 		  |			  && getWorld().isInsideWorldBorders(jumpPosition) 
	 * 		  |			  && (for each t in 0..time where t < time:
	 *   	  |					getWorld().isPassable(jumpStep(t)
	 *   	  |				 && (! getWorld().isAdjacent(jumpPosition, R))	)
	 * 		  |				then result == time
	 * 			Else if this worm hits an impassable piece of terrain at a 
	 * 			distance equal to the radius of this worm from its original 
	 * 			position, the resulting jump time is equal to zero.
	 * 		  |	else if for some time in double:
	 * 		  |		if ( (jumpPosition.getDistanceFrom(getPosition()) < R)
	 * 		  |		  && getWorld().isImpassable(jumpPosition) )
	 * 		  |			then result == 0
	 * 			Otherwise, if this worm leaves its game world, the resulting 
	 * 			jump time is equal to positive infinity (i.e. an infinite jump
	 * 			in an infinite and undefined space).
	 * 		  |	else if for some time in double:
	 * 		  |		if (! getWorld().isInsideWorldBorders(jumpPosition))
	 * 		  |			then result == Double.POSITIVE_INFINITY
	 * @throws	IllegalArgumentException
	 * 			This worm cannot jump.
	 * 		  |	! canJump()
	 */
	@Override
	public double jumpTime(double dt) {
		if (! hasWorld()) return Double.POSITIVE_INFINITY;
		
		double jumpTime = 0;
		boolean jumping = true;
		Position startPosition = getPosition().clone();
		Position jumpPosition = getPosition().clone();
		Position pre = jumpPosition.clone();
		World world = getWorld();
		double radius = getRadius();
		double jumpSpeed = jumpSpeed(getLaunchForce());
		double interval = 0;
		double ds = 0.1 * getRadius();
		
		while (jumping
				&& (jumpPosition.getDistanceFrom(startPosition) < radius)
				&& (world.isInsideWorldBorders(jumpPosition))) {
			jumpTime += dt;
			jumpPosition = jumpStep(jumpTime, jumpSpeed);
			interval += jumpPosition.getDistanceFrom(pre);
			pre = jumpPosition.clone();
			while (interval < ds) {
				jumpTime += dt;
				jumpPosition = jumpStep(jumpTime, jumpSpeed);
				interval += jumpPosition.getDistanceFrom(pre);
				pre = jumpPosition.clone();
			}
			interval = 0;
			if (world.isImpassableForObject(jumpPosition, radius)) {jumping = false;}
		}
		
		while (jumping && (world.isInsideWorldBorders(jumpPosition))) {
			jumpTime += dt;
			jumpPosition = jumpStep(jumpTime, jumpSpeed);
			interval += jumpPosition.getDistanceFrom(pre);
			pre = jumpPosition.clone();
			while (interval < ds) {
				jumpTime += dt;
				jumpPosition = jumpStep(jumpTime, jumpSpeed);
				interval += jumpPosition.getDistanceFrom(pre);
				pre = jumpPosition.clone();
			}
			interval = 0;
			if (world.isAdjacent(jumpPosition, radius)) {jumping = false;}
		
		}
		
		if (! world.isAdjacent(jumpPosition, radius)) {
			jumpTime = 0;
		}
		if (! world.isInsideWorldBorders(jumpPosition)) {
			jumpTime = Double.POSITIVE_INFINITY;
		}
		
		return jumpTime;
	}
	
	/**
	 * Returns the initial velocity this worm has in m/s when jumping.
	 * 
	 * @return	The resulting speed of this worm is equal to the speed as a
	 * 			result of the jump force exerted by the worm in an interval of 
	 * 			0.5 seconds.
	 * 		  |	let 
	 * 		  |		g = GameObject.getGravityOfEarth()
	 * 		  |		double F = 5 * this.getCurrentActionPoints() 
	 * 		  |					+ this.getMass()*g
	 * 		  | in
	 * 		  |		result ==
	 *   	  |			F / this.getMass() * 0.5
	 */
	@Override
	public double jumpSpeed(double launchForce) {
		return launchForce / getMass() * 0.5;
	}
	
	@Override
	public double getLaunchForce() {
		double g = GameObject.getGravityOfEarth();
		return 5 * getCurrentActionPoints() + getMass() * g;
	}
	
	
	
	
	//DIRECTION RELATED METHODS (nominal)
	/**
	 * Turn this worm by the given angle in radians by changing its direction.
	 * @param 	angle
	 * 			The angle in radians by which this worm has to turn.
	 * @pre		This worm can turn the given angle.
	 * 		  |	canTurn(angle)
	 * @post	The new direction of this worm is equal to modulo angle range of  
	 * 			the sum of the old direction from this worm and the given angle.
	 * 			The resulting angle is a valid angle.
	 * 		  | let 
	 * 		  |		double newAngle = (this.getDirection() + angle)%(angleRange) 
	 * 		  |	in
	 * 		  |		if (newAngle > 0)
	 * 		  |			new.getDirection() == newAngle;
	 * 		  |		if (newAngle < 0)
	 * 		  |			new.getDirection() == newAngle + angleRange;
	 * @post	The new current action points is equal to the initial current 
	 * 			action points decremented by the fraction of the angle range 
	 * 			times sixty.
	 * 		  |	new.getCurrentActionPoints() ==
	 * 		  |		this.getCurrentActionPoints() 
	 * 		  |		- Math.abs(angle)/(angleRange)*60)
	 */
	public void turn(double angle) {
		assert canTurn(angle);
		double newAngle = (this.getDirection() + angle) % (getAngleRange());
		if (newAngle > 0) {
			setDirection(newAngle);
			setCurrentActionPoints((int) (getCurrentActionPoints() - 60
					* Math.abs(angle) / (getAngleRange())));
		}
		if (newAngle < 0) {
			setDirection(newAngle + getAngleRange());
			setCurrentActionPoints((int) (getCurrentActionPoints() - 60
					* Math.abs(angle) / (getAngleRange())));
		}
	}

	/**
	 * Return a boolean reflecting whether this worm can turn by the given
	 * angle.
	 * 
	 * @param 	angle
	 * 			The angle to be checked.
	 * @return	True if and only if the absolute value of the given angle is a 
	 * 			valid direction and if this worm has enough action points to
	 * 			turn by the given angle.
	 * 		  |	result == ( (isValidDirection(Math.abs(angle)))
	 * 		  |			 	&& (this.getCurrentActionPoints()
	 * 		  |				  >= (Math.abs(angle)/(angleRange)*60)) );
	 */
	public boolean canTurn(double angle) {
		return ( (isValidDirection(Math.abs(angle)))
				&& (this.getCurrentActionPoints()
					>= (Math.abs(angle)/(getAngleRange())*60)) );
	}
	
	
	
	
	
	//RADIUS RELATED METHODS (defensive)
	/**
	 * Return a boolean reflecting whether this worm can have the given radius
	 * as its radius.
	 * 
	 * @param 	radius
	 * 			The radius to check.
	 * @return	True if and only if the given radius is a valid radius for game
	 * 			objects, not less than the lower radius bound of this worm and 
	 * 			less than positive infinity (i.e. a finite radius).
	 * 		  |	result ==
	 * 		  |	  ( super.canHaveAsRadius(radius)
	 * 		  |	 && (radius >= getLowerRadiusBound())
	 * 		  |  && (radius < Double.POSITIVE_INFINITY) )
	 */
	@Override
	public boolean canHaveAsRadius(double radius) {
		return
		 (  super.canHaveAsRadius(radius)
		 &&	(radius >= getLowerRadiusBound()) );
	}
	
	/**
	 * Return the maximum action points this worm can have.
	 *   The maximum of action points of this worm is derived from its mass.
	 * 
	 * @return	The resulting maximum action points is equal to the mass of this
	 * 			worm rounded to the nearest integer.
	 * 		  |	result ==
	 * 		  |		Math.round(getMass())
	 */
	public int getActionPointsMaximum() {
		return (int) Math.round(getMass());
	}
	
	/**
	 * Return the maximum hit points this worm can have.
	 *   The maximum of hit points of this worm is derived from its mass.
	 * 
	 * @return	The resulting maximum hit points is equal to the mass of this
	 * 			worm rounded to the nearest integer.
	 * 		  |	result ==
	 * 		  |		Math.round(getMass())
	 */
	public int getHitPointsMaximum() {
		return (int) Math.round(getMass());
	}

	
	

	/**
	 * Return the lower radius bound of this worm expressed in metres.
	 */
	@Basic
	@Immutable
	public double getLowerRadiusBound() {
		return lowerRadiusBound;
	}
	
	/**
	 * Variable registering the lower radius bound of this worm expresssed in 
	 * metres.
	 */
	private final double lowerRadiusBound = 0.25;
	
	
	
	
	/**
	 * Return the mass of this worm in kilograms.
	 *   The mass of a worm is derived from its radius which specifies a
	 *   spherical body from which the mass is calculated.
	 *   
	 * @post	The resulting mass is equal to the mass of a spherical body with
	 * 			the density. of a worm.
	 * 		  |	new.getMass() ==
	 * 		  |		getWormDensity()*4/3*Math.PI*Math.pow(getRadius(), 3)
	 */
	
	public void setMass() {
		Double mass = getWormDensity()*4/3*Math.PI*Math.pow(getRadius(), 3);
		setMass(mass);
	}
	
	
	
	
	
	/**
	 * Return the worm density of all worms expressed in kg/m³.
	 */
	@Basic
	private static double getWormDensity() {
		return wormDensity;
	}
	
	/**
	 * Variable registering the density of any worm in kg/m³.
	 */
	private static final double wormDensity = 1062;
	
	
	
	
	
	//ACTION POINTS RELATED METHODS (total)
	/**
	 * Return the current action points of this worm.
	 *   The current action points of a worm determines how much a worm can 
	 *   move, turn and how far a worm can jump.
	 */
	@Basic
	public int getCurrentActionPoints() {
		return this.currentActionPoints;
	}
	
	/**
	 * Decrease the current number of action points of this worm with the given
	 * amount of action points.
	 * 
	 * @param 	actionPoints
	 * 			The number of action points to subtract from the current amount
	 * 			of action points.
	 * @post	If the given amount of action points is less than zero, the 
	 * 			new current action points for this new worm is equal to zero.
	 * 			If the given current action points is greater than or equal to
	 * 			the maximum amount of action points, the new current action
	 * 			points for this new worm is equal to the maximum action points. 
	 * 			Otherwise the new current action points for this new worm is
	 * 			equal to the given current action points.
	 * 		  |	let
	 * 		  |		currentActionPoints = this.getCurrentActionPoints()
	 * 		  |	in
	 * 		  |		if (actionPoints >= currentActionPoints) 
	 * 		  |			then new.getCurrentActionPoints() == 0;
	 * 		  | 	else if ( (actionPoints > 0) 
	 * 		  |			&& (actionPoints < currentActionPoints) )
	 *		  |			then new.getCurrentActionPoints 
	 *		  |				== currentActionPoints - actionPoints
	 *		  | 	else if (actionPoints <= 0)
	 *		  | 		new.getCurrentActionPoints == currentActionPoints
	 * 
	 */
	public void decreaseActionPoints(int actionPoints) {
		int currentActionPoints = getCurrentActionPoints();
		if (actionPoints >= currentActionPoints) {
			setCurrentActionPoints(0);
		} else if ((actionPoints > 0) && (actionPoints < currentActionPoints)) {
			setCurrentActionPoints(currentActionPoints - actionPoints);
		} else if (actionPoints <= 0) {}
	}
	
	/**
	 * Set the current action points of this worm to the given action points.
	 * 
	 * @param	actionPoints
	 *			The new current action points for this worm.
	 * @post	If the given current action points is less than zero, the 
	 * 			new current action points for this new worm is equal to zero.
	 * 			If the given current action points is greater than or equal to
	 * 			the maximum amount of action points, the new current action
	 * 			points for this new worm is equal to the maximum action points. 
	 * 			Otherwise the new current action points for this new worm is
	 * 			equal to the given current action points.
	 * 		  |	if (actionPoints <= 0) this.currentActionPoints = 0;
	 * 		  | if ( (actionPoints > 0) && (actionPoints < getActionPointsMaximum()))
	 *		  |		currentActionPoints = actionPoints;
	 *		  | if (actionPoints >= getActionPointsMaximum())
	 *		  | 	this.currentActionPoints = getActionPointsMaximum();
	 */
	protected void setCurrentActionPoints(int actionPoints) {
		if (actionPoints <= 0) {
			currentActionPoints = 0;
		} else if ((actionPoints > 0)
				&& (actionPoints < getActionPointsMaximum())) {
			currentActionPoints = actionPoints;
		} else if (actionPoints >= getActionPointsMaximum()) {
			this.currentActionPoints = getActionPointsMaximum();
		}
	}
	
	/**
	 * Variable registering the the current action points of a worm.
	 */
	private int currentActionPoints;
	
	
	
	
	
	//HIT POINTS
	/**
	 * Return the current amount of hit points this worm has left.
	 */
	@Basic
	public int getCurrentHitPoints() { return currentHitPoints;}
	
	/**
	 * Subtract the given hit points from the current hit points this worm has.
	 * 
	 * @param 	inflictedHitPoints
	 * 			The number of hit points to subtract.
	 * @effect	Set the new current hit points to the original hit points
	 * 			decremented with the given hit points.
	 * 		  |	setCurrentHitPoints(this.getCurrentHitpoints()
	 * 		  |		- inflictedHitPoints)
	 */
	public void decreaseHitPoints(int inflictedHitPoints) {
		setCurrentHitPoints(getCurrentHitPoints() - inflictedHitPoints);
		if (getCurrentHitPoints() <= 0) {
			getWorld().removeAsWorm(this);
		}
	}
	
	/**
	 * Add the given hit points to the current hit points this worm has.
	 * 
	 * @param 	hitPoints
	 * 			The number of hit points to add.
	 * @effect	Set the new current hit points to the original hit points
	 * 			incremented with the given hit points.
	 * 		  |	setCurrentHitPoints(this.getCurrentHitpoints() + hitPoints)
	 */
	public void addHitPoints(int hitPoints) {
		setCurrentHitPoints(getCurrentHitPoints() + hitPoints);
	}
	
	/**
	 * Set the current hit points of this worm to the given hit points.
	 * 
	 * @param	hitPoints
	 *			The new current hit points for this worm.
	 * @post	If the given current hit points is less than zero, the 
	 * 			new current hit points for this new worm is equal to zero.
	 * 			If the given current hit points is greater than or equal to
	 * 			the maximum amount of hit points, the new current hit
	 * 			points for this new worm is equal to the maximum hit points. 
	 * 			Otherwise the new current hit points for this new worm is
	 * 			equal to the given current hit points.
	 * 		  |	if (hitPoints <= 0) this.currentHitPoints = 0;
	 * 		  | if ( (hitPoints > 0) && (hitPoints < getHitPointsMaximum()))
	 *		  |		currentHitPoints = hitPoints;
	 *		  | if (hitPoints >= getHitPointsMaximum())
	 *		  | 	this.currentHitPoints = getHitPointsMaximum();
	 */
	public void setCurrentHitPoints(int hitPoints) {
		if (hitPoints <= 0) {
			currentHitPoints = 0;
		} else if ((hitPoints > 0)
				&& (hitPoints < getActionPointsMaximum())) {
			currentHitPoints = hitPoints;
		} else if (hitPoints >= getActionPointsMaximum()) {
			this.currentHitPoints = getActionPointsMaximum();
		}
	}
	
	/**
	 * Variable registering the current hit points of this worm.
	 */
	private int currentHitPoints;
	
	
	
	
	
	//NAME RELATED METHODS (defensive)
	/**
	 * Return the name of this worm.
	 */
	@Basic
	public String getName() {
		return this.name;
	}
	
	/**
	 * Set the name of this worm to the given name.
	 * 
	 * @param	name
	 * 			The new name for this worm.
	 * @post	The new name of this worm is equal to the given name.
	 * 		  |	this.getName() == name
	 * @throws	IllegalArgumentException()
	 * 		  	The given name is not a valid name for any worm.
	 * 		  |	! isValidName(name)
	 */
	public void setName(String name) throws IllegalArgumentException {
		if (! isValidName(name))
			throw new IllegalArgumentException();
		this.name = name;
	}
	
	/**
	 * Variable registering the name of a worm.
	 */
	private String name;
	
	/**
	 * Checks whether the given name is a valid name for any worm.
	 * 
	 * @param 	name
	 * 			The name to check.
	 * @return	True if and only if the given name is at least two characters
	 * 			long, start with an uppercase letter and all characters are
	 * 			upper or lower case letters, spaces or single or double quotes.
	 * 		  |	result ==  
	 * 		  |		(name.length() >= 2)
	 * 		  |		&& (Character.isUpperCase(name.charAt(0)))
	 *   	  |		&& ! for (int i = 0; i < name.length(); i++) {
	 *		  |				char ch = name.charAt(i);
	 *		  |				(! Character.isLowerCase(ch))
	 *		  |				&&	(! Character.isUpperCase(ch))
	 *		  |				&&	(! Character.isWhitespace(ch))
	 *		  |				&&  (! Character.isDigit(ch))
	 *		  |				&&	(ch != '\'')
	 *		  |				&&	(ch != '\"') )
	 *		  |			}		
	 */
	public static boolean isValidName(String name) {
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			if (	(! Character.isLowerCase(ch))
				&&	(! Character.isUpperCase(ch))
				&&	(! Character.isWhitespace(ch))
				&&  (! Character.isDigit(ch))
				&&	(ch != '\'')
				&&	(ch != '\"') ) {
						return false;
			}
		}
		return ((name.length() >= 2)
				&& (Character.isUpperCase(name.charAt(0))));
	}
	
	
	
	
	//ASSOCIATION	
	/**
	 * Check whether this worm can have the given world as its world.
	 * 
	 * @param 	world
	 * 			The world to check.
	 * @return	True if and only if the given world is not effective or if it
	 * 			can have this worm as one of its worms.
	 * 		  |	result ==
	 * 		  |		( (world == null)
	 * 		  |	   || world.canHaveAsWorm(this) )
	 */
	@Override
	public boolean canHaveAsWorld(World world) {
		return ((world == null) || world.canHaveAsWorm(this));
	}
	
	/**
	 * Check whether this worm has a proper world attached to it.
	 * 	Check for consistency of mutual reference implied by bidirectional
	 * 	association and additional restrictions on associated objects.
	 * 
	 * @return	True if and only if this worm can have its world as the world to
	 * 			which it is attached, and if that world is either not effective
	 * 			or has this worm as one of its worms.
	 * 		  |	result == 
	 * 		  |		( canHaveAsWorld(getWorld())
	 * 		  |	   && ( (getWorld() == null)
	 * 		  |	     ||	getWorld().hasAsWorm(this)) )
	 */
	@Override
	public boolean hasProperWorld() {
		return ( canHaveAsWorld(getWorld())
			  && ( (getWorld() == null)
				||	getWorld().hasAsWorm(this)) );
	}
	
	
	
	
	/**
	 * Return the number of weapons of this worm.
	 */
	@Basic
	public int getNbWeapons() {
		return weapons.size();
	}
	
	/**
	 * Return the weapon of this worm at the given index.
	 * 
	 * @param 	index
	 * 			The index of the weapon to be returned.
	 * @throws	IndexOutOfBoundsException
	 * 			The given index is not positive or it exceeds the number of 
	 * 			weapons attached to this worm.
	 * 		  |	(index < 0) || (index > getNbWeapons() - 1)
	 */
	public Weapon getWeaponAt(int index) throws IndexOutOfBoundsException {
		return weapons.get(index);
	}
	
	/**
	 * Return the index at which the given weapon is registered as a weapon of
	 * this worm.
	 * 
	 * @param	weapon
	 * 			The weapon to look for.
	 * @return	The given weapon is registered as a weapon of this worm at the 
	 * 			resulting index.
	 * 		  |	getWeaponAt(result) == weapon
	 * @throws	IllegalArgumentException
	 * 			The given weapon is not a weapon of this worm.
	 * 		  |	! hasAsWeapon(weapon)
	 */
	public int getIndexOfWeapon(Weapon weapon) {
		if (! hasAsWeapon(weapon)) throw new IllegalArgumentException();
		return (weapons.indexOf(weapon));
	}
	
	/**
	 * Check whether this worm has the given weapon as one of its weapons.
	 * 
	 * @param 	weapon
	 * 			The weapon to check.
	 */
	@Basic
	public boolean hasAsWeapon(Weapon weapon) {
		return weapons.contains(weapon);
	}
	
	/**
	 * Check whether this worm can have the given weapon as one of its weapons.
	 * 
	 * @param 	weapon
	 * 			The weapon to check.
	 * @return	False if the given weapon is not effective.
	 * 		  |	if (weapon == null)
	 * 		  |		then result == false
	 * 			Otherwise true if and only if this worm is not yet terminated
	 * 			and the given weapon is not yet registered as a weapon attached
	 * 			to this worm.
	 * 		  |	else result == 
	 * 		  |		( (! this.isTerminated())
	 * 		  |	   && (! this.hasAsWeapon(weapon)) )
	 */
	public boolean canHaveAsWeapon(Weapon weapon) {
		if (weapon == null) {
			return false;
		} else {
			return ( (! this.isTerminated())
				  && (! this.hasAsWeapon(weapon)));
		}
	}
	
	/**
	 * Check whether this worm has proper weapons attached to it.
	 * 
	 * @return	True if and only if this worm can have each of its weapons as
	 * 			a weapon attached to it.
	 * 		  |	for each weapon in Weapon:
	 * 		  |		( if (this.hasAsWeapon(weapon))
	 * 		  |			then canHaveAsWeapon(weapon) )
	 */
	public boolean hasProperWeapons() { return true;}
	
	/**
	 * Add the given weapon to the list of weapons attached to this worm.
	 * 
	 * @param	weapon
	 * 			The weapon to be added.
	 * @post	This worm has the given weapon as one of its weapons.
	 * 		  |	new.hasAsWeapon(weapon)
	 * @throws	IllegalArgumentException
	 * 			This worm cannot have the given weapon as one if its weapons.
	 * 		  |	! canHaveAsWeapon(weapon)
	 */
	public void addAsWeapon(Weapon weapon) {
		if (! canHaveAsWeapon(weapon)) {
			throw new IllegalArgumentException();
		}
		weapons.add(weapon);
	}
	
	/**
	 * Remove the given weapon from the list of weapons attached to this worm.
	 * 
	 * @param	weapon
	 * 		  	The weapon to be removed.
	 * @post	This worm does not have the given weapon as on of its weapons.
	 * 		  |	! new.hasAsWeapon(weapon)
	 */
	public void removeAsWeapon(Weapon weapon) {
		weapons.remove(weapon);
	}
	
	/**
	 * List collecting references to weapons attached to this worm.
	 * 
	 * @invar	The list of weapons is effective.
	 * 		  |	weapons != null
	 * @invar	Each weapon in the list of weapons references a weapon that is
	 * 			an acceptable weapon for this worm.
	 * 		  |	for each weapon in Weapon:
	 * 		  |		canHaveAsWeapon(weapon)
	 */
	private final List<Weapon> weapons = new ArrayList<Weapon>();
	
	/**
	 * Return the active weapon of this worm.
	 */
	@Basic
	public Weapon getActiveWeapon() {
		return activeWeapon;
	}
	
	/**
	 * Select the next weapon in the list of weapons attached to this worm as
	 * the new active weapon of this worm.
	 * 
	 * @post	The new active weapon of this worm is the next weapon in the 
	 * 			list of weapons attached to this worm.
	 * 		  |	let
	 * 		  |		currentWeapon = getActiveWeapon();
	 *		  |		index = weapons.indexOf(currentWeapon);
	 *		  |		maxIndex = weapons.size();
	 *		  |	in
	 * 		  |		if (index + 1 >= maxIndex) 
	 * 		  |			then new.getActiveWeapon() == getWeaponAt(0)
	 * 		  |		else
	 * 		  |			new.getActiveWeapon() == getWeapontAt(index + 1)
	 */
	public void selectNextWeapon() {
		Weapon currentWeapon = getActiveWeapon();
		int index = weapons.indexOf(currentWeapon);
		int maxIndex = weapons.size();
		if (index + 1 >= maxIndex) {
			activeWeapon = weapons.get(0);
		} else {
			activeWeapon = weapons.get(index + 1);
		}
	}
	
	/**
	 * Variable referencing the active weapon of this worm.
	 */
	private Weapon activeWeapon;
	
	
	
	
	/**
	 * Return the program of this worm.
	 */
	@Basic
	public Program getProgram() {
		return program;
	}
	
	/**
	 * Set the program of this worm to the given program.
	 * 
	 * @param 	program
	 * 			The program to set.
	 */
	public void setProgram(Program program) {
		this.program = program;
	}
	
	/**
	 * Variable referencing the program of this worm.
	 */
	private Program program;
	
	
	
	
	//SHOOTING
	/**
	 * Fire the active weapon of this worm (i.e. add an active projectile to 
	 * this world).
	 * 
	 * @param 	propulsionYield
	 * 			The propulsion yield with which the active weapon of this worm
	 * 			is fired.
	 * @post	The new current action points of this worm is equal to the
	 * 			initial current action points of this worm decremented with the 
	 * 			action point cost of the active weapon of this worm.
	 * 		  |	new.getCurrentActionPoints() ==
	 * 		  |		this.getCurrentActionPoints()
	 * 		  |		- this.getActiveWeapon().getActionPointsCost()
	 * @post	If the resulting position of the fired projectile overlaps with 
	 * 			a worm in the world of this worm, the damage of the fired 
	 * 			projectile shall be	subtracted from the hit points of the worm 
	 * 			which is overlapped.
	 * 		  |	let
	 * 		  |		Worm hitWorm = getWorld().getOverlappingWorm(
	 *		  |			projectile.getPosition(), projectile.getRadius())
	 *		  |	in
	 * 		  |		if (getWorld().overlaps(projectile.getPosition(),
	 *		  |			projectile.getRadius()))
	 *		  |			then hitWorm.decreaseHitPoints(inflictedHitPoints)
	 * @post	The world of this worm shall have no effective projectile 
	 * 			attached to it.
	 * 		  |	new.getWorld().getProjectile() == null
	 * @effect	Create a new projectile just outside the radius of this worm in
	 * 			the direction this worm is facing and jump this new projectile.
	 * 		  |	let
	 * 		  |		Weapon weapon = getActiveWeapon();
	 *		  |		double force = weapon.getForce(propulsionYield);
	 *		  |		double R = weapon.getProjectileRadius();
	 *		  |		double direction = getDirection();
	 *		  |		double dx = R * Math.cos(direction);
	 *		  |		double dy = R * Math.sin(direction);
	 *		  |		Position initialPosition = getPosition().translate(dx, dy);
	 *		  |		double timestep = 0.0001;
	 *		  |	in
	 * 		  |		(Projectile projectile = new Projectile(initialPosition,
	 *		  |			direction, weapon.getProjecileMass(), 
	 *		  |			weapon.getDamage(), force))
	 *		  |	 && (projectile.jump(timestep))
	 */
	public void shoot(int propulsionYield) {
		if (! canShoot()) return;
			
		Weapon weapon = getActiveWeapon();
		double force = weapon.getForce(propulsionYield);
		double R = weapon.getProjectileRadius();
		double direction = getDirection();
		double dx = R * Math.cos(direction);
		double dy = R * Math.sin(direction);
		Position initialPosition = getPosition().translate(dx, dy);
		Projectile projectile = new Projectile(initialPosition, direction,
				weapon.getProjecileMass(), weapon.getDamage(), force);

		decreaseActionPoints(weapon.getActionPointsCost());
		getWorld().setProjectile(projectile);
	}
	
	/**
	 * Checks whether this worm can shoot its active weapon.
	 * 
	 * @return	True if and only if this worm is located at impassable terrain
	 * 			and this worm has at least the amount of action points needed to
	 * 			fire its active weapon.
	 * 		  |	result ==
	 * 		  |		 (  getWorld().isAdjacent(getPosition(), getRadius())
	 * 		  |		&& (getCurrentActionPoints() 
	 * 		  |			>= getActiveWeapon().getActionPointCost()) )
	 */
	public boolean canShoot() {
		return ( getWorld().isAdjacent(getPosition(), getRadius())
			  && (getCurrentActionPoints()
						>= getActiveWeapon().getActionPointsCost()));	
	}

}
