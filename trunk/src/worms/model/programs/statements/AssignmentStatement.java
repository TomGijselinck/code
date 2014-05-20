package worms.model.programs.statements;

import be.kuleuven.cs.som.annotate.Immutable;
import worms.model.programs.expressions.Expression;
import worms.model.programs.types.Type;

public class AssignmentStatement extends Statement {

	public AssignmentStatement(int line, int column,
			String variableName, Expression rhs) {
		super(line, column);
		setExpression(rhs);
		this.varName = variableName;
	}

	@Immutable
	public final String getVariableName() {
		return varName;
	}

	private final String varName;

	public Expression getExpression() {
		return expression;
	}

	private Expression expression;

	@Override
	public void execute() {
		if (getProgram().isPaused() && (! canResumeExecution())) {
			//skip
		} else {
			if (getProgram().isPaused()) {
				getProgram().resume(this);
			}
			Type<?> value = getExpression().evaluate();
			getProgram().setGlobalVariable(getVariableName(), value);
		}
	}

}
