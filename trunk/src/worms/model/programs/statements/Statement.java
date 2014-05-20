package worms.model.programs.statements;

import java.util.ArrayList;
import java.util.List;

import worms.model.programs.Program;
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
	
	public boolean getExecutionFlag() {
		return flag;
	}
	
	public void setExecutionFlag(boolean flag) {
		this.flag = flag;
	}
	
	private boolean flag = false;
	
	public abstract void execute();
	
	public boolean canResumeExecution() {
		return getExecutionFlag();
	}
	
	
	
	
	//ASSOCIATIONS
	
	public Program getProgram() {
		if (hasParentStatement()) {
			return getRootProgram();
		}
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
		statement.setParentStatement(this);
		statements.add(statement);
	}
	
	private List<Statement> statements = new ArrayList<Statement>();
	
	
	
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
		expression.setStatement(this);
		this.expression = expression;
	}
	
	private Expression expression;
	
}
