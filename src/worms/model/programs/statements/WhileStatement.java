package worms.model.programs.statements;

import be.kuleuven.cs.som.annotate.Immutable;
import worms.model.programs.expressions.Expression;

public class WhileStatement extends Statement {

	public WhileStatement(int line, int column, Expression condition,
			Statement body) {
		super(line, column);
		condition.setStatement(this);
		body.setParentStatement(this);
		this.condition = condition;
		this.body = body;
	}
	
	@Immutable
	public final Expression getCondition() {
		return condition;
	}
	
	private final Expression condition;
	
	@Immutable
	public final Statement getBody() {
		return body;
	}
	
	private final Statement body;

	@Override
	public void execute() {
		if (getProgram().isPaused() && (! canResumeExecution())) {
			//skip
		} else {
			if (getProgram().isPaused()) {
				getProgram().resume(this);
			}
			while ((Boolean) getCondition().evaluate().getValue() == true) {
				getBody().execute();
			}
		}
	}

}
