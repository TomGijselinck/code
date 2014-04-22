package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Value;

@Value
public class Rifle extends Weapon {
	
	public Rifle () {
		super ("Rifle", 10, 0.01, 20);	
	}
	
	@Basic @Immutable
	public double getForce(int propulsion) {
		return 1.5;
	}

}