package worms.model.programs.expressions;

import worms.model.programs.types.DoubleType;

public class SquarerootExpression extends Expression {
	
	public SquarerootExpression(int line, int column, Expression e) {
		super(line, column);
		setChildExpression(e);
	}

	@Override
	public DoubleType evaluate() {
		double result = (Double) getChildExpression().evaluate().getValue();
		result = Math.sqrt(result);
		return new DoubleType(result);
	}

}
