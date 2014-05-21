package worms.model.programs.expressions;

import worms.model.programs.types.NullType;

public class NullExpression extends Expression {
	
	public NullExpression(int line, int column) {
		super(line, column);
	}

	@Override
	public NullType evaluate() {
		return new NullType();
	}

}
