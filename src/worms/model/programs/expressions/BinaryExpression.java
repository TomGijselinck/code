package worms.model.programs.expressions;

import worms.model.programs.types.Type;

public abstract class BinaryExpression extends Expression {
	
	protected BinaryExpression(int line, int column, Expression e1, Expression e2) {
		super(line, column);
		setFirstChildExpression(e1);
		setSecondChildExpression(e2);
	}
	
	public Expression getFirstChildExpression() {
		return getChildExpression();
	}
	
	private void setFirstChildExpression(Expression firstChildExpr) {
		setChildExpression(firstChildExpr);
	}
	
	public Expression getSecondChildExpression() {
		return secondExpression;
	}
	
	private void setSecondChildExpression(Expression secondChildExpr) {
		secondExpression = secondChildExpr;
		secondChildExpr.setParentExpression(this);
	}
	
	private Expression secondExpression;
	
	public abstract Type<?> evaluate();

}
