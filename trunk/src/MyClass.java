
public class MyClass {
	
	public MyClass(Object d) {
		type = d.getClass();
	}
	
	private Class<?> type;
	
	public Class<?> getType() { return type;}

	public static void main(String[] args) {
		
		double t = 5;
		MyClass x = new MyClass(t);
		System.out.println(x.getType().getName());
		
	}

}
