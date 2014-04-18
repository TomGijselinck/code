package worms.model;

import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Immutable;
import be.kuleuven.cs.som.annotate.Value;

/**
 * An enumeration introducing different weapons. In its current form, the class
 * only supports the Rifle and the Bazooka.
 * 
 * @author Tom Gijselinck
 * @version 1.0
 * 
 */
@Value
public abstract class Weapon {

	/**
	 * Initialize this weapon with given name and action point cost.
	 * 
	 * @param name
	 *            ...
	 * @param ActionPointCost
	 *            ...
	 * @post ... | new.getName() == name
	 * @post ... | new.getActionPointCost() == actionPointCost
	 */
	public Weapon(String name, int actionPointCost, int projectileMass,
			int damage) {
		this.name = name;
		this.actionPointCost = actionPointCost;
		this.projectileMass = projectileMass;
		this.damage = damage;
	}

	@Basic
	@Immutable
	public String getName() {
		return name;
	}

	private final String name;

	@Basic
	@Immutable
	public int getActionPointsCost() {
		return actionPointCost;
	}

	private int actionPointCost;

	@Basic
	@Immutable
	public int getProjecileMass() {
		return projectileMass;
	}

	private int projectileMass;

	@Basic
	@Immutable
	public double getProjectileRadius() {
		return (Math.pow((0.75 * getProjecileMass())
				/ (Math.PI * getProjectileDensity()), 1 / 3));
	}

	@Basic
	@Immutable
	public int getDamage() {
		return damage;
	}

	private int damage;

	public int getProjectileDensity() {
		return projectileDensity;
	}

	private int projectileDensity = 7800;

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this.getClass() != other.getClass())
			return false;
		Weapon otherWeapon = (Weapon) other;
		return ((this.getName() == otherWeapon.getName())
				&& (this.getActionPointsCost() == otherWeapon
						.getActionPointsCost())
				&& (this.getProjecileMass() == otherWeapon.getProjecileMass()) && (this
					.getDamage() == otherWeapon.getDamage()));
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 29 * hash + this.getActionPointsCost();
		hash = 29 * hash + this.getDamage();
		hash = 29 * hash + this.getName().hashCode();
		hash = 29 * hash + this.getProjecileMass();
		return hash;

	}

	@Override
	public String toString() {
		return getName();
	}

}
