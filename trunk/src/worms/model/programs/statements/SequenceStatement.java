package worms.model.programs.statements;

import java.util.Iterator;
import java.util.List;

public class SequenceStatement extends Statement {
	
	public SequenceStatement(int line, int column, List<Statement> statements) {
		super(line, column);
		setSequenceStatements(statements);
	}
	
	public void setSequenceStatements(List<Statement> statements) {
		Iterator<Statement> iterator = statements.iterator();
		while (iterator.hasNext()) {
			adAsStatement(iterator.next());
		}
	}
	
	@Override
	public void execute() {
		if ((getProgram().isPaused() && (! canResumeExecution())) || (getStatements() == null) ) {
			//skip
		} else {
			if (getProgram().isPaused()) {
				getProgram().resume(this);
			}
			Iterator<Statement> iterator = getStatements().iterator();
			while (iterator.hasNext()) {
				iterator.next().execute();
			}
		}
		
	}

}
