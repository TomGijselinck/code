package worms.model.programs.statements;

import be.kuleuven.cs.som.annotate.Immutable;
import worms.model.programs.expressions.BoolExpression;
import worms.model.programs.expressions.Expression;
import worms.model.programs.statements.Statement;

public class IfElseStatement extends Statement {

	public IfElseStatement(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		super(line, column);
		if (condition == null) {
			this.condition = new BoolExpression(line, column, true);
		} else {
			this.condition = condition;
		}
		this.condition.setStatement(this);
		this.then = then;
		this.then.setParentStatement(this);
		this.otherwise = otherwise;
		this.otherwise.setParentStatement(this);
	}
	
	@Immutable
	public final Expression getCondition() {
		return condition;
	}
	
	private final Expression condition;
	
	@Immutable
	public final Statement getThen() {
		return then;
	}
	
	private final Statement then;
	
	@Immutable
	public final Statement getOthwerwise() {
		return otherwise;
	}
	
	private final Statement otherwise;

	@Override
	public void execute() {
		if (getProgram().isPaused() && (! canResumeExecution())) {
			//skip
		} else {
			if (getProgram().isPaused()) {
				getProgram().resume(this);
			}
			if ((Boolean) getCondition().evaluate().getValue() == true) {
				getThen().execute();
			} else {
				getOthwerwise().execute();
			}
		}
		
	}

}
