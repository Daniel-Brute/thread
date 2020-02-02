package futureDemo;

public class App {

	public static void main(String[] args) {
		
		System.out.println("main BEGIN");
		
		// Host host = new Host();
		cHost host = new cHost();
		Data d1 = host.request(10, 'A');
		Data d2 = host.request(20, 'B');
		Data d3 = host.request(30, 'C');
		System.out.println("main otherJob BEGIN");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main otherJob BEGIN");
		System.out.println("d1 = " + d1.getContext());
		System.out.println("d2 = " + d2.getContext());
		System.out.println("d3 = " + d3.getContext());
		System.out.println("main END");
	}
}
