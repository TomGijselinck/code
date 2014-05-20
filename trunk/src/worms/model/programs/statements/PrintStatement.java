package worms.model.programs.statements;

import worms.model.programs.expressions.Expression;

public class PrintStatement extends Statement {
	
	public PrintStatement(int line, int column, Expression e) {
		super(line, column);
		setExpression(e);
	}

	@Override
	public void execute() {
		if (getProgram().isPaused() && (! canResumeExecution())) {
			//skip
		} else {
			if (getProgram().isPaused()) {
				getProgram().resume(this);
			}
			System.out.println(getExpression().evaluate().getValue());
		}
		
	}

}
