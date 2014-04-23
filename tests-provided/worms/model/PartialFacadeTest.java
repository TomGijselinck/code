package worms.model;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import worms.model.Facade;
import worms.model.Worm;
import worms.util.Util;

public class PartialFacadeTest {

	private static final double EPS = Util.DEFAULT_EPSILON;

	private IFacade facade;

	private Random random;

	private World world;
	
	private World world1;

	// X X X X
	// . . . .
	// . . . .
	// X X X X
	private boolean[][] passableMap = new boolean[][] {
			{ false, false, false, false }, { true, true, true, true },
			{ true, true, true, true }, { false, false, false, false } };
	
	// 10x10 pixels
			//     0 1 2 3 4 5 6 7 8 9
			//    --------------------
			//  1| . . . . . . . . . .
			//  2| . . X . . . . . . .
			//  3| X X X . . . . X . .
			//  4| . X X X X X X X . .
			//  5| . . . . . . . . . .
			//  6| . . . . . . . . . .
			//  7| . . . . . . O . . .
			//  8| . . . . . . . . . .
			//  9| . . . . . . . . . .
			// 10| X X X X X X X X X X
			private boolean[][] passableMap1 = new boolean[][] {
					{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
					{true, 	true, 	false, 	true,	true,	true,	true,	true,	true,	true},
					{false, false, 	false, 	true,	true,	true,	true,	false,	true,	true},
					{true, false, 	false, 	false,	false,	false,	false,	false,	true,	true},
					{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
					{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
					{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
					{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
					{true, 	true, 	true, 	true,	true,	true,	true,	true,	true,	true},
					{false, false, 	false, 	false,	false,	false,	false,	false,	false,	false}
			};

	@Before
	public void setup() {
		facade = new Facade();
		random = new Random(7357);
		world = new World(4.0, 4.0, passableMap, random);
		world1 = new World(5, 5, passableMap1, new Random());
	}

	@Test
	public void testMaximumActionPoints() {
		Worm worm = facade.createWorm(world, 1, 2, 0, 1, "Test");
		assertEquals(4448, facade.getMaxActionPoints(worm));
	}

	@Test
	public void testMoveHorizontal() {
		Worm worm = facade.createWorm(world, 1, 2, 0, 1, "Test");
		facade.move(worm);
		assertEquals(2, facade.getX(worm), EPS);
		assertEquals(2, facade.getY(worm), EPS);
	}

	@Test
	public void testMoveVertical() {
		Worm worm = facade.createWorm(world, 1, 1.5, Math.PI / 2, 0.5, "Test");
		facade.move(worm);
		assertEquals(1, facade.getX(worm), EPS);
		assertEquals(2.0, facade.getY(worm), EPS);
	}

	@Test
	public void testMoveVerticalAlongTerrain() {
		// . . X
		// . w X
		World world = facade.createWorld(3.0, 2.0, new boolean[][] {
				{ true, true, false }, { true, true, false } }, random);
		Worm worm = facade.createWorm(world, 1.5, 0.5,
				Math.PI / 2 - 10 * 0.0175, 0.5, "Test");
		facade.move(worm);
		assertEquals(1.5, facade.getX(worm), EPS);
		assertEquals(1.0, facade.getY(worm), EPS);
	}

	@Test
	public void testFall() {
		// . X .
		// . w .
		// . . .
		// X X X
		World world = facade.createWorld(3.0, 4.0, new boolean[][] {
				{ true, false, true }, { true, true, true },
				{ true, true, true }, { false, false, false } }, random);
		Worm worm = facade.createWorm(world, 1.5, 2.5, -Math.PI / 2, 0.5,
				"Test");
		assertFalse(facade.canFall(worm));
		facade.move(worm);
		assertTrue(facade.canFall(worm));
		facade.fall(worm);
		assertEquals(1.5, facade.getX(worm), EPS);
		assertEquals(1.5, facade.getY(worm), EPS);
	}
	
	@Test
	public void isAdjacent_TrueCase() {
		assertFalse(facade.isAdjacent(world1, 0.5, 1.5, 0.5));
	}
}
