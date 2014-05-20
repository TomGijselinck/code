package worms.model.programs.expressions;

import worms.model.Position;
import worms.model.World;
import worms.model.Worm;
import worms.model.programs.types.EntityType;

public class SearchObjectExpression extends Expression {

	public SearchObjectExpression(int line, int column, Expression e) {
		super(line, column);
		setChildExpression(e);
	}

	@Override
	public EntityType evaluate() {
		Worm thisWorm = getProgram().getWorm();
		double wormDir = thisWorm.getDirection();
		double dx = thisWorm.getRadius() * Math.cos(wormDir);
		double dy = thisWorm.getRadius() * Math.sin(wormDir);
		Position position = thisWorm.getPosition().translate(dx, dy);
		World world = thisWorm.getWorld();
		while (world.isInsideWorldBorders(position)) {
			if (! world.isPassable(position)) {
				return new EntityType(null);
			} else if (world.overlaps(position, thisWorm.getRadius())) {
				Worm closestWorm = world.getOverlappingWorm(position,
						thisWorm.getRadius());
				return new EntityType(closestWorm);
			}
		}
		//no result
		return new EntityType(null);
	}
}
