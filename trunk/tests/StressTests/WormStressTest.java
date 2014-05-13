package StressTests;


import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import worms.model.Position;
import worms.model.World;
import worms.model.Worm;

/**
 * A class collecting tests for the class of Worm. In particular, this test 
 * class performs a number of stress tests to measure the runtime efficiency of 
 * the class of Worm.
 * 
 * @version 1.1
 * @author Tom Gijselinck
 * 
 */
public class WormStressTest {

	private World world1;
	private Worm worm1;
	
	// 10x10 pixels
	//     0 1 2 3 4 5 6 7 8 9
	//    --------------------
	//  1| X X X X X X X X X X
	//  2| X . . . . . . . . X
	//  3| X . . . . . . . . X
	//  4| X . . . . . . . . X
	//  5| X . . . . . . . . X
	//  6| X . . . . . . . . X
	//  7| X . . . . . . . . X
	//  8| X . . . . . . . . X
	//  9| X . . . . . . . . X
	// 10| X X X X X X X X X X
	private boolean[][] passableMap1 = new boolean[][] {
			{false, 	false, 	false, 	false,	false,	false,	false,	false,	false,	false},
			{false, 	true, 	true, 	true,	true,	true,	true,	true,	true,	false},
			{false, 	true, 	true, 	true,	true,	true,	true,	true,	true,	false},
			{false, 	true, 	true, 	true,	true,	true,	true,	true,	true,	false},
			{false, 	true, 	true, 	true,	true,	true,	true,	true,	true,	false},
			{false, 	true, 	true, 	true,	true,	true,	true,	true,	true,	false},
			{false, 	true, 	true, 	true,	true,	true,	true,	true,	true,	false},
			{false, 	true, 	true, 	true,	true,	true,	true,	true,	true,	false},
			{false, 	true, 	true, 	true,	true,	true,	true,	true,	true,	false},
			{false, 	false, 	false, 	false,	false,	false,	false,	false,	false,	false}
	};
	
	private static double timeStep = 0.0001;
	
	@Before
	public void setup() {
		world1 = new World(5, 5, passableMap1, new Random());
		worm1 = new Worm(new Position(1, 1), 0.5, 0.5, "First worm");
		world1.addAsWorm(worm1);
	}
	
	@Test
	public void jumpTime_StressTest() {
		double width = worm1.getWorld().getWidth();
		int runs = 20000;
		for (int i = 0; i<runs; i++) {
			Worm worm = new Worm(new Position(1 + (width - 2) * i / runs, 1), 0.5, 0.5, "Test");
			world1.addAsWorm(worm);
			worm.jump(timeStep);
			//System.out.println(worm.getPosition().toString());
		}
	}

}
