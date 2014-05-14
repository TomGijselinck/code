package worms.model.programs.statements;

import worms.model.programs.Type;
import worms.model.programs.expressions.Expression;

public class AssignmentStatement extends Statement {

	public AssignmentStatement(int line, int column,
			String variableName, Expression rhs) {
		super(line, column);
		this.varName = variableName;
		this.expression = rhs;
	}

	public String getVariableName() {
		return varName;
	}

	private String varName;

	public Expression getExpression() {
		return expression;
	}

	private Expression expression;

	@Override
	public void execute() {
		Type<?> value = getExpression().evaluate();
		getProgram().setGlobalVariable(getVariableName(), value);
	}

}
