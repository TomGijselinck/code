package worms.model.programs.statements;

import java.util.Iterator;
import java.util.List;

public class SequenceStatement extends Statement {
	
	public SequenceStatement(int line, int column, List<Statement> statements) {
		super(line, column);
	}
	
	public List<Statement> getSequenceStatements() {
		return sequenceStatements;
	}
	
	private List<Statement> sequenceStatements;
	
	@Override
	public void execute() {
		Iterator<Statement> iterator = getSequenceStatements().iterator();
		while (iterator.hasNext()) {
			Statement statement = iterator.next();
			statement.execute();
		}
	}

}
