package worms.model.programs.expressions;

import worms.model.Program;
import worms.model.programs.statements.Statement;
import worms.model.programs.types.Type;

public abstract class Expression {
	
	protected Expression(int line, int column) {
		this.line = line;
		this.column = column;
	}
	
	public int getLine() {
		return line;
	}

	private int line;
	
	public int getCol() {
		return column;
	}
	
	private int column;
	
	public abstract Type<?> evaluate();
	
	
	
	
	//ASSOCIATIONS
	
	public Program getProgram() {
		Expression expression = this;
		while (expression.hasParentExpression()) {
			expression = getParentExpression();
		}
		return getStatement().getRootProgram();
	}
	
	public Statement getStatement() {
		return statement;
	}
	
	public void setStatement(Statement statement) {
		this.statement = statement;
	}
	
	private Statement statement;
	
	public Expression getParentExpression() {
		return parentExpression;
	}
	
	public boolean hasParentExpression() {
		return (getParentExpression() != null);
	}
	
	public void setParentExpression(Expression expression) {
		parentExpression = expression;
	}
	
	private Expression parentExpression;
	
	public Expression getChildExpression() {
		return childExpression;
	}
	
	public void setChildExpression(Expression childExpr) {
		childExpression = childExpr;
		childExpr.setParentExpression(this);
	}
	
	private Expression childExpression;

}
