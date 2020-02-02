package twoPhaseTerminationDemo;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

public class CountDownLatchMyTask implements Runnable{
	private final CountDownLatch doneLatch;
	private final int context;
	private final Random random = new Random();
	
	public CountDownLatchMyTask(CountDownLatch doneLatch, int context) {
		this.doneLatch = doneLatch;
		this.context = context;
		
	}

	public void run() {
		doTask();
		doneLatch.countDown();
	}

	private void doTask() {
		String name = Thread.currentThread().getName();
		System.out.println(name + ":MyTask:BEGIN:context=" + context);
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		} finally {
			System.out.println(name + ":MyTask:BEGIN:context=" + context);	
		}		
	}
}
