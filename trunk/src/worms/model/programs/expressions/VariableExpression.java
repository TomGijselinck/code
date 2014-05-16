package worms.model.programs.expressions;

import worms.model.programs.types.Type;
import be.kuleuven.cs.som.annotate.Basic;

public class VariableExpression extends Expression {
	
	public VariableExpression(int line, int column, String name) {
		super(line, column);
		this.name = name;
	}
	
	@Basic
	public String getName() {
		return name;
	}
	
	private String name;
	
	@Override
	public Type<?> evaluate() {
		Type<?> value;
		value = getStatement().getProgram().getGlobalVariables().get(name);
		return value;
	}

}
