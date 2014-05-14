package worms.model.programs.expressions;

public class BoolExpression extends Expression {
	
	public BoolExpression(int line, int column, boolean b) {
		super(line, column);
		value = b;
	}
	
	@Override
	public Boolean evaluate() {
		return value;
	}
	
	private boolean value;

}
