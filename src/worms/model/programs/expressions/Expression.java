package worms.model.programs.expressions;

public abstract class Expression {
	
	protected Expression(int line, int column) {
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
	
	public abstract Object evaluate();

}
