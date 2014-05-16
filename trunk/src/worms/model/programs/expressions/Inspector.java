package worms.model.programs.expressions;

import worms.model.programs.types.DoubleType;
import worms.model.programs.types.EntityType;

public class Inspector extends Expression {
	
	public enum InspectorType {
		X, Y, RADIUS, DIRECTION, AP, MAXAP, HP, MAXHP
	}
	
	public Inspector(int line, int column, Expression e, InspectorType type) {
		super(line, column);
		setChildExpression(e);
		this.type = type;
	}
	
	private InspectorType getType() {
		return type;
	}
	
	private final InspectorType type;

	@Override
	public DoubleType evaluate() {
		EntityType worm = (EntityType) getChildExpression().evaluate();
		if (getType() == InspectorType.X) {
			return new DoubleType(worm.getValue().getPosition().getX());
		} else if (getType() == InspectorType.Y) {
			return new DoubleType(worm.getValue().getPosition().getY());
		} else if (getType() == InspectorType.RADIUS) {
			return new DoubleType(worm.getValue().getRadius());
		} else if (getType() == InspectorType.DIRECTION) {
			return new DoubleType(worm.getValue().getDirection());
		} else if (getType() == InspectorType.AP) {
			return new DoubleType((double) worm.getValue().getCurrentActionPoints());
		} else if (getType() == InspectorType.MAXAP) {
			return new DoubleType((double) worm.getValue().getActionPointsMaximum());
		} else if (getType() == InspectorType.HP) {
			return new DoubleType((double) worm.getValue().getCurrentHitPoints());
		} else if (getType() == InspectorType.MAXHP) {
			return new DoubleType((double) worm.getValue().getHitPointsMaximum());
		} else {
			return null;
		}
		
	}

}
