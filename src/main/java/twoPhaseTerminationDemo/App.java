package twoPhaseTerminationDemo;

public class App {

	public static void main(String[] args) {
		try {
			
			CountupThread t = new CountupThread();
			t.start();
			
			Thread.sleep(10000);
			
			System.out.println("main: shutdownRequest");
			
			t.shutdownRequest();
			System.out.println("main: join");
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main: END");
	}
}
