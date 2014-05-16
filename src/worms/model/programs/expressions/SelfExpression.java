package worms.model.programs.expressions;

import worms.model.Worm;
import worms.model.programs.types.EntityType;
import worms.model.programs.types.Type;

public class SelfExpression extends Expression {
	
	public SelfExpression(int line, int column) {
		super(line, column);
	}

	@Override
	public Type<Worm> evaluate() {
		return new EntityType(getProgram().getWorm());
	}

}
