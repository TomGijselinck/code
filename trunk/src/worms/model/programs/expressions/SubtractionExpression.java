package worms.model.programs.expressions;

import worms.model.programs.types.DoubleType;
import worms.model.programs.types.Type;

public class SubtractionExpression extends BinaryExpression {
	
	public SubtractionExpression(int line, int column, Expression e1, Expression e2) {
		super(line, column, e1, e2);
	}
	
	@Override
	public Type<?> evaluate() {
		Double value1 = (Double) getFirstChildExpression().evaluate().getValue();
		Double value2 = (Double) getSecondChildExpression().evaluate().getValue();
		Double result = value1 - value2;
		return new DoubleType(result);
	}

}
