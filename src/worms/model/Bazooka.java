package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Value;

@Value
public class Bazooka extends Weapon {
	
	public Bazooka () {
		super ("Bazooka", 50, 300, 80);
	}	
	
	@Basic @Immutable
	public double getForce(int propulsion) {
		return (propulsion / 100 * 7 + 2.5);
	}

}