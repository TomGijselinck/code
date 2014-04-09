package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Value;


/**
 * An enumeration introducing different weapons.
 * 	In its current form, the class only supports the Rifle and the Bazooka.
 * 	 
 * @author 	Tom Gijselinck
 * @version	1.0
 *
 */
@Value
enum Weapon {
	
	RIFLE("Rifle", 10), BAZOOKA("Bazooka", 50);
	
	/**
	 * Initialize this weapon with given name and action point cos.
	 * 
	 * @param 	name
	 * 			...
	 * @param 	ActionPointCost
	 * 			...
	 * @post	...
	 * 		  |	new.getName() == name
	 * @post	...
	 * 		  |	new.getActionPointCost() == actionPointCost
	 */
	private Weapon(String name, int ActionPointCost) {
		this.name = name;
		this.actionPointCost = ActionPointCost;
	}
	
	/**
	 * ...
	 */
	@Basic @Immutable
	public String getName() {
		return name;
	}
	
	/**
	 * ...
	 */
	private final String name;
	
	
	
	
	/**
	 * ...
	 */
	public int getActionPointsCost() {
		return actionPointCost;
	}
	
	/**
	 * ...
	 */
	private int actionPointCost;

}
