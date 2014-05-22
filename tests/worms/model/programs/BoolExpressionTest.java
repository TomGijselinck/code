package worms.model.programs;

import org.junit.Before;
import org.junit.Test;

import worms.model.programs.expressions.BoolExpression;
import static org.junit.Assert.*;

public class BoolExpressionTest {
	
	private static BoolExpression boolexpr;

	@Before
	public void setup() {
		boolexpr = new BoolExpression(1, 1, true);
	}
	
	@Test
	public void evaluate_SingleCase() {
		assertEquals(boolexpr.evaluate().getValue(), true);
	}
	

}