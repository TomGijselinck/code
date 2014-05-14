package worms.model.programs.expressions;

import be.kuleuven.cs.som.annotate.Basic;

public class VariableExpression extends Expression {
	
	public VariableExpression(int line, int column, String name) {
		super(line, column, name);
		this.name = name;
		this.value = null;
	}
	
	@Basic
	public String getName() {
		return name;
	}
	
	private String name;
	
	//TODO: werk af na statements
	@Override
	public Object evaluate() {
		return value;
	}
	
	private Object value;

}
