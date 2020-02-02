package twoPhaseTerminationDemo;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierMyTask implements Runnable{
	
	private final int PHASES = 5;
	private final CyclicBarrier cyclicBarrier;
	private final CountDownLatch doneLatch;
	private final int context;
	private final Random random = new Random();
	
	public CyclicBarrierMyTask(CountDownLatch doneLatch, CyclicBarrier cyclicBarrier, int context) {
		this.doneLatch = doneLatch;
		this.cyclicBarrier = cyclicBarrier;
		this.context = context;
		
	}

	public void run() {
		try {
			for (int i = 0; i < PHASES; i++) {
				doTask(i);
				cyclicBarrier.await();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			doneLatch.countDown();
		}
		
	}

	private void doTask(int phase) {
		String name = Thread.currentThread().getName();
		System.out.println(name + ":MyTask:BEGIN:context = " + context + ", pahse" + phase);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		} finally {
			System.out.println(name + ":MyTask:BEGIN:context=" + context + ", pahse" + phase);	
		}		
	}
}
