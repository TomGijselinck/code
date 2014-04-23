package worms.model;

import static org.junit.Assert.*;

import org.junit.*;

public class WeaponTest {

	private static Rifle rifle;
	
	@Before
	public void setup() {
		rifle = new Rifle();
	}
	
	@Test
	public void equals_TrueCase() {
		Rifle otherRifle = new Rifle();
		assertTrue(otherRifle.equals(rifle));
	}

}
