package worms.model;

import static org.junit.Assert.*;

import org.junit.*;

public class ProjectileTest {

	private static Projectile projectile;
	
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
	
	@Before
	public void setup() {
		projectile = new Projectile(new Position(2, 4), 10);
		world = new World(5, 5, passableMap);
	}
	
	@Test
	public void constructor() {
		assertEquals(10, projectile.getMass());
	}
	
	@Test
	public void terminate_SingleCase() {
		world.setProjectile(projectile);
		assertFalse(projectile.isTerminated());
	}

}
