package worms.model;

import static org.junit.Assert.*;

import org.junit.*;

import static worms.util.Util.*;

/**
 * A class collecting tests for the class of positions.
 * 
 * @version 1.0
 * @author Tom Gijselinck
 * 
 */

public class PositionTest {

	/**
	 * Variable referencing a position with X coordinate 1 and Y coordinate 2.
	 */
	private static Position position1x1y;
	
	/**
	 * Set up an immutable test fixture.
	 * 
	 * @post	The variable position1x1y references a new position with X
	 * 			coordinate 1 and Y coordinate 2.
	 */
	@BeforeClass
	public static void setUpImmutableFixture() throws Exception {
		position1x1y = new Position(1, 1);
	}
	
	@Test
	public void constructor_LegalCase() throws Exception {
		Position position = new Position(2, 2);
		assertTrue(fuzzyEquals(2, position.getX()));
		assertTrue(fuzzyEquals(2, position.getY()));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructor_IllegalXCoordinate() throws Exception {
		new Position(Double.NaN, 1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void constructor_IllegalYCoordinate() throws Exception {
		new Position(1, Double.NaN);
	}
	
	@Test
	public void translate_NormalCase() throws Exception {
		Position translatedPosition = position1x1y.translate(1, 2);
		assertTrue(fuzzyEquals(2, translatedPosition.getX()));
		assertTrue(fuzzyEquals(3, translatedPosition.getY()));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void translate_IllegalCoordinate() throws Exception {
		position1x1y.translate(Double.NaN, 1);
	}
	
	@Test
	public void equals_TrueCase() throws Exception {
		Position otherPosition = new Position(1, 1);
		assertTrue(position1x1y.equals(otherPosition));
	}
	
	@Test
	public void equals_DifferentClass() {
		double otherObject = 5;
		assertFalse(position1x1y.equals(otherObject));
	}

	@Test
	public void equals_DifferentXCoordinate() throws Exception {
		Position otherPosition = new Position(5, 1);
		assertFalse(position1x1y.equals(otherPosition));
	}
	
	@Test
	public void equals_DifferentYCoordinate() throws Exception {
		Position otherPosition = new Position(1, 5);
		assertFalse(position1x1y.equals(otherPosition));
	}

}
