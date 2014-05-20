package worms.model.programs.expressions;

import worms.model.Worm;
import worms.model.programs.types.BoolType;
import worms.model.programs.types.Type;

public class IsWormExpression extends Expression {

	public IsWormExpression(int line, int column, Expression e) {
		super(line, column);
		setChildExpression(e);
	}

	@Override
	public Type<Boolean> evaluate() {
		boolean result = getChildExpression().evaluate().getType() == Worm.class;
		return (new BoolType(result));
	}

}
