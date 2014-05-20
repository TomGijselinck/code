package worms.model.programs.statements;

import be.kuleuven.cs.som.annotate.Immutable;
import worms.gui.game.IActionHandler;
import worms.model.Worm;
import worms.model.programs.expressions.Expression;

public class ActionStatement extends Statement {
	
	public enum Action {
		TURN, MOVE, JUMP, TOGGLEWEAP, FIRE, SKIP
	}
	
	public ActionStatement(int line, int column, Expression e, Action action) {
		super(line, column);
		setExpression(e);
		this.action = action;
	}
	
	public ActionStatement(int line, int column, Action action) {
		super(line, column);
		this.action = action;
	}
	
	@Immutable
	private final Action getAction() {
		return action;
	}
	
	private final Action action;

	@Override
	public void execute() {
		IActionHandler handler = getProgram().getHandler();
		Worm worm = getProgram().getWorm();
		if (getAction() == Action.TURN) {
			double angle = (Double) getExpression().evaluate().getValue();
			handler.turn(worm, angle);
		} else if (getAction() == Action.MOVE) {
			handler.move(worm);
		} else if (getAction() == Action.JUMP) {
			handler.jump(worm);
		} else if (getAction() == Action.TOGGLEWEAP) {
			handler.toggleWeapon(worm);
		} else if (getAction() == Action.FIRE) {
			double propulsion = (Double) getExpression().evaluate().getValue();
			handler.fire(worm, (int) propulsion);
		} else if (getAction() == Action.SKIP) {
			//do nothing (i.e. no action)
		}
	}

}
