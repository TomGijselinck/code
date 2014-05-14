package worms.model.programs;

import java.util.List;

import worms.model.Worm;
import worms.model.programs.ProgramFactory.ForeachType;
import worms.model.programs.expressions.BoolExpression;
import worms.model.programs.expressions.DoubleConstantExpression;
import worms.model.programs.expressions.Expression;
import worms.model.programs.statements.AssignmentStatement;
import worms.model.programs.statements.SequenceStatement;
import worms.model.programs.statements.Statement;

public class MyProgramFactory implements ProgramFactory<Expression, Statement, Type<?>>{
	
	/* possible types for a foreach statement */

	public enum ForeachType {
		WORM, FOOD, ANY
	}

	/* expressions */

	/**
	 * Create an expression representing a double literal, with value d
	 */
	@Override
	public Expression createDoubleLiteral(int line, int column, double d) {
		return new DoubleConstantExpression(line, column, d);
	}

	/**
	 * Create an expression representing a boolean literal, with value b
	 */
	public BoolExpression createBooleanLiteral(int line, int column, boolean b) {
		return new BoolExpression(line, column, b);
	}

	/**
	 * Create an expression representing the logical and operation on two
	 * expressions e1 and e2
	 */
	public Expression createAnd(int line, int column, Expression e1, Expression e2);

	/**
	 * Create an expression representing the logical or operation on two
	 * expressions e1 and e2
	 */
	public Expression createOr(int line, int column, Expression e1, Expression e2);

	/**
	 * Create an expression representing the logical not operation on the
	 * expression e
	 */
	public Expression createNot(int line, int column, Expression e);

	/**
	 * Create an expression representing the literal 'null'
	 */
	public Expression createNull(int line, int column);

	/**
	 * Create an expression representing a reference to the worm that is
	 * executing the program
	 */
	public Expression createSelf(int line, int column);

	/**
	 * Create an expression to get the x-coordinate of the entity identified by
	 * the expression e
	 */
	public Expression createGetX(int line, int column, Expression e);

	/**
	 * Create an expression to get the y-coordinate of the entity identified by
	 * the expression e
	 */
	public Expression createGetY(int line, int column, Expression e);

	/**
	 * Create an expression to get the radius of the entity identified by the
	 * expression e
	 */
	public Expression createGetRadius(int line, int column, Expression e);

	/**
	 * Create an expression to get the direction of the entity identified by the
	 * expression e
	 */
	public Expression createGetDir(int line, int column, Expression e);

	/**
	 * Create an expression to get the action points of the entity identified by
	 * the expression e
	 */
	public Expression createGetAP(int line, int column, Expression e);

	/**
	 * Create an expression to get the maximum number of action points of the
	 * entity identified by the expression e
	 */
	public Expression createGetMaxAP(int line, int column, Expression e);

	/**
	 * Create an expression to get the hit points of the entity identified by
	 * the expression e
	 */
	public Expression createGetHP(int line, int column, Expression e);

	/**
	 * Create an expression to get the maximum number of hit points of the
	 * entity identified by the expression e
	 */
	public Expression createGetMaxHP(int line, int column, Expression e);

	/**
	 * Create an expression to evaluate whether the worm identified by the
	 * expression e belongs to the same team as the worm that is executing the
	 * program
	 */
	public Expression createSameTeam(int line, int column, Expression e);

	/**
	 * Create an expression to get the closest object in the direction theta+e,
	 * starting from the position of the worm that is executing the program,
	 * where theta is the current direction of the worm that is executing the
	 * program
	 */
	public Expression createSearchObj(int line, int column, Expression e);

	/**
	 * Create an expression that evaluates whether the entity identified by the
	 * expression e is a worm
	 */
	public Expression createIsWorm(int line, int column, Expression e);

	/**
	 * Create an expression that evaluates whether the entity identified by the
	 * expression e is a food ration
	 */
	public Expression createIsFood(int line, int column, Expression e);

	/**
	 * Create an expression that evaluates to the value of the variable with the
	 * given name
	 */
	public Expression createVariableAccess(int line, int column, String name);

	/**
	 * Create an expression that checks whether the value of expression e1 is
	 * less than the value of the expression e2
	 */
	public Expression createLessThan(int line, int column, Expression e1, Expression e2);

	/**
	 * Create an expression that checks whether the value of expression e1 is
	 * greater than the value of the expression e2
	 */
	public Expression createGreaterThan(int line, int column, Expression e1, Expression e2);

	/**
	 * Create an expression that checks whether the value of expression e1 is
	 * less than or equal to the value of the expression e2
	 */
	public Expression createLessThanOrExpressionqualTo(int line, int column, Expression e1, Expression e2);

	/**
	 * Create an expression that checks whether the value of expression e1 is
	 * greater than or equal to the value of the expression e2
	 */
	public Expression createGreaterThanOrExpressionqualTo(int line, int column, Expression e1, Expression e2);

	/**
	 * Create an expression that checks whether the value of expression e1 is
	 * equal to the value of the expression e2
	 */
	public Expression createExpressionquality(int line, int column, Expression e1, Expression e2);

	/**
	 * Create an expression that checks whether the value of expression e1 is
	 * not equal to the value of the expression e2
	 */
	public Expression createInequality(int line, int column, Expression e1, Expression e2);

	/**
	 * Create an expression that represents the addition of the value of
	 * expression e1 and the value of the expression e2
	 */
	public Expression createAdd(int line, int column, Expression e1, Expression e2);

	/**
	 * Create an expression that represents the subtraction of the value of
	 * expression e1 and the value of the expression e2
	 */
	public Expression createSubtraction(int line, int column, Expression e1, Expression e2);

	/**
	 * Create an expression that represents the multiplication of the value of
	 * expression e1 and the value of the expression e2
	 */
	public Expression createMul(int line, int column, Expression e1, Expression e2);

	/**
	 * Create an expression that represents the division of the value of
	 * expression e1 and the value of the expression e2
	 */
	public Expression createDivision(int line, int column, Expression e1, Expression e2);

	/**
	 * Create an expression that represents the square root of the value of
	 * expression e1 and the value of the expression e2
	 */
	public Expression createSqrt(int line, int column, Expression e);

	/**
	 * Create an expression that represents the sine of the value of expression
	 * e1 and the value of the expression e2
	 */
	public Expression createSin(int line, int column, Expression e);

	/**
	 * Create an expression that represents the cosine of the value of
	 * expression e1 and the value of the expression e2
	 */
	public Expression createCos(int line, int column, Expression e);

	/* actions */

	/**
	 * Create a statement that represents a turn of the worm executing the
	 * program by the value of the angle expression
	 */
	public S createTurn(int line, int column, Expression angle);

	/**
	 * Create a statement that represents a move of the worm executing the
	 * program
	 */
	public S createMove(int line, int column);

	/**
	 * Create a statement that represents a jump of the worm executing the
	 * program
	 */
	public S createJump(int line, int column);

	/**
	 * Create a statement that represents toggling the weapon of the worm
	 * executing the program
	 */
	public S createToggleWeap(int line, int column);

	/**
	 * Create a statement that represents firing the current weapon of the worm
	 * executing the program, where the propulsion yield is given by the yield
	 * expression
	 */
	public S createFire(int line, int column, Expression yield);

	/**
	 * Create a statement that represents no action of a worm
	 */
	public S createSkip(int line, int column);

	/* other statements */

	/**
	 * Create a statement that represents the assignment of the value of the rhs
	 * expression to a variable with the given name
	 */
	@Override
	public Statement createAssignment(int line, int column, String variableName, Expression rhs) {
		return new AssignmentStatement(line, column, variableName, rhs);
	}

	/**
	 * Create a statement that represents the conditional execution of the
	 * statements then or otherwise, depending on the value of the condition
	 * expression
	 */
	public S createIf(int line, int column, Expression condition, S then, S otherwise);

	/**
	 * Create a statement that represents the repeated execution of the body
	 * statement, as long as the value of the condition expression evaluates to
	 * true
	 */
	public S createWhile(int line, int column, Expression condition, S body);

	/**
	 * Create a statement that represents the repeated execution of the body
	 * statement, where for each execution the value of the variable with the
	 * given name is set to a different object of the given type.
	 */
	public S createForeach(int line, int column, ForeachType type,
			String variableName, S body);

	/**
	 * Create a statement that represents the sequential execution of the given
	 * statements
	 */
	@Override
	public Statement createSequence(int line, int column, List<Statement> statements) {
		return new SequenceStatement(line, column, statements);
	}

	/**
	 * Create a statement that represents printing out the value of the
	 * expression e
	 */
	public S createPrint(int line, int column, Expression e);

	/* types */

	/**
	 * Returns an object that represents the type of a global variable with
	 * declared type 'double'.
	 */
	@Override
	public Type<?> createDoubleType() {
		return new Type<Double>();
	}

	/**
	 * Returns an object that represents the type of a global variable with
	 * declared type 'boolean'.
	 */
	@Override
	public Type<?> createBooleanType() {
		return new Type<Boolean>();
	}

	/**
	 * Returns an object that represents the type of a global variable with
	 * declared type 'entity'.
	 */
	@Override
	public Type<?> createEntityType() {
		return new Type<Worm>();
	}

}
