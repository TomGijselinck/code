package worms.model;

import static org.junit.Assert.*;
import static worms.util.Util.fuzzyEquals;

import org.junit.Before;
import org.junit.Test;

public class GameObjectTest {

	/**
	 * Variable referencing a game object with position (3.5, 1.5), direction 0,
	 * radius 1 and name "Standard".
	 */
	private static GameObject standardGameObject;

	/**
	 * Set up a mutable test fixture.
	 */
	@Before
	public void setUpMutableFixture() throws Exception {
		standardGameObject = new Worm(new Position(3.5, 1.5), 0, 1, "Standard");
	}

	@Test
	public void Constructor_LegalCase() throws Exception {
		GameObject theGameObject = new Worm(new Position(3, 5), 70, 11,
				"The GameObject");
		assertTrue(theGameObject.getPosition().equals(new Position(3, 5)));
		assertTrue(fuzzyEquals(70, theGameObject.getDirection()));
		assertTrue(fuzzyEquals(11, theGameObject.getRadius()));
		assertFalse(theGameObject.isTerminated());
	}

	@Test(expected = IllegalArgumentException.class)
	public void extendedConstructor_InvalidRadius() throws Exception {
		new Worm(new Position(0, 0), 0, Double.POSITIVE_INFINITY,
				"Infinite radius");
	}

	@Test
	public void isValidDirection_NaN() {
		assertFalse(GameObject.isValidDirection(Double.NaN));
	}

	@Test
	public void isValidDirection_BelowLowerBound() {
		double lowerBound = GameObject.getLowerAngleBound();
		assertFalse(GameObject.isValidDirection(lowerBound - 1));
	}

	@Test
	public void isValidDirection_UpperBound() {
		double upperBound = GameObject.getUpperAngleBound();
		assertFalse(GameObject.isValidDirection(upperBound));
	}

	@Test
	public void hasProperPosition_TrueCase() {
		assertTrue(standardGameObject.hasProperPosition());
	}

	@Test
	public void canhaveAsRadius_TrueCase() {
		assertTrue(standardGameObject.canHaveAsRadius(5));
	}

	@Test
	public void canhaveAsRadius_Nan() {
		assertFalse(standardGameObject.canHaveAsRadius(Double.NaN));
	}

	@Test
	public void canhaveAsRadius_NegativeRadius() {
		assertFalse(standardGameObject.canHaveAsRadius(-5));
	}

	@Test
	public void canhaveAsRadius_InfiniteRadius() {
		assertFalse(standardGameObject
				.canHaveAsRadius(Double.POSITIVE_INFINITY));
	}

	@Test
	public void isValidMass_TrueCase() {
		assertTrue(GameObject.isValidMass(0.5));
	}

	@Test
	public void isValidMass_Nan() {
		assertFalse(GameObject.isValidMass(Double.NaN));
	}

	@Test
	public void isValidMass_Negative() {
		assertFalse(GameObject.isValidMass(-1));
	}

	@Test
	public void isValidPosition_NonTerminatedGameObjectTrueCase() {
		assertTrue(standardGameObject.canHaveAsPosition(new Position(1, 1)));
	}

	@Test
	public void isValidPosition_NonTerminatedGameObjectFalseCase() {
		assertFalse(standardGameObject.canHaveAsPosition(null));
	}

	@Test
	public void isValidPosition_TerminatedGameObjectTrueCase() {
		standardGameObject.terminate();
		assertTrue(standardGameObject.canHaveAsPosition(null));
	}

	@Test
	public void isValidPosition_TerminatedGameObjectFalseCase() {
		standardGameObject.terminate();
		assertFalse(standardGameObject.canHaveAsPosition(new Position(1, 1)));
	}

}
