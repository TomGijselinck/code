package worms.model;


public class Facade implements IFacade {
	
	public Worm createWorm(double x, double y, double direction, double radius,
			String name) {
		try {
			return new Worm(new Position(x, y), direction, radius, name);
		}
		catch (Exception exc) {
			throw new ModelException(exc) ;
		}
	}
	
	public boolean canMove(Worm worm, int nbSteps) {
		return worm.canActivelyMoveSteps(nbSteps);
	}
	
	public void move(Worm worm, int nbSteps) {
		try {
			worm.move(nbSteps, true);
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}
	
	public boolean canTurn(Worm worm, double angle) {
		return worm.canTurn(angle);
	}
	
	public void turn(Worm worm, double angle) {
		worm.turn(angle);
	}
	
	public void jump(Worm worm) {
		try {
			worm.jump();
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}
	
	public double getJumpTime(Worm worm) {
		return worm.jumpTime();
	}
	
	public double[] getJumpStep(Worm worm, double t) {
		double[] location = new double[2];
		try {
			location[0] = worm.jumpStep(t).getX();
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
		try {
			location[1] = worm.jumpStep(t).getY();
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
		return location;
	}
	
	public double getX(Worm worm) {
		return worm.getPosition().getX();
	}
	
	public double getY(Worm worm) {
		return worm.getPosition().getY();
	}
	
	public double getOrientation(Worm worm) {
		return worm.getDirection();
	}
	
	public double getRadius(Worm worm) {
		return worm.getRadius();
	}
	
	public void setRadius(Worm worm, double newRadius) {
		try {
			worm.setRadius(newRadius);
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}
	
	public double getMinimalRadius(Worm worm) {
		return worm.getLowerRadiusBound();
	}
	
	public int getActionPoints(Worm worm) {
		return worm.getCurrentActionPoints();
	}
	
	public int getMaxActionPoints(Worm worm) {
		return worm.getActionPointsMaximum();
	}
	
	public String getName(Worm worm) {
		return worm.getName();
	}
	
	public void rename(Worm worm, String newName) {
		try {
			worm.setName(newName);
		}
		catch (Exception exc) {
			throw new ModelException(exc);
		}
	}
	
	public double getMass(Worm worm) {
		return worm.getMass();
	}

}
