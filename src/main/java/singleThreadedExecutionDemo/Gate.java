package singleThreadedExecutionDemo;

/**

 * @author 32216
 *
 */

public class Gate {

	private int count = 0;
	private String name = "noBody";
	private String address = "noWhere";
	
	public synchronized void pass(String name, String address) {
		count++;
		this.name = name;
		this.address = address;
		check();
	}

	@Override
	public synchronized String toString() {
		return "No." + count + "," + name + "," + address;
	}
	private void check() {
		if(name.charAt(0) != address.charAt(0)) {
			System.out.println("*********** BROKEN  **********"+ toString());
		}
	}
}
