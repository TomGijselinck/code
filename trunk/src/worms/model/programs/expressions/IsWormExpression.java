package worms.model.programs.expressions;

import worms.model.programs.types.Type;

public class IsWormExpression extends Expression {
	
	public IsWormExpression(int line, int column, Expression e) {
		super(line, column);
		setChildExpression(e);
	}

	@Override
	public Type<?> evaluate() {
		// TODO Auto-generated method stub
		return null;
	}

}
