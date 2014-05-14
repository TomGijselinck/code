package worms.model.programs.expressions;

public class DoubleConstantExpression extends Expression{
	
	public DoubleConstantExpression(int line, int column, double d) {
		super(line, column);
		setValue(d);
	}
	
	public double getValue() {
		return value;
	}
	
	private void setValue(double value) {
		this.value = value;
	}
	
	private double value;
	
	@Override
	public Double evaluate() {
		return getValue();
	}

}
