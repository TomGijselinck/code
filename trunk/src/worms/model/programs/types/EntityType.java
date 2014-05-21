package worms.model.programs.types;

import worms.model.Worm;

public class EntityType extends Type<Worm> {
	
	public EntityType() {
		super();
		setValue(null);
	}
	
	public EntityType(Worm value) {
		super();
		setValue(value);
	}

}
