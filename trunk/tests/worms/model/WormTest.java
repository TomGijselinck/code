package worms.model;

import static org.junit.Assert.*;

import org.junit.*;

import worms.exceptions.IllegalJumpException;
import worms.exceptions.IllegalNameException;
import worms.exceptions.IllegalRadiusException;
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
	 * Variable referencing a worm with position (2, 4), direction 0, radius 
	 * 0.5, and name "FallWorm".
	 */
	private static Worm fallableWorm;

	/**
	 * Variable referencing a worm with position (2, 4), upward direction of 
	 * Pi/2 radians, radius 0.5 and name "UpwardWorm".
	 */
	private static Worm wormUpwardDirection;

	/**
	 * Variable referencing a worm with downward direction of 7/4*Pi radians.
	 */
	private static Worm wormDownwardDirection;

	/**
	 * Variable referencing a worm with radius of 2.
	 */
	private static Worm wormRadius2;
	
	/**
	 * Variable referencing a world with width and height of 5 and passableMap
	 * as the passable map.
	 */
	private static World world;
	
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

	/**
	 * Set up a mutable test fixture.
	 * 
	 * @post The variable standardWorm references a new worm with default
	 *       variable values.
	 */
	@Before
	public void setUpMutableFixture() throws Exception {
		standardWorm = new Worm(new Position(3.5, 1.5), 0, 1, "Standard");
		wormUpwardDirection = new Worm(new Position(2, 4), Math.PI / 2, 0.5,
				"UpwardWorm");
		wormDownwardDirection = new Worm(new Position(0, 0), Math.PI * 7 / 4,
				1, "Downward direction");
		moveableWorm = new Worm(new Position(0.5, 1), 0, 0.5, "MoveWorm");
		fallableWorm = new Worm(new Position(2, 4), 0, 0.5, "FallWorm");
		world = new World(5, 5, passableMap);
		world.addAsWorm(standardWorm);
		world.addAsWorm(moveableWorm);
		world.addAsWorm(wormUpwardDirection);
		world.addAsWorm(fallableWorm);
	}

	/**
	 * Set up an immutable test fixture.
	 * 
	 * @post The variable wormRadius2 references a new worm with radius 2.
	 */
	@BeforeClass
	public static void SetUpImmutableFixture() throws Exception {
		wormRadius2 = new Worm(new Position(0, 0), 0, 2, "Radius of two");
	}

	@Test
	public void extendedConstructor_LegalCase() throws Exception {
		Worm theWorm = new Worm(new Position(3, 5), 70, 11, "The worm");
		assertTrue(theWorm.getPosition().equals(new Position(3, 5)));
		assertTrue(fuzzyEquals(70, theWorm.getDirection()));
		assertTrue(fuzzyEquals(11, theWorm.getRadius()));
		assertEquals("The worm", theWorm.getName());
		assertEquals(theWorm.getActionPointsMaximum(),
				theWorm.getCurrentActionPoints());
		assertEquals(theWorm.getHitPointsMaximum(), 
				theWorm.getCurrentHitPoints());
		assertTrue(theWorm.hasAsWeapon(Weapon.RIFLE));
		assertTrue(theWorm.hasAsWeapon(Weapon.BAZOOKA));
		assertFalse(theWorm.isTerminated());
	}

	@Test(expected = IllegalRadiusException.class)
	public void extendedConstructor_InvalidRadius() throws Exception {
		new Worm(new Position(0, 0), 0, Double.POSITIVE_INFINITY,
				"Infinite radius");
	}

	@Test(expected = IllegalNameException.class)
	public void extendedConstructor_InvalidName() throws Exception {
		new Worm(new Position(0, 0), 0, 1, "W");
	}

	@Test
	public void hasProperPosition_TrueCase() {
		assertTrue(standardWorm.hasProperPosition());
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
		wormUpwardDirection.move(1);
		assertTrue(fuzzyEquals(2, wormUpwardDirection
				.getPosition().getX()));
		assertTrue(fuzzyEquals(4.5, wormUpwardDirection
				.getPosition().getY()));
	}

	@Test
	public void move_MultipleIntervals() {
		moveableWorm.move(2);
		moveableWorm.move(1);
		assertTrue(fuzzyEquals(2, moveableWorm.getPosition().getX()));
		assertTrue(fuzzyEquals(1, moveableWorm.getPosition().getY()));
	}
//TODO: work out exception
//	@Test(expected = IllegalStepsException.class)
//	public void move_IllegalCase() throws Exception {
//		standardWorm.move(10000);
//	}
	
	@Test
	public void  fall_SingleCase() {
		fallableWorm.move(4);
		assertTrue(fuzzyEquals(1.05, fallableWorm.getPosition().getY(), 0.01));
	}

	@Test
	public void jump_SingleCase() {
		Position positionBefore = new Position(wormUpwardDirection
				.getPosition().getX(), wormUpwardDirection.getPosition().getY());
		double jumpdistance = wormUpwardDirection.jumpDistance();
		wormUpwardDirection.jump();
		assertTrue(fuzzyEquals(positionBefore.getX() + jumpdistance,
				wormUpwardDirection.getPosition().getX()));
		assertTrue(fuzzyEquals(positionBefore.getY(), wormUpwardDirection
				.getPosition().getY()));
		assertEquals(0, wormUpwardDirection.getCurrentActionPoints());
	}

	@Test(expected = IllegalJumpException.class)
	public void jump_IllegalJump() throws Exception {
		Worm worm = new Worm(new Position(0, 0),
				Worm.getUpperAngleBound() / 2 + 1, 1, "Illegal Jumper");
		worm.jump();
	}

	@Test(expected = IllegalJumpException.class)
	public void jump_SecondJump() throws Exception {
		wormUpwardDirection.jump();
		wormUpwardDirection.jump();
	}

	@Test
	public void canJump_TrueCase() {
		assertTrue(standardWorm.canJump());
	}

	@Test
	public void canJump_BelowLowerAngleBound() {
		Worm worm = new Worm(new Position(0, 0), Worm.getLowerAngleBound() - 1,
				1, "Worm");
		assertFalse(worm.canJump());
	}

	@Test
	public void canJump_AboveUpperAngleBound() {
		Worm worm = new Worm(new Position(0, 0), Worm.getUpperAngleBound() + 1,
				1, "Worm");
		assertFalse(worm.canJump());
	}

	@Test
	public void canJump_ZeroActionPoints() {
		standardWorm.decreaseActionPoints(5000);
		assertFalse(standardWorm.canJump());
	}

	@Test
	public void jumpTime_SingleCase() {
		assertTrue(fuzzyEquals(
				wormUpwardDirection.jumpDistance()
						/ (wormUpwardDirection.jumpSpeed() * Math.cos(wormUpwardDirection
								.getDirection())),
				wormUpwardDirection.jumpTime()));
	}

	@Test
	public void jumpStep_LegalCase() {
		double g = Worm.getGravityOfEarth();
		Position resultPosition = wormUpwardDirection.jumpStep(1);
		Position expectedPosition = wormUpwardDirection.getPosition()
				.translate(
						wormUpwardDirection.jumpSpeed()
								* Math.cos(wormUpwardDirection.getDirection()),
						wormUpwardDirection.jumpSpeed()
								* Math.sin(wormUpwardDirection.getDirection())
								- 0.5 * g * Math.pow(1, 2));
		assertTrue(fuzzyEquals(expectedPosition.getX(), resultPosition.getX()));
		assertTrue(fuzzyEquals(expectedPosition.getY(), resultPosition.getY()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void jumpStep_IllegalTimeInterval() throws Exception {
		wormUpwardDirection.jumpStep(2);
	}

	@Test
	public void jumpSpeed_SingleCase() {
		double g = Worm.getGravityOfEarth();
		double F = 5 * wormUpwardDirection.getCurrentActionPoints()
				+ wormUpwardDirection.getMass() * g;
		assertTrue(fuzzyEquals(F / wormUpwardDirection.getMass() * 0.5,
				wormUpwardDirection.jumpSpeed()));
	}

	@Test
	public void jumpDistance_SingleCase() {
		double g = Worm.getGravityOfEarth();
		assertTrue(fuzzyEquals(Math.pow(wormUpwardDirection.jumpSpeed(), 2)
				* Math.sin(2 * wormUpwardDirection.getDirection()) / g,
				wormUpwardDirection.jumpDistance()));
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
	public void jump_AfterTurningUpward() {
		Position positionBefore = new Position(wormDownwardDirection
				.getPosition().getX(), wormDownwardDirection.getPosition()
				.getY());
		wormDownwardDirection.turn(Math.PI / 2);
		assertTrue(fuzzyEquals(Math.PI / 4,
				wormDownwardDirection.getDirection()));
		double jumpdistance = wormDownwardDirection.jumpDistance();
		wormDownwardDirection.jump();
		assertTrue(fuzzyEquals(positionBefore.getX() + jumpdistance,
				wormDownwardDirection.getPosition().getX()));
		assertTrue(fuzzyEquals(positionBefore.getY(), wormDownwardDirection
				.getPosition().getY()));
		assertEquals(0, wormDownwardDirection.getCurrentActionPoints());
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

	@Test(expected = IllegalRadiusException.class)
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
		assertTrue(standardWorm.hasAsWeapon(Weapon.RIFLE));
	}
	
	@Test
	public void canHaveAsWeapon_TrueCase() {
		standardWorm.removeAsWeapon(Weapon.RIFLE);
		assertTrue(standardWorm.canHaveAsWeapon(Weapon.RIFLE));
	}
	
	@Test
	public void canHaveAsWeapon_NonEffectiveWeapon() {
		assertFalse(standardWorm.canHaveAsWeapon(null));
	}
	
	@Test
	public void canHaveAsWeapon_TerminatedWorm() {
		standardWorm.terminate();
		assertFalse(standardWorm.canHaveAsWeapon(Weapon.RIFLE));
	}
	
	@Test
	public void canHaveAsWeapon_HasWeaponAlready() {
		assertFalse(standardWorm.canHaveAsWeapon(Weapon.RIFLE));
	}

}
