package worms.model.programs.types;

public abstract class Type<T> {
	
	protected Type() {}
	
	public Class<?> getType() {
		return getClass();
	}
	
	public T getValue() {
		return value;
	}
	
	protected void setValue(T value) {
		this.value = value;
	}
	
	private T value;
}
