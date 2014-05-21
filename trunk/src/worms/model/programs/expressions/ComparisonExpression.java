package worms.model.programs.expressions;

import worms.model.programs.types.BoolType;

public class ComparisonExpression extends BinaryExpression {

	/**
	 * An enumeration introducing different numerical equality and inequality
	 * relations between expressions.
	 */
	public enum Relation {
		LESS, LESSOREQUAL, GREATER, GREATEROREQUAL, EQUAL, NOTEQUAL
	}

	public ComparisonExpression(int line, int column, Expression e1,
			Expression e2, Relation relation) {
		super(line, column, e1, e2);
		this.relation = relation;
	}
	
	private Relation getRelation() {
		return relation;
	}
	
	private final Relation relation;

	@Override
	public BoolType evaluate() {
		boolean result;
		Object a = getFirstChildExpression().evaluate().getValue();;
		Object b = getSecondChildExpression().evaluate().getValue();
		if (getRelation() == Relation.LESS) {
			result = (Double) a < (Double) b;
		} else if (getRelation() == Relation.LESSOREQUAL) {
			result = (Double) a <= (Double) b;
		} else if (getRelation() == Relation.GREATER) {
			result = (Double) a > (Double) b;
		} else if (getRelation() == Relation.GREATEROREQUAL) {
			result = (Double) a >= (Double) b;
		} else if (getRelation() == Relation.EQUAL) {
			result =  a == b;
		} else if (getRelation() == Relation.NOTEQUAL) {
			result = a != b;
		} else {
			return null;
		}
		return new BoolType(result);
	}

}
