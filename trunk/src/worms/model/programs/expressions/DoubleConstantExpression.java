package worms.model.programs.expressions;

import worms.model.programs.types.DoubleType;

public class DoubleConstantExpression extends Expression{
	
	public DoubleConstantExpression(int line, int column, double d) {
		super(line, column);
		setValue(d);
	}
	
	@Override
	public DoubleType evaluate() {
		return value;
	}
	
	private void setValue(double value) {
		this.value = new DoubleType(value);
	}
	
	private DoubleType value;

}
;