package worms.model.programs.statements;

import java.util.List;

import worms.model.Program;
import worms.model.programs.expressions.Expression;

public abstract class Statement {
	
	protected Statement(int line, int column) {
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
	
	public abstract void execute();
	
	
	
	
	//ASSOCIATIONS
	
	public Program getProgram() {
		return program;
	}
	
	public Program getRootProgram() {
		Statement statement = this;
		while (statement.hasParentStatement()) {
			statement = statement.getParentStatement();
		}
		return statement.getProgram();
	}
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	private Program program;
	
	
	
	public List<Statement> getStatements() {
		return statements;
	}
	
	public void adAsStatement(Statement statement) {
		statements.add(statement);
		statement.setParentStatement(this);
	}
	
	private List<Statement> statements;
	
	
	
	public Statement getParentStatement() {
		return parentStatement;
	}
	
	public boolean hasParentStatement() {
		return (getParentStatement() != null);
	}
	
	public void setParentStatement(Statement statement) {
		parentStatement = statement;
	}
	
	private Statement parentStatement;
	
	
	
	public Expression getExpression() {
		return expression;
	}
	
	public void setExpression(Expression expression) {
		this.expression = expression;
		expression.setStatement(this);
	}
	
	private Expression expression;
	
}
