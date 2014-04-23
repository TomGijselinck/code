package worms.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Random;

import worms.exceptions.ModelException;


public class Facade implements IFacade {
	
	/**
	 * Create and add an empty team with the given name to the given world.
	 * 
	 * (For single-student groups that do not implement teams, this method should have no effect)
	 */
	public void addEmptyTeam(World world, String newName) {
		//do nothing
	}
	
	/**
	 * Create and add a new food ration to the given world.
	 * The food must be placed at a random adjacent location.
	 * 
	 * (For single-student groups that do not implement food, this method should have no effect)
	 */
	public void addNewFood(World world) {
		//do nothing
	}

	/**
	 * Create and add a new worm to the given world.
	 * The new worm must be placed at a random adjacent location.
	 * The new worm can have an arbitrary (but valid) radius and direction.
	 * The new worm may (but isn't required to) have joined a team.
	 */
	public void addNewWorm(World world) {
		try {
			world.addWorm();
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}

	/**
	 * Returns whether or not the given worm can fall down
	 */
	public boolean canFall(Worm worm) {
		return worm.canFall();
	}
	
	/**
	 * Returns whether or not the given worm is allowed to move.
	 */
	public boolean canMove(Worm worm) {
		//System.out
		//		.println("canMove() (" + worm.getName() + "@"
		//				+ worm.getPosition().toString() + ": "
		//				+ worm.canMove(worm.getDirection()));
		return worm.canMove(worm.getDirection());
	}
	
	/**
	 * Returns whether or not the given worm can turn by the given angle.
	 */
	public boolean canTurn(Worm worm, double angle) {
		return worm.canTurn(angle);
	}

	/**
	 * Create a new food ration that is positioned at the given location in the given world.
	 * 
	 * @param world
	 * The world in which to place the created food ration
	 * @param x
	 * The x-coordinate of the position of the new food ration (in meter)
	 * @param y
	 * The y-coordinate of the position of the new food ration (in meter)
	 * 
	 * (For single-student groups that do not implement food, this method should have no effect)
	 */
	public Food createFood(World world, double x, double y) {
		return new Food(); //not implemented
	}

	/**
	 * Creates a new world.
	 * 
	 * @param width The width of the world (in meter)
	 * 
	 * @param height The height of the world (in meter)
	 * 
	 * @param 	passableMap 
	 * 			A rectangular matrix indicating which parts of the terrain are 
	 * 			passable and impassable.
	 *  		This matrix is derived from the transparency of the pixels in 
	 *  		the image file of the terrain.
	 *  		passableMap[r][c] is true if the location at row r and column c 
	 *  		is passable, and false if that location is impassable.
	 *  		The elements in the first row (row 0) represent the pixels at 
	 *  		the top of the terrain (i.e., largest y-coordinates).
	 *  		The elements in the last row (row passableMap.length-1) 
	 *  		represent pixels at the bottom of the terrain (smallest 
	 *  		y-coordinates).
	 *  		The elements in the first column (column 0) represent the pixels
	 *  		at the left of the terrain (i.e., smallest x-coordinates).
	 *  		The elements in the last column (column passableMap[0].length-1)
	 *  		represent the pixels at the right of the terrain (i.e., largest 
	 *  		x-coordinates).     
	 * 
	 * @param 	random 
	 * 			A random number generator, seeded with the value obtained 
	 * 			from the command line or from GUIOptions, that can be used to 
	 * 			randomize aspects of the world in a repeatable way.
	 * 
	 * @return The world.
	 */
	public World createWorld(double width, double height,
			boolean[][] passableMap, Random random) {
		try {
			return new World(width, height, passableMap, random);
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}
	
	/**
	 * Create a new worm that is positioned at the given location in the given
	 * world, looks in the given direction, has the given radius and the given 
	 * name.
	 * 
	 * @param world
	 * The world in which to place the created worm  
	 * @param x
	 * The x-coordinate of the position of the new worm (in meter)
	 * @param y
	 * The y-coordinate of the position of the new worm (in meter)
	 * @param direction
	 * The direction of the new worm (in radians)
	 * @param radius 
	 * The radius of the new worm (in meter)
	 * @param name
	 * The name of the new worm
	 */
	public Worm createWorm(World world, double x, double y, double direction,
			double radius, String name) {
		try {
			if (direction < 0) direction += 2 * Math.PI;
			Worm worm = new Worm(new Position(x, y), direction, radius, name);
			world.addAsWorm(worm);
			return worm;
		}
		catch (Exception exc) {
			throw new ModelException(exc) ;
		}
	}

	/**
	 * Makes the given worm fall down until it rests on impassable terrain 
	 * again.
	 */
	public void fall(Worm worm) {
		try {
			worm.fall();
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}
	
	/**
	 * Returns the current number of action points of the given worm.
	 */
	public int getActionPoints(Worm worm) {
		return worm.getCurrentActionPoints();
	}

	/**
	 * Returns the active projectile in the world, or null if no active projectile exists.
	 */
	public Projectile getActiveProjectile(World world) {
		return world.getProjectile();
	}

	/**
	 * Returns the active worm in the given world (i.e., the worm whose turn it is).
	 */
	public Worm getCurrentWorm(World world) {
		return world.getCurrentWorm();
	}

	/**
	 * Returns all the food rations in the world
	 * 
	 * (For single-student groups that do not implement food, this method must always return an empty collection)
	 */
	public Collection<Food> getFood(World world) {
		return new HashSet<>();
	}

	/**
	 * Returns the current number of hit points of the given worm.
	 */
	public int getHitPoints(Worm worm) {
		return worm.getCurrentHitPoints();
	}

	/**
	 * Returns the location on the jump trajectory of the given projectile after a
	 * time t.
	 * 
	 * @return An array with two elements, with the first element being the
	 *         x-coordinate and the second element the y-coordinate
	 */
	public double[] getJumpStep(Projectile projectile, double t) {
		double[] location = new double[2];
		try {
			double F = projectile.getLaunchForce();
			Position position = projectile.jumpStep(t, projectile.jumpSpeed(F));
			location[0] = position.getX();
			location[1] = position.getY();
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
		return location;
	}
	
	/**
	 * Returns the location on the jump trajectory of the given worm after a
	 * time t.
	 * 
	 * @return An array with two elements, with the first element being the
	 *         x-coordinate and the second element the y-coordinate
	 */
	public double[] getJumpStep(Worm worm, double t) {
		double[] location = new double[2];
		try {
			double F = worm.getLaunchForce();
			Position position = worm.jumpStep(t, worm.jumpSpeed(F));
			location[0] = position.getX();
			location[1] = position.getY();
			//System.out.println("jumpstep (" + worm.getName() + "): "
			//		+ position.toString());
		} catch (Exception exc) {
			throw new ModelException(exc);
		}
		return location;
	}

	/**
	 * Determine the time that the given projectile can jump until it hits the
	 * terrain, hits a worm, or leaves the world.
	 * The time should be determined using the given elementary time interval.
	 * 
	 * @param projectile The projectile for which to calculate the jump time.
	 * 
	 * @param timeStep An elementary time interval during which you may assume
	 *                 that the projectile will not completely move through a 
	 *                 piece of impassable terrain.
	 *                 
	 * @return The time duration of the projectile's jump.
	 */
	public double getJumpTime(Projectile projectile, double timeStep) {
		return projectile.jumpTime(timeStep);
	}

	/**
	 * Determine the time that the given worm can jump until it hits the terrain
	 * or leaves the world.
	 * The time should be determined using the given elementary time interval.
	 * 
	 * @param worm The worm for which to calculate the jump time.
	 * 
	 * @param timeStep An elementary time interval during which you may assume
	 *                 that the worm will not completely move through a piece of 
	 *                 impassable terrain.
	 *                 
	 * @return The time duration of the worm's jump.
	 */
	public double getJumpTime(Worm worm, double timeStep) {
		try {
		//System.out.println("getting jumpTime...");
		double time = worm.jumpTime(timeStep);
		//System.out.println("jumptime for (" + worm.getName()
		//		+ "): " + time);
		return time;
		}
		catch (Exception exc) {
			throw new ModelException(exc) ;
		}
	}

	/**
	 * Returns the mass of the given worm.
	 */
	public double getMass(Worm worm) {
		return worm.getMass();
	}

	/**
	 * Returns the maximum number of action points of the given worm.
	 */
	public int getMaxActionPoints(Worm worm) {
		return worm.getActionPointsMaximum();
	}

	/**
	 * Returns the maximum number of hit points of the given worm.
	 */
	public int getMaxHitPoints(Worm worm) {
		return worm.getHitPointsMaximum();
	}

	/**
	 * Returns the minimal radius of the given worm.
	 */
	public double getMinimalRadius(Worm worm) {
		return worm.getLowerRadiusBound();
	}

	/**
	 * Returns the name the given worm.
	 */
	public String getName(Worm worm) {
		return worm.getName();
	}

	/**
	 * Returns the current orientation of the given worm (in radians).
	 */
	public double getOrientation(Worm worm) {
		return worm.getDirection();
	}

	/**
	 * Returns the radius of the given food ration
	 * 
	 * (For single-student groups that do not implement food, this method may return any value)
	 */
	public double getRadius(Food food) {
		return 0;
	}

	/**
	 * Returns the radius of the given projectile.
	 */
	public double getRadius(Projectile projectile) {
		return projectile.getRadius();
	}

	/**
	 * Returns the radius of the given worm.
	 */
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}

	
	/**
	 * Returns the name of the weapon that is currently active for the given worm,
	 * or null if no weapon is active.
	 */
	public String getSelectedWeapon(Worm worm) {
		return worm.getActiveWeapon().getName();
	}

	/**
	 * Returns the name of the team of the given worm, or returns null if this
	 * worm is not part of a team.
	 * 
	 * (For single-student groups that do not implement teams, this method should always return null)
	 */
	public String getTeamName(Worm worm) {
		return null;
	}

	/**
	 * Returns the name of a single worm if that worm is the winner, or the name
	 * of a team if that team is the winner. This method should null if there is no winner.
	 * 
	 * (For single-student groups that do not implement teams, this method should always return the name of the winning worm, or null if there is no winner)
	 */
	public String getWinner(World world) {
		return world.getWinningWorm().getName();
	}

	/**
	 * Returns all the worms in the given world
	 */
	public Collection<Worm> getWorms(World world) {
		return world.getAllWorms();
	}

	/**
	 * Returns the x-coordinate of the given food ration
	 * 
	 * (For single-student groups that do not implement food, this method may return any value)
	 */
	public double getX(Food food) {
		return 0;
	}

	/**
	 * Returns the x-coordinate of the given projectile.
	 */
	public double getX(Projectile projectile) {
		return projectile.getPosition().getX();
	}

	/**
	 * Returns the x-coordinate of the current location of the given worm.
	 */
	public double getX(Worm worm) {
		return worm.getPosition().getX();
	}

	/**
	 * Returns the y-coordinate of the given food ration
	 * 
	 * (For single-student groups that do not implement food, this method may return any value)
	 */
	public double getY(Food food) {
		return 0;
	}

	/**
	 * Returns the y-coordinate of the given projectile.
	 */
	public double getY(Projectile projectile) {
		return projectile.getPosition().getY();
	}

	/**
	 * Returns the y-coordinate of the current location of the given worm.
	 */
	public double getY(Worm worm) {
		return worm.getPosition().getY();
	}

	/**
	 * Returns whether or not the given food ration is alive (active), i.e., not eaten.
	 * 
	 * (For single-student groups that do not implement food, this method should always return false)
	 */
	public boolean isActive(Food food) {
		return false;
	}
	
	/**
	 * Returns whether the given projectile is still alive (active).
	 */
	public boolean isActive(Projectile projectile) {
		return (! projectile.isTerminated());
	}

	/**
	 * Checks whether the given circular region of the given world,
	 * defined by the given center coordinates and radius,
	 * is passable and adjacent to impassable terrain. 
	 * 
	 * @param world The world in which to check adjacency
	 * @param x The x-coordinate of the center of the circle to check  
	 * @param y The y-coordinate of the center of the circle to check
	 * @param radius The radius of the circle to check
	 * 
	 * @return True if the given region is passable and adjacent to impassable terrain, false otherwise.
	 */
	public boolean isAdjacent(World world, double x, double y, double radius) {
		Position position = new Position(x, y);
		return world.isAdjacent(position, radius);
	}

	/**
	 * Returns whether the given worm is alive
	 */
	public boolean isAlive(Worm worm) {
		return (! worm.isTerminated());
	}

	/**
	 * Returns whether the game in the given world has finished.
	 */
	public boolean isGameFinished(World world) {
		return world.isGameFinished();
	}

	/**
	 * Checks whether the given circular region of the given world,
	 * defined by the given center coordinates and radius,
	 * is impassable. 
	 * 
	 * @param world The world in which to check impassability 
	 * @param x The x-coordinate of the center of the circle to check  
	 * @param y The y-coordinate of the center of the circle to check
	 * @param radius The radius of the circle to check
	 * 
	 * @return True if the given region is impassable, false otherwise.
	 */
	public boolean isImpassable(World world, double x, double y, double radius) {
		Position position = new Position(x, y);
		return world.isImpassableForObject(position, radius);
	}

	/**
	 * Make the given projectile jump to its new location.
	 * The new location should be determined using the given elementary time interval. 
	 *  
	 * @param projectile The projectile that needs to jump
	 * 
	 * @param timeStep An elementary time interval during which you may assume
	 *                 that the projectile will not completely move through a piece of impassable terrain.
	 */
	public void jump(Projectile projectile, double timeStep) {
		try {
			projectile.jump(timeStep);
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}

	/**
	 * Make the given worm jump to its new location.
	 * The new location should be determined using the given elementary time interval. 
	 *  
	 * @param worm The worm that needs to jump
	 * 
	 * @param timeStep An elementary time interval during which you may assume
	 *                 that the worm will not completely move through a piece of impassable terrain.
	 */
	public void jump(Worm worm, double timeStep) {
		try {
			worm.jump(timeStep);
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}
	
	/**
	 * Moves the given worm according to the rules in the assignment.
	 */
	public void move(Worm worm) {
		try {
			worm.move(1);
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}

	/**
	 * Renames the given worm.
	 */
	public void rename(Worm worm, String newName) {
		try {
			worm.setName(newName);
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}

	/**
	 * Activates the next weapon for the given worm
	 */
	public void selectNextWeapon(Worm worm) {
		worm.selectNextWeapon();
	}

	/**
	 * Sets the radius of the given worm to the given value.
	 */
	public void setRadius(Worm worm, double newRadius) {
		try {
			worm.setRadius(newRadius);
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}

	/**
	 * Makes the given worm shoot its active weapon with the given propulsion yield.
	 */
	public void shoot(Worm worm, int yield) {
		try {
			worm.shoot(yield);
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}

	/**
	 * Starts a game in the given world.
	 */
	public void startGame(World world) {
		//System.out.println("start game");
		world.startGame();
	}

	/**
	 * Starts the next turn in the given world
	 */
	public void startNextTurn(World world) {
		world.startNextTurn();
	}

	/**
	 * Turns the given worm by the given angle.
	 */
	public void turn(Worm worm, double angle) {
		worm.turn(angle);
	}

}
