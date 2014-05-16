package worms.model.programs.expressions;

import worms.model.programs.types.BoolType;

public class BoolExpression extends Expression {
	
	public BoolExpression(int line, int column, boolean b) {
		super(line, column);
		setValue(b);
	}
	
	@Override
	public BoolType evaluate() {
		return value;
	}
	
	private void setValue(boolean value) {
		this.value = new BoolType(value);
	}
	
	private BoolType value;

}
