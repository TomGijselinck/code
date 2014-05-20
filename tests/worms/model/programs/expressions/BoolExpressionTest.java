package worms.model.programs.expressions;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoolExpressionTest {
	
	private static BoolExpression boolexpr;

	@Before
	public void setup() {
		boolexpr = new BoolExpression(1, 1, true);
	}
	
	@Test
	public void evaluate_SingleCase() {
		assertEquals(boolexpr.evaluate(), true);
	}
	

}
