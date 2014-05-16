package worms.model.programs.expressions;

import worms.model.programs.types.Type;

public class NullExpression extends Expression {
	
	public NullExpression(int line, int column) {
		super(line, column);
	}

	@Override
	public Type<?> evaluate() {
		return null;
	}

}
