package worms.model;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import static worms.util.Util.*;

public class WorldTest {

	private World world1;
	private World world2;
	private Worm worm1;
	private Worm worm2;
	
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
	
	// 6x6 pixels
	//    1 2 3 4 5 6
	//   ------------
	// 1| . . . . . .
	// 2| . . . . . .
	// 3| . . X X . .
	// 4| . . X X . .
	// 5| . . . . . .
	// 6| X . . . . X
	private boolean[][] passableMap2 = new boolean[][] {
			{true,	true,	true,	true,	true,	true},
			{true,	true,	true,	true,	true,	true},
			{true,	true,	false,	false,	true,	true},
			{true,	true,	false,	false,	true,	true},
			{true,	true,	true,	true,	true,	true},
			{false,	true,	true,	true,	true,	false}
	};
	
	@Before
	public void setup() {
		world1 = new World(5, 5, passableMap1);
		world2 = new World(6, 6, passableMap2);
		worm1 = new Worm(new Position(0.5, 1), 0, 0.5, "First worm");
		worm2 = new Worm(new Position(3.5, 1.5), 0, 1, "Second worm");
		world1.addAsWorm(worm1);
		world1.addAsWorm(worm2);
	}
	
	@Test
	public void getNoHorizontalPixels_SingleCase() {
		assertEquals(10, world1.getNoHorizontalPixels());
	}
	
	@Test
	public void getNoVerticalPixels_SingleCase() {
		assertEquals(10, world1.getNoVerticalPixels());
	}
	
	@Test
	public void getHorizontalScale_SingleCase() { 
		assertTrue(fuzzyEquals(2, world1.getHorizontalScale()));
	}
	
	@Test
	public void getVerticalScale_SingleCase() { 
		assertTrue(fuzzyEquals(2, world1.getVerticalScale()));
	}
	
	@Test
	public void getPixelWidth_SingleCase() {
		assertTrue(fuzzyEquals(0.5, world1.getPixelWidth()));
	}
	
	@Test
	public void getPixelHeight_SingleCase() {
		assertTrue(fuzzyEquals(0.5, world1.getPixelHeight()));
	}
	
	@Test
	public void getPixelRow_SingleCase() {
		Position position = new Position(3.35, 2.3);
		assertEquals(6, world1.getPixelRow(position));
	}
	
	@Test
	public void getPixelColumn_SingleCase() {
		Position position = new Position(3.35, 2.3);
		assertEquals(7, world1.getPixelColumn(position));
	}
	
	@Test
	public void isPassable_TrueCase() {
		Position position = new Position(3.0, 2.0);
		assertTrue(world1.isPassable(position));
	}
	
	@Test
	public void isPassable_FalseCaseBottomLeft() {
		Position position = new Position(0.5, 3);
		assertFalse(world1.isPassable(position));
	}
	
	@Test
	public void isPassable_FalseCaseLeft() {
		Position position = new Position(3.5, 3.7);
		assertFalse(world1.isPassable(position));
	}
	
	@Test
	public void isPassable_FalseCaseTopLeft() {
		Position position = new Position(3.5, 4);
		assertFalse(world1.isPassable(position));
	}
	
	@Test
	public void isPassable_FalseCaseTop() {
		Position position = new Position(2.1, 3.5);
		assertFalse(world1.isPassable(position));
	}
	
	@Test
	public void isPassable_FalseCaseTopRight() {
		Position position = new Position(1.5, 4.5);
		assertFalse(world1.isPassable(position));
	}
	
	@Test
	public void isPassable_FalseCaseRight() {
		Position position = new Position(4, 3.9);
		assertFalse(world1.isPassable(position));
	}
	
	@Test
	public void isPassable_FalseCaseBottomRight() {
		Position position = new Position(4, 3);
		assertFalse(world1.isPassable(position));
	}
	
	@Test
	public void isPassable_FalseCaseBottom() {
		Position position = new Position(2.4, 3);
		assertFalse(world1.isPassable(position));
	}
	
	@Test
	public void isPassableForObject_TrueCase() {
		assertTrue(world1.isPassableForObject(new Position(3.5, 2), 0.5));
	}
	
	@Test
	public void isPassableForObject_FalseCase() {
		assertFalse(world1.isPassableForObject(new Position(3.5, 2), 1.1));
	}
	
	@Test
	public void isPassableArea_TrueCase() {
		assertTrue(world2.isPassableArea(new Position(3, 3), 2.7, 1.9));
	}
	
	@Test
	public void isPassableArea_FalseCase() {
		assertFalse(world1.isPassableArea(new Position(1, 1), 0.75, 0.5));
	}
	
	@Test
	public void isAdjacent_TrueCase() {
		assertTrue(world1.isAdjacent(new Position(3.5, 1.5), 1));
	}
	
	@Test
	public void isAdjacent_FalseCase() {
		assertFalse(world1.isAdjacent(new Position(3.5, 2), 0.5));
	}
	
	@Test
	public void canHaveAsWorm_TrueCase() {
		world1.removeAsWorm(worm1);
		assertTrue(world1.canHaveAsWorm(worm1));
	}
	
	@Test
	public void canHaveAsWorm_NonEffectiveWorm() {
		assertFalse(world1.canHaveAsWorm(null));
	}
	
	@Test
	public void canHaveAsWorm_TerminatedWorm() {
		worm1.terminate();
		assertFalse(world1.canHaveAsWorm(worm1));
	}
	
	@Test
	public void canHaveAsWorm_TerminatedWorld() {
		world1.terminate();
		assertFalse(world1.canHaveAsWorm(worm1));
	}
	
	@Test
	public void canHaveAsWorm_WormNotAdjacent() {
		Worm worm = new Worm(new Position(1.5, 2), 0, 0.5, "Worm");
		assertFalse(world1.canHaveAsWorm(worm));
	}
	
	@Test
	public void hasProperWorms_SingleCase() {
		assertTrue(world1.hasProperWorms());
	}
	
	@Test
	public void getAllWorldObjects_SingleCase() {
		Set<Object> allObjects = world1.getAllWorldObjects();
		Iterator<Worm> iterator = world1.getAllWorms().iterator();
		while (iterator.hasNext()) {
			Worm worm = iterator.next();
			assertTrue(allObjects.contains(worm));
		}
		if (world1.hasProjectile()) {
			assertTrue(allObjects.contains(world1.getProjectile()));
		}
	}
	

}
