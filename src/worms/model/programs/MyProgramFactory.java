package worms.model.programs;

import java.util.List;

import worms.model.programs.expressions.ArithmeticOperation;
import worms.model.programs.expressions.BoolExpression;
import worms.model.programs.expressions.ComparisonExpression;
import worms.model.programs.expressions.DoubleConstantExpression;
import worms.model.programs.expressions.Expression;
import worms.model.programs.expressions.Inspector;
import worms.model.programs.expressions.IsWormExpression;
import worms.model.programs.expressions.NullExpression;
import worms.model.programs.expressions.SearchObjectExpression;
import worms.model.programs.expressions.SelfExpression;
import worms.model.programs.expressions.SquarerootExpression;
import worms.model.programs.expressions.ArithmeticOperation.Operation;
import worms.model.programs.expressions.ComparisonExpression.Relation;
import worms.model.programs.expressions.Inspector.InspectorType;
import worms.model.programs.expressions.VariableExpression;
import worms.model.programs.statements.ActionStatement;
import worms.model.programs.statements.AssignmentStatement;
import worms.model.programs.statements.IfElseStatement;
import worms.model.programs.statements.PrintStatement;
import worms.model.programs.statements.SequenceStatement;
import worms.model.programs.statements.Statement;
import worms.model.programs.statements.WhileStatement;
import worms.model.programs.statements.ActionStatement.Action;
import worms.model.programs.types.BoolType;
import worms.model.programs.types.DoubleType;
import worms.model.programs.types.EntityType;
import worms.model.programs.types.Type;

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
	@Override
	public Expression createBooleanLiteral(int line, int column, boolean b) {
		return new BoolExpression(line, column, b);
	}

	/**
	 * Create an expression representing the logical and operation on two
	 * expressions e1 and e2
	 */
	@Override
	public Expression createAnd(int line, int column, Expression e1, Expression e2) {
		return null;
	}

	/**
	 * Create an expression representing the logical or operation on two
	 * expressions e1 and e2
	 */
	@Override
	public Expression createOr(int line, int column, Expression e1, Expression e2) {
		return null;
	}

	/**
	 * Create an expression representing the logical not operation on the
	 * expression e
	 */
	@Override
	public Expression createNot(int line, int column, Expression e) {
		return null;
	}

	/**
	 * Create an expression representing the literal 'null'
	 */
	@Override
	public Expression createNull(int line, int column) {
		return new NullExpression(line, column);
	}

	/**
	 * Create an expression representing a reference to the worm that is
	 * executing the program
	 */
	@Override
	public Expression createSelf(int line, int column) {
		return new SelfExpression(line, column);
	}

	/**
	 * Create an expression to get the x-coordinate of the entity identified by
	 * the expression e
	 */
	@Override
	public Expression createGetX(int line, int column, Expression e) {
		return new Inspector(line, column, e, InspectorType.X);
	}

	/**
	 * Create an expression to get the y-coordinate of the entity identified by
	 * the expression e
	 */
	@Override
	public Expression createGetY(int line, int column, Expression e) {
		return new Inspector(line, column, e, InspectorType.Y);
	}

	/**
	 * Create an expression to get the radius of the entity identified by the
	 * expression e
	 */
	@Override
	public Expression createGetRadius(int line, int column, Expression e) {
		return new Inspector(line, column, e, InspectorType.RADIUS);
	}

	/**
	 * Create an expression to get the direction of the entity identified by the
	 * expression e
	 */
	@Override
	public Expression createGetDir(int line, int column, Expression e) {
		return new Inspector(line, column, e, InspectorType.DIRECTION);
	}

	/**
	 * Create an expression to get the action points of the entity identified by
	 * the expression e
	 */
	@Override
	public Expression createGetAP(int line, int column, Expression e) {
		return new Inspector(line, column, e, InspectorType.AP);
	}

	/**
	 * Create an expression to get the maximum number of action points of the
	 * entity identified by the expression e
	 */
	@Override
	public Expression createGetMaxAP(int line, int column, Expression e) {
		return new Inspector(line, column, e, InspectorType.MAXAP);
	}

	/**
	 * Create an expression to get the hit points of the entity identified by
	 * the expression e
	 */
	@Override
	public Expression createGetHP(int line, int column, Expression e) {
		return new Inspector(line, column, e, InspectorType.HP);
	}

	/**
	 * Create an expression to get the maximum number of hit points of the
	 * entity identified by the expression e
	 */
	@Override
	public Expression createGetMaxHP(int line, int column, Expression e) {
		return new Inspector(line, column, e, InspectorType.MAXHP);
	}

	/**
	 * Create an expression to evaluate whether the worm identified by the
	 * expression e belongs to the same team as the worm that is executing the
	 * program
	 */
	@Override
	public Expression createSameTeam(int line, int column, Expression e) {
		return null;
	}

	/**
	 * Create an expression to get the closest object in the direction theta+e,
	 * starting from the position of the worm that is executing the program,
	 * where theta is the current direction of the worm that is executing the
	 * program
	 */
	@Override
	public Expression createSearchObj(int line, int column, Expression e) {
		return new SearchObjectExpression(line, column, e);
	}

	/**
	 * Create an expression that evaluates whether the entity identified by the
	 * expression e is a worm
	 */
	@Override
	public Expression createIsWorm(int line, int column, Expression e) {
		return new IsWormExpression(line, column, e);
	}

	/**
	 * Create an expression that evaluates whether the entity identified by the
	 * expression e is a food ration
	 */
	@Override
	public Expression createIsFood(int line, int column, Expression e) {
		return null;
	}

	/**
	 * Create an expression that evaluates to the value of the variable with the
	 * given name
	 */
	@Override
	public Expression createVariableAccess(int line, int column, String name) {
		return new VariableExpression(line, column, name);
	}

	/**
	 * Create an expression that checks whether the value of expression e1 is
	 * less than the value of the expression e2
	 */
	@Override
	public Expression createLessThan(int line, int column, Expression e1, Expression e2) {
		return new ComparisonExpression(line, column, e1, e2, Relation.LESS);
	}

	/**
	 * Create an expression that checks whether the value of expression e1 is
	 * greater than the value of the expression e2
	 */
	@Override
	public Expression createGreaterThan(int line, int column, Expression e1, Expression e2) {
		return new ComparisonExpression(line, column, e1, e2, Relation.GREATER);
	}

	/**
	 * Create an expression that checks whether the value of expression e1 is
	 * less than or equal to the value of the expression e2
	 */
	@Override
	public Expression createLessThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
		return new ComparisonExpression(line, column, e1, e2, Relation.LESSOREQUAL);
	}

	/**
	 * Create an expression that checks whether the value of expression e1 is
	 * greater than or equal to the value of the expression e2
	 */
	@Override
	public Expression createGreaterThanOrEqualTo(int line, int column, Expression e1, Expression e2) {
		return new ComparisonExpression(line, column, e1, e2, Relation.GREATEROREQUAL);
	}

	/**
	 * Create an expression that checks whether the value of expression e1 is
	 * equal to the value of the expression e2
	 */
	@Override
	public Expression createEquality(int line, int column, Expression e1, Expression e2) {
		return new ComparisonExpression(line, column, e1, e2, Relation.EQUAL);
	}

	/**
	 * Create an expression that checks whether the value of expression e1 is
	 * not equal to the value of the expression e2
	 */
	@Override
	public Expression createInequality(int line, int column, Expression e1, Expression e2) {
		return new ComparisonExpression(line, column, e1, e2, Relation.NOTEQUAL);
	}

	/**
	 * Create an expression that represents the addition of the value of
	 * expression e1 and the value of the expression e2
	 */
	@Override
	public Expression createAdd(int line, int column, Expression e1, Expression e2) {
		return new ArithmeticOperation(line, column, e1, e2, Operation.ADDITION);
	}

	/**
	 * Create an expression that represents the subtraction of the value of
	 * expression e1 and the value of the expression e2
	 */
	@Override
	public Expression createSubtraction(int line, int column, Expression e1, Expression e2) {
		return new ArithmeticOperation(line, column, e1, e2, Operation.SUBTRACTION);
	}

	/**
	 * Create an expression that represents the multiplication of the value of
	 * expression e1 and the value of the expression e2
	 */
	@Override
	public Expression createMul(int line, int column, Expression e1, Expression e2) {
		return new ArithmeticOperation(line, column, e1, e2, Operation.MULTIPLICATION);
	}

	/**
	 * Create an expression that represents the division of the value of
	 * expression e1 and the value of the expression e2
	 */
	@Override
	public Expression createDivision(int line, int column, Expression e1, Expression e2) {
		return new ArithmeticOperation(line, column, e1, e2, Operation.DIVISION);
	}

	/**
	 * Create an expression that represents the square root of the value of
	 * expression e1 and the value of the expression e2
	 */
	@Override
	public Expression createSqrt(int line, int column, Expression e) {
		return new SquarerootExpression(line, column, e);
	}

	/**
	 * Create an expression that represents the sine of the value of expression
	 * e1 and the value of the expression e2
	 */
	@Override
	public Expression createSin(int line, int column, Expression e) {
		return null;
	}

	/**
	 * Create an expression that represents the cosine of the value of
	 * expression e1 and the value of the expression e2
	 */
	@Override
	public Expression createCos(int line, int column, Expression e) {
		return null;
	}

	/* actions */

	/**
	 * Create a statement that represents a turn of the worm executing the
	 * program by the value of the angle expression
	 */
	@Override
	public Statement createTurn(int line, int column, Expression angle) {
		return new ActionStatement(line, column, angle, Action.TURN);
	}

	/**
	 * Create a statement that represents a move of the worm executing the
	 * program
	 */
	@Override
	public Statement createMove(int line, int column) {
		return new ActionStatement(line, column, Action.MOVE);
	}

	/**
	 * Create a statement that represents a jump of the worm executing the
	 * program
	 */
	@Override
	public Statement createJump(int line, int column) {
		return new ActionStatement(line, column, Action.JUMP);
	}

	/**
	 * Create a statement that represents toggling the weapon of the worm
	 * executing the program
	 */
	@Override
	public Statement createToggleWeap(int line, int column) {
		return new ActionStatement(line, column, Action.TOGGLEWEAP);
	}

	/**
	 * Create a statement that represents firing the current weapon of the worm
	 * executing the program, where the propulsion yield is given by the yield
	 * expression
	 */
	@Override
	public Statement createFire(int line, int column, Expression yield) {
		return new ActionStatement(line, column, yield, Action.FIRE);
	}

	/**
	 * Create a statement that represents no action of a worm
	 */
	@Override
	public Statement createSkip(int line, int column) {
		return new ActionStatement(line, column, Action.SKIP);
	}

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
	@Override
	public Statement createIf(int line, int column, Expression condition, Statement then, Statement otherwise) {
		return new IfElseStatement(line, column, condition, then, otherwise);
	}

	/**
	 * Create a statement that represents the repeated execution of the body
	 * statement, as long as the value of the condition expression evaluates to
	 * true
	 */
	@Override
	public Statement createWhile(int line, int column, Expression condition, Statement body) {
		return new WhileStatement(line, column, condition, body);
	}

	/**
	 * Create a statement that represents the repeated execution of the body
	 * statement, where for each execution the value of the variable with the
	 * given name is set to a different object of the given type.
	 */
	@Override
	public Statement createForeach(int line, int column,
			worms.model.programs.ProgramFactory.ForeachType type,
			String variableName, Statement body) {
		return null;
	}

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
	@Override
	public Statement createPrint(int line, int column, Expression e) {
		return new PrintStatement(line, column, e);
	}

	/* types */

	/**
	 * Returns an object that represents the type of a global variable with
	 * declared type 'double'.
	 */
	@Override
	public Type<?> createDoubleType() {
		return new DoubleType();
	}

	/**
	 * Returns an object that represents the type of a global variable with
	 * declared type 'boolean'.
	 */
	@Override
	public Type<?> createBooleanType() {
		return new BoolType();
	}

	/**
	 * Returns an object that represents the type of a global variable with
	 * declared type 'entity'.
	 */
	@Override
	public Type<?> createEntityType() {
		return new EntityType();
	}

}
