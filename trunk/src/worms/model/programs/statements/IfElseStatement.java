package worms.model.programs.statements;

import be.kuleuven.cs.som.annotate.Immutable;
import worms.model.programs.expressions.Expression;
import worms.model.programs.statements.Statement;

public class IfElseStatement extends Statement {

	public IfElseStatement(int line, int column, Expression condition,
			Statement then, Statement otherwise) {
		super(line, column);
		condition.setStatement(this);
		then.setParentStatement(this);
		otherwise.setParentStatement(this);
		this.condition = condition;
		this.then = then;
		this.otherwise = otherwise;
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
