package worms.model.programs.expressions;

import worms.model.programs.types.DoubleType;

public class ArithmeticOperation extends BinaryExpression {

	public enum Operation {
		ADDITION, SUBTRACTION, MULTIPLICATION, DIVISION
	}

	public ArithmeticOperation(int line, int column, Expression e1,
			Expression e2, Operation operation) {
		super(line, column, e1, e2);
		this.operation = operation;
	}
	
	private Operation getOperation() {
		return operation;
	}
	
	private Operation operation;

	@Override
	public DoubleType evaluate() {
		double result;
		double a = (Double) getFirstChildExpression().evaluate().getValue();
		double b = (Double) getSecondChildExpression().evaluate().getValue();
		if (getOperation() == Operation.ADDITION) {
			result = a + b;
		} else if (getOperation() == Operation.SUBTRACTION) {
			result = a - b;
		} else if (getOperation() == Operation.MULTIPLICATION) {
			result = a * b;
		} else if (getOperation() == Operation.DIVISION) {
			result = a / b;
		} else {
			return null;
		}
		return new DoubleType(result);
	}

}
