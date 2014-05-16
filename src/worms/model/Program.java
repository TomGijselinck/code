package worms.model;

import java.util.Map;

import worms.gui.game.IActionHandler;
import worms.model.programs.MyProgramFactory;
import worms.model.programs.ParseOutcome;
import worms.model.programs.ProgramParser;
import worms.model.programs.expressions.Expression;
import worms.model.programs.statements.Statement;
import worms.model.programs.types.Type;

public class Program {
	
	public Program() {}
	
	public ParseOutcome<?> parseProgram(String programText, IActionHandler handler) {
		this.handler = handler;
		factory = new MyProgramFactory();
		parser = new ProgramParser<Expression, Statement, Type<?>>(getFactory());
		parser.parse(programText);
		if (parser.getErrors().isEmpty()) {
			setInitialGlobalVariables(parser.getGlobals());
			initializeGlobalVariables(initialGlobalVariables);
			setMainStatement(parser.getStatement());
			return ParseOutcome.success(this);
		} else {
			return ParseOutcome.failure(parser.getErrors());
		}
	}
	
	public IActionHandler getHandler() {
		return handler;
	}
	
	private IActionHandler handler;
	
	public MyProgramFactory getFactory() {
		return factory;
	}
	
	private MyProgramFactory factory;
	
	public ProgramParser<Expression, Statement, Type<?>> getParser() {
		return parser;
	}
	
	private ProgramParser<Expression, Statement, Type<?>> parser;
	
	public Map<String, Type<?>> getGlobalVariables() { return null;}
	
	public void setGlobalVariable(String name, Type<?> value) {
		globalVariables.put(name, value);
	}
	
	public void initializeGlobalVariables(Map<String, Type<?>> variables) {
		globalVariables = variables;
	}
	
	private Map<String, Type<?>> globalVariables;
	
	private final void setInitialGlobalVariables(Map<String, Type<?>> variables) {
		initialGlobalVariables = variables;
	}
	
	private Map<String, Type<?>> initialGlobalVariables;
	
	public void run() {
		getMainStatement().execute();
	}
	
	public void pause() {}
	
	public void unPause() {}
	
	
	
	
	//ASSCIATIONS
	public Statement getMainStatement() {
		return mainStatement;
	}
	
	public void setMainStatement(Statement main) {
		mainStatement = main;
	}
	
	private Statement mainStatement;
	
	public Worm getWorm() {
		return worm;
	}
	
	public void setWorm(Worm worm) {
		this.worm = worm;
	}
	
	private Worm worm;

}
