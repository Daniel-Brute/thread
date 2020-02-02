package twoPhaseTerminationDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchDemo {

	public static final int TASKS = 10;
	
	public static void main(String[] args) {
		System.out.println("main: BEGIN");
		ExecutorService service = Executors.newFixedThreadPool(TASKS);
		CountDownLatch doneLatch = new CountDownLatch(TASKS); 
		
		try {
			
			for (int i = 0; i < TASKS; i++) {
				service.execute(new CountDownLatchMyTask(doneLatch, i));
			}
			
			System.out.println("await");
			doneLatch.await();
		} catch (InterruptedException e) {
		} finally {
			service.shutdown();
			System.out.println("END");
		}
	}
}
