package worms.model;

import static org.junit.Assert.*;

import org.junit.*;

import static worms.util.Util.*;

/**
 * A class collecting tests for the class of worms.
 * 
 * @version 1.0
 * @author Tom Gijselinck
 * 
 */

public class WormTest {

	/**
	 * Variable referencing a worm with ordinary variable values.
	 */
	private static Worm standardWorm;

	/**
	 * Variable referencing a worm with direction of Pi/4 radians.
	 */
	private static Worm wormDirectionQuarterPi;

	/**
	 * Variable referencing a worm with radius of 2.
	 */
	private static Worm wormRadius2;

	/**
	 * Set up a mutable test fixture.
	 * 
	 * @post The variable standardWorm references a new worm with default
	 *       variable values.
	 */
	@Before
	public void setUpMutableFixture() throws Exception {
		standardWorm = new Worm(new Position(0, 0), 0, 1, "Standard");
		wormDirectionQuarterPi = new Worm(new Position(0, 0), Math.PI / 4, 1,
				"Quarter pi");
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
		assertTrue(fuzzyEquals(3, theWorm.getPosition().getX()));
		assertTrue(fuzzyEquals(5, theWorm.getPosition().getY()));
		assertTrue(fuzzyEquals(70, theWorm.getDirection()));
		assertTrue(fuzzyEquals(11, theWorm.getRadius()));
		assertEquals(theWorm.getActionPointsMaximum(),
				theWorm.getCurrentActionPoints());
		assertEquals("The worm", theWorm.getName());
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
	public void shortConstructor_SingleCase() {
		Worm theWorm = new Worm("The worm");
		assertEquals("The worm", theWorm.getName());
		assertTrue(fuzzyEquals(0, theWorm.getPosition().getX()));
		assertTrue(fuzzyEquals(0, theWorm.getPosition().getY()));
		assertTrue(fuzzyEquals(0, theWorm.getDirection()));
		assertTrue(fuzzyEquals(standardWorm.getLowerRadiusBound(),
				theWorm.getRadius()));
		assertEquals(theWorm.getActionPointsMaximum(),
				theWorm.getCurrentActionPoints());
	}

	@Test
	public void canMove_ActiveLegaCase() {
		assertTrue(wormRadius2.canActivelyMoveSteps(1));
	}
	
	@Test
	public void canMove_ActiveLegalCaseUpwardOrientation() {
		assertTrue(wormDirectionQuarterPi.canActivelyMoveSteps(1));
	}

	@Test
	public void canMove_ActiveFalseCase() {
		assertFalse(wormRadius2.canActivelyMoveSteps(100000));
	}

	@Test
	public void move_ActiveHorizontal() {
		standardWorm.move(4, true);
		assertTrue(fuzzyEquals(4, standardWorm.getPosition().getX()));
		assertTrue(fuzzyEquals(0, standardWorm.getPosition().getY()));
		assertEquals(4444, standardWorm.getCurrentActionPoints());
	}

	@Test
	public void move_ActiveVertical() {
		Worm worm = new Worm(new Position(0, 0), Math.PI / 2, 1, "Vertical");
		worm.move(3, true);
		assertTrue(fuzzyEquals(0, worm.getPosition().getX()));
		assertTrue(fuzzyEquals(3, worm.getPosition().getY()));
	}
	
	@Test
	public void move_ActiveUpwardOrientation() {
		wormDirectionQuarterPi.move(10, true);
		assertTrue(fuzzyEquals(10 * Math.cos(Math.PI / 4),
				wormDirectionQuarterPi.getPosition().getX()));
		assertTrue(fuzzyEquals(10 * Math.sin(Math.PI / 4),
				wormDirectionQuarterPi.getPosition().getY()));
	}
	
	@Test
	public void move_ActiveMultipleIntervals() {
		wormDirectionQuarterPi.move(5, true);
		wormDirectionQuarterPi.move(5, true);
		assertTrue(fuzzyEquals(10 * Math.cos(Math.PI / 4),
				wormDirectionQuarterPi.getPosition().getX()));
		assertTrue(fuzzyEquals(10 * Math.sin(Math.PI / 4),
				wormDirectionQuarterPi.getPosition().getY()));
	}

	@Test(expected = IllegalStepsException.class)
	public void moveActive_IllegalCase() throws Exception {
		standardWorm.move(10000, true);
	}

	@Test
	public void move_PassiveSingleCase() {
		standardWorm.move(1, false);
		assertEquals(new Position(1, 0), standardWorm.getPosition());
		assertEquals(standardWorm.getActionPointsMaximum(),
				standardWorm.getCurrentActionPoints());
	}

	@Test
	public void jump_SingleCase() {
		Worm wormBefore = standardWorm;
		standardWorm.jump();
		assertTrue(fuzzyEquals(
				wormBefore.getPosition().getX() + wormBefore.jumpDistance(),
				standardWorm.getPosition().getX()));
		assertTrue(fuzzyEquals(wormBefore.getPosition().getY(), standardWorm
				.getPosition().getY()));
		assertEquals(0, standardWorm.getCurrentActionPoints());
	}

	@Test(expected = IllegalJumpException.class)
	public void jump_IllegalJump() throws Exception {
		Worm worm = new Worm(new Position(0, 0),
				Worm.getUpperAngleBound() / 2 + 1, 1, "Illegal Jumper");
		worm.jump();
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
		standardWorm.setCurrentActionPoints(0);
		assertFalse(standardWorm.canJump());
	}

	@Test
	public void jumpTime_SingleCase() {
		assertTrue(fuzzyEquals(
				wormDirectionQuarterPi.jumpDistance()
						/ (wormDirectionQuarterPi.jumpSpeed() * Math.cos(wormDirectionQuarterPi
								.getDirection())),
				wormDirectionQuarterPi.jumpTime()));
	}

	@Test
	public void jumpStep_LegalCase() {
		Position resultPosition = wormDirectionQuarterPi.jumpStep(1);
		Position expectedPosition = wormDirectionQuarterPi.getPosition();
		expectedPosition.translate(
				wormDirectionQuarterPi.getPosition().getX()
						+ wormDirectionQuarterPi.jumpSpeed()
						* Math.cos(wormDirectionQuarterPi.getDirection()) * 4,
				wormDirectionQuarterPi.getPosition().getY()
						+ wormDirectionQuarterPi.jumpSpeed()
						* Math.sin(wormDirectionQuarterPi.getDirection()) - 0.5
						* wormDirectionQuarterPi.g * Math.pow(4, 2));
		assertTrue(fuzzyEquals(expectedPosition.getX(), resultPosition.getX()));
		assertTrue(fuzzyEquals(expectedPosition.getY(), resultPosition.getY()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void jumpStep_IllegalTimeInterval() throws Exception {
		wormDirectionQuarterPi.jumpStep(2);
	}

	@Test
	public void jumpSpeed_SingleCase() {
		double F = 5 * wormDirectionQuarterPi.getCurrentActionPoints()
				+ wormDirectionQuarterPi.getMass() * wormDirectionQuarterPi.g;
		assertTrue(fuzzyEquals(F / wormDirectionQuarterPi.getMass() * 0.5,
				wormDirectionQuarterPi.jumpSpeed()));
	}

	@Test
	public void jumpDistance_SingleCase() {
		assertTrue(fuzzyEquals(Math.pow(wormDirectionQuarterPi.jumpSpeed(), 2)
				* Math.sin(2 * wormDirectionQuarterPi.getDirection())
				/ wormDirectionQuarterPi.g,
				wormDirectionQuarterPi.jumpDistance()));
	}

	@Test
	public void canTurn_TrueCase() {
		standardWorm.canTurn(standardWorm.getAngleRange() / 2);
	}

	@Test
	public void canTurn_FalseCaseActionPoints() {
		standardWorm.setCurrentActionPoints(0);
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
	public void setCurrentActionPoints_LegalCase() {
		standardWorm.setCurrentActionPoints(standardWorm
				.getActionPointsMaximum() - 1);
		assertEquals(standardWorm.getActionPointsMaximum() - 1,
				standardWorm.getCurrentActionPoints());
	}

	@Test
	public void setCurrentActionPoints_NegativeAmount() {
		standardWorm.setCurrentActionPoints(-10);
		assertEquals(0, standardWorm.getCurrentActionPoints());
	}

	@Test
	public void setCurrentActionPoints_AboveMaximum() {
		standardWorm.setCurrentActionPoints(standardWorm
				.getActionPointsMaximum() + 1);
		assertEquals(standardWorm.getActionPointsMaximum(),
				standardWorm.getCurrentActionPoints());
	}

	@Test
	public void setName_LegalCase() {
		standardWorm.setName("New name");
		assertEquals("New name", standardWorm.getName());
	}

	@Test(expected = IllegalNameException.class)
	public void setName_InvalidName() throws Exception {
		standardWorm.setName("Inval1d nam3");
	}

	@Test
	public void isValidName_SingleCharacterName() {
		assertFalse(standardWorm.isValidName("M"));
	}

	@Test
	public void isValidName_LowerCaseCharacterName() {
		assertFalse(standardWorm.isValidName("lower case"));
	}

	@Test
	public void isValidName_IllegalCharacterName() {
		assertFalse(standardWorm.isValidName("Illegal$ character"));
	}

}
