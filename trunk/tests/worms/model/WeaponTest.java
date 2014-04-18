package worms.model;

import static org.junit.Assert.*;

import org.junit.*;

public class WeaponTest {

	private static Rifle rifle;
	private static Bazooka bazooka;
	
	@Before
	public void setup() {
		rifle = new Rifle();
		bazooka = new Bazooka();
	}
	
	@Test
	public void equals_TrueCase() {
		Rifle otherRifle = new Rifle();
		assertTrue(otherRifle.equals(rifle));
	}

}
