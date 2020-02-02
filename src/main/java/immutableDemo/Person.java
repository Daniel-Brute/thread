package immutableDemo;

public final class Person {

	private String name;
	private String address;
	
	public Person(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	
	@Override
	public String toString() {
		return "[ Persom: name = " + name + ", address = " + address + "]";
	}
	
}
