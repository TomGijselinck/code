package worms.model.programs.statements;

import worms.model.Program;

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
	
	public void setProgram(Program program) {
		this.program = program;
	}
	
	private Program program;
	
}
