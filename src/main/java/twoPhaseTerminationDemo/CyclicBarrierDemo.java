package twoPhaseTerminationDemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
	
	public static final int THREADS = 10;
	
	public static void main(String[] args) {
		System.out.println("BEGIN");
		ExecutorService service = Executors.newFixedThreadPool(THREADS);
		CountDownLatch doneLatch = new CountDownLatch(THREADS); 
		
		Runnable barrierAction = new Runnable() {

			public void run() {
				System.out.println("Barrier Action");
			}
		};
		
		CyclicBarrier cyclicBarrier = new CyclicBarrier(THREADS, barrierAction);
		
		try {
			
			for (int i = 0; i < THREADS; i++) {
				service.execute(new CyclicBarrierMyTask(doneLatch, cyclicBarrier, i));
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
