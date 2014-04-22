package worms.model;

import static org.junit.Assert.*;

import org.junit.*;
import java.util.Random;

import worms.exceptions.IllegalJumpException;
import worms.exceptions.IllegalNameException;
import worms.exceptions.IllegalStepException;
import static worms.util.Util.*;

/**
 * A class collecting tests for the class of worms.
 * 
 * @version 1.1
 * @author Tom Gijselinck
 * 
 */

public class WormTest {

	/**
	 * Variable referencing a worm with position (3.5, 1.5), direction 0, radius
	 * 1 and name "Standard".
	 */
	private static Worm standardWorm;
	
	/**
	 * Variable referencing a worm with position (0.5, 1), direction 0, radius 
	 * 0.5 and name "MoveWorm".
	 */
	private static Worm moveableWorm;
	
	/**
	 * Variable referencing a worm with position (0.5, 1), direction 0, radius 
	 * 0.5 and name "FalloutWorm".
	 */
	private static Worm fallOutWorm;
	
	/**
	 * Variable referencing a worm with position (2, 4), direction 0, radius 
	 * 0.5, and name "FallWorm".
	 */
	private static Worm fallableWorm;
	
	/**
	 * Variable referencing a world with width and height of 5 and passableMap
	 * as the passable map.
	 */
	private static World world;
	
	private static World world2;
	
	// 10x10 pixels
		//     0 1 2 3 4 5 6 7 8 9
		//    --------------------
		//  1| . . X . . . . . . .
		//  2| . . X . . . . . . .
		//  3| X X X . . . . . . .
		//  4| . X X X X X . . . .
		//  5| . . . . . . . . . .
		//  6| . . . . . . . . . .
		//  7| . . . . . . O . . .
		//  8| . . . . . . . . . .
		//  9| . . . . . . . . . .
		// 10| X X X X X X X X X X
		private boolean[][] passableMap = new boolean[][] {
				{true, 	true, 	false, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	false, 	true,	true,	true,	true,	true,	true,	true},
				{false, false, 	false, 	true,	true,	true,	true,	true,	true,	true},
				{true, false, 	false, 	false,	false,	false,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{false, false, 	false, 	false,	false,	false,	false,	false,	false,	false}
		};
		
		// 10x10 pixels
		//     0 1 2 3 4 5 6 7 8 9
		//    --------------------
		//  1| . . . . . . . . . .
		//  2| . . . . . . . . . .
		//  3| . . . . . . . . . .
		//  4| . . . . . . . . . .
		//  5| . . . . . . . . . .
		//  6| . . . . . . . . . .
		//  7| . . . . . . . . . .
		//  8| . . . . . . . . . .
		//  9| . . . . . . . . . .
		// 10| X X X . . . . X X X
		private boolean[][] passableMap2 = new boolean[][] {
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
				{false, false, 	false, 	true,	true,	true,	true,	false,	false,	false}
		};
		
		private static double timeStep = 0.0001;

	/**
	 * Set up a mutable test fixture.
	 * 
	 * @post The variable standardWorm references a new worm with default
	 *       variable values.
	 */
	@Before
	public void setUpMutableFixture() throws Exception {
		standardWorm = new Worm(new Position(3.5, 1.5), 0, 1, "Standard");
		moveableWorm = new Worm(new Position(0.5, 1), 0, 0.5, "MoveWorm");
		fallableWorm = new Worm(new Position(2, 4), 0, 0.5, "FallWorm");
		fallOutWorm = new Worm(new Position(0.5, 1), 0, 0.5, "FalloutWorm");
		world = new World(5, 5, passableMap, new Random());
		world2 = new World(5, 5, passableMap2, new Random());
		world.addAsWorm(standardWorm);
		world.addAsWorm(moveableWorm);
		world.addAsWorm(fallableWorm);
		world2.addAsWorm(fallOutWorm);
	}

	@Test
	public void extendedConstructor_LegalCase() throws Exception {
		Worm theWorm = new Worm(new Position(3, 5), 70, 11, "The worm");
		assertEquals("The worm", theWorm.getName());
		assertEquals(theWorm.getActionPointsMaximum(),
				theWorm.getCurrentActionPoints());
		assertEquals(theWorm.getHitPointsMaximum(), 
				theWorm.getCurrentHitPoints());
		assertTrue(theWorm.hasAsWeapon(new Rifle()));
		assertTrue(theWorm.hasAsWeapon(new Bazooka()));
	}

	@Test(expected = IllegalNameException.class)
	public void extendedConstructor_InvalidName() throws Exception {
		new Worm(new Position(0, 0), 0, 1, "W");
	}
	
	@Test
	public void canMove_TrueCase() {
		assertTrue(standardWorm.canMove(0));
	}

	@Test
	public void canMove_FalseCase() {
		standardWorm.decreaseActionPoints(5000);
		assertFalse(standardWorm.canMove(0));
	}

	@Test
	public void move_Horizontal() {
		int initialActionPoints = moveableWorm.getCurrentActionPoints();
		moveableWorm.move(3);
		assertTrue(fuzzyEquals(2, moveableWorm.getPosition().getX()));
		assertTrue(fuzzyEquals(1, moveableWorm.getPosition().getY()));
		assertEquals(initialActionPoints - 12,
				moveableWorm.getCurrentActionPoints());
	}

	@Test
	public void move_UpwardOrientation() {
		fallableWorm.turn(Math.PI / 2);
		fallableWorm.move(1);
		assertTrue(fuzzyEquals(2, fallableWorm
				.getPosition().getX()));
		assertTrue(fuzzyEquals(4.5, fallableWorm
				.getPosition().getY()));
	}

	@Test
	public void move_MultipleIntervals() {
		moveableWorm.move(2);
		moveableWorm.move(1);
		assertTrue(fuzzyEquals(2, moveableWorm.getPosition().getX()));
		assertTrue(fuzzyEquals(1, moveableWorm.getPosition().getY()));
	}

	@Test(expected = IllegalStepException.class)
	public void move_IllegalCase() throws Exception {
		standardWorm.decreaseActionPoints(5000);
		standardWorm.move(1);
	}
	
	@Test
	public void move_FallOutOfMap() {
		int steps = 4;
		for (int i = 0; i<steps; i++) {
			fallOutWorm.move(1);		
		}
	}
	
	@Test
	public void  fall_SingleCase() {
		fallableWorm.move(4);
		assertTrue(fuzzyEquals(1.0, fallableWorm.getPosition().getY(), 0.05));
	}

	@Test
	public void jump_SingleCase() {
		moveableWorm.turn(0.3);
		moveableWorm.jump(timeStep);
		assertEquals(0, moveableWorm.getCurrentActionPoints());
	}

	@Test
	public void jump_NotBeyondWormRadius() {
		Position initialPosition = new Position(1.5, 1.75);
		Worm worm = new Worm(initialPosition, 1.4, 1.2, "BigWorm");
		world.addAsWorm(worm);
		worm.jump(timeStep);
		assertTrue(worm.getPosition().equals(initialPosition));
	}
	
	@Test
	public void jump_OutsideWorldBorders() {
		Worm worm = new Worm(new Position(4, 1), 0.7, 0.5, "Outside Borders");
		world.addAsWorm(worm);
		worm.jump(timeStep);
		assertTrue(worm.isTerminated());
	}
	
	@Test(expected = IllegalJumpException.class)
	public void jump_IllegalJump() throws Exception {
		standardWorm.decreaseActionPoints(5000);
		standardWorm.jump(timeStep);
	}

	@Test
	public void canJump_TrueCase() {
		assertTrue(standardWorm.canJump());
	}

	@Test
	public void canJump_ZeroActionPoints() {
		standardWorm.decreaseActionPoints(5000);
		assertFalse(standardWorm.canJump());
	}
	
	@Test
	public void jumpTime_SingleCase() {
		moveableWorm.turn(0.3);
		double time = standardWorm.jumpTime(timeStep);
	}

	@Test
	public void jumpStep_LegalCase() {
		moveableWorm.turn(0.7);
		double g = Worm.getGravityOfEarth();
		double F = moveableWorm.getLaunchForce();
		double initialSpeed = moveableWorm.jumpSpeed(F);
		Double direction = moveableWorm.getDirection();
		Position resultPosition = moveableWorm.jumpStep(1, initialSpeed);
		Position expectedPosition = moveableWorm.getPosition().translate(
				initialSpeed * Math.cos(direction),
				initialSpeed * Math.sin(direction) - 0.5 * g * Math.pow(1, 2));
		assertTrue(fuzzyEquals(expectedPosition.getX(), resultPosition.getX()));
		assertTrue(fuzzyEquals(expectedPosition.getY(), resultPosition.getY()));
	}

	@Test
	public void jumpSpeed_SingleCase() {
		moveableWorm.turn(0.7);
		double F = standardWorm.getLaunchForce();
		assertTrue(fuzzyEquals(F / moveableWorm.getMass() * 0.5,
				moveableWorm.jumpSpeed(F)));
	}

	@Test
	public void canTurn_TrueCase() {
		standardWorm.canTurn(standardWorm.getAngleRange() / 2);
	}

	@Test
	public void canTurn_FalseCaseActionPoints() {
		standardWorm.decreaseActionPoints(5000);
		standardWorm.canTurn(standardWorm.getAngleRange() / 2);
	}

	@Test
	public void canTurn_FalseCaseDirection() {
		standardWorm.canTurn(2 * standardWorm.getAngleRange());
	}

	@Test
	public void turn_InAngleRange() {
		int initialAP = standardWorm.getCurrentActionPoints();
		standardWorm.turn(standardWorm.getAngleRange() / 2);
		assertTrue(fuzzyEquals(standardWorm.getAngleRange() / 2,
				standardWorm.getDirection()));
		assertTrue(fuzzyEquals((initialAP - 30),
				standardWorm.getCurrentActionPoints()));

	}
	
	@Test
	public void turn_NegativeAngle() {
		standardWorm.turn(-0.7);
		assertTrue(fuzzyEquals(2 * Math.PI - 0.7, standardWorm.getDirection()));
	}

	@Test
	public void turn_OutsideAngleRange() {
		int initialAP = standardWorm.getCurrentActionPoints();
		standardWorm.turn(standardWorm.getAngleRange() * 3 / 4);
		standardWorm.turn(standardWorm.getAngleRange() * 1 / 2);
		assertTrue(fuzzyEquals(standardWorm.getAngleRange() * 1 / 4,
				standardWorm.getDirection()));
		assertTrue(fuzzyEquals((initialAP - 75),
				standardWorm.getCurrentActionPoints()));
	}

	@Test
	public void canHaveAsRadius_LegalCase() {
		assertTrue(standardWorm.canHaveAsRadius(standardWorm
				.getLowerRadiusBound() + 1));
	}

	@Test
	public void canHaveAsRadius_NaN() {
		assertFalse(standardWorm.canHaveAsRadius(Double.NaN));
	}

	@Test
	public void canHaveAsRadius_UnderLowerRadiusBound() {
		assertFalse(standardWorm.canHaveAsRadius(standardWorm
				.getLowerRadiusBound() - 1));
	}

	@Test
	public void canHaveAsRadius_PostiveInfinity() {
		assertFalse(standardWorm.canHaveAsRadius(Double.POSITIVE_INFINITY));
	}

	@Test
	public void setRadius_LegalCase() {
		standardWorm.setRadius(standardWorm.getLowerRadiusBound() + 1);
		assertTrue(fuzzyEquals(standardWorm.getLowerRadiusBound() + 1,
				standardWorm.getRadius()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void setRadius_InvalidRadius() throws Exception {
		standardWorm.setRadius(Double.POSITIVE_INFINITY);
	}

	@Test
	public void decreaseActionPoints_NormalAmount() {
		standardWorm.decreaseActionPoints(50);
		assertEquals(standardWorm.getActionPointsMaximum() - 50,
				standardWorm.getCurrentActionPoints());
	}

	@Test
	public void decreaseActionPoints_NegativeAmount() {
		standardWorm.decreaseActionPoints(-10);
		assertEquals(standardWorm.getActionPointsMaximum(),
				standardWorm.getCurrentActionPoints());
	}
	
	@Test
	public void decreaseActionPoints_AmountGreaterThanCurrentAP() {
		int currentAP = standardWorm.getCurrentActionPoints();
		standardWorm.decreaseActionPoints(currentAP + 1);
		assertEquals(0, standardWorm.getCurrentActionPoints());
	}

	@Test
	public void setName_LegalCase() {
		standardWorm.setName("New name");
		assertEquals("New name", standardWorm.getName());
	}

	@Test(expected = IllegalNameException.class)
	public void setName_InvalidName() throws Exception {
		standardWorm.setName("Inval1d nam$");
	}

	@Test
	public void isValidName_TrueCase() {
		assertTrue(Worm.isValidName("James o'Hara 007"));
	}
	
	@Test
	public void isValidName_SingleCharacterName() {
		assertFalse(Worm.isValidName("M"));
	}

	@Test
	public void isValidName_LowerCaseCharacterName() {
		assertFalse(Worm.isValidName("lower case"));
	}

	@Test
	public void isValidName_IllegalCharacterName() {
		assertFalse(Worm.isValidName("Illegal$ character"));
	}
	
	@Test
	public void canHaveAsWorld_NonEffectiveWorld() {
		assertTrue(standardWorm.canHaveAsWorld(null));
	}
	
	@Test
	public void canHaveAsWorld_EffectiveWorld() {
		assertTrue(standardWorm.canHaveAsWorld(world));
	}
	
	@Test
	public void hasProperWorld_SingleCase() {
		assertTrue(standardWorm.hasProperWorld());
	}
	
	@Test
	public void hasAsWeapon_SingleCase() {
		assertTrue(standardWorm.hasAsWeapon(new Rifle()));
	}
	
	@Test
	public void canHaveAsWeapon_TrueCase() {
		standardWorm.removeAsWeapon(new Rifle());
		assertTrue(standardWorm.canHaveAsWeapon(new Rifle()));
	}
	
	@Test
	public void canHaveAsWeapon_NonEffectiveWeapon() {
		assertFalse(standardWorm.canHaveAsWeapon(null));
	}
	
	@Test
	public void canHaveAsWeapon_TerminatedWorm() {
		standardWorm.terminate();
		assertFalse(standardWorm.canHaveAsWeapon(new Rifle()));
	}
	
	@Test
	public void canHaveAsWeapon_HasWeaponAlready() {
		assertFalse(standardWorm.canHaveAsWeapon(new Rifle()));
	}
	
	@Test
	public void selectNextWeapon_SingleCase() {
		assertTrue(standardWorm.getActiveWeapon().equals(new Rifle()));
		standardWorm.selectNextWeapon();
		assertTrue(standardWorm.getActiveWeapon().equals(new Bazooka()));
	}
	
	@Test
	public void shoot_HitNothing() {
		fallableWorm.turn(0.5);
		int intialAP = fallableWorm.getCurrentActionPoints();
		int consumedAP = fallableWorm.getActiveWeapon().getActionPointsCost();
		fallableWorm.shoot(100);
		assertEquals(intialAP - consumedAP,
				fallableWorm.getCurrentActionPoints());
	}

	@Test
	public void shoot_HitWorm() {
		int initialHP = standardWorm.getCurrentHitPoints();
		int inflictedHP = moveableWorm.getActiveWeapon().getDamage();
		moveableWorm.turn(0.3);
		moveableWorm.shoot(100);
		moveableWorm.getWorld().getProjectile().jump(timeStep);
		assertEquals(initialHP - inflictedHP,
				standardWorm.getCurrentHitPoints());
	}

	@Test
	public void shoot_HitImpassableTerrain() {
		moveableWorm.turn(1.55);
		moveableWorm.shoot(100);
		moveableWorm.getWorld().getProjectile().jump(timeStep);
		assertTrue(moveableWorm.getWorld().getProjectile() == null);
	}

}
