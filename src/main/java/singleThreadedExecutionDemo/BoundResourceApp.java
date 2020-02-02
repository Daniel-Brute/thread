package singleThreadedExecutionDemo;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class BoundResourceApp {

	public static void main(String[] args) {
		BoundResource resource = new BoundResource(3);
		for (int i = 0; i < 10; i++) {
			new UserThread2(resource).start();
		}
	}
}


class Log{
	public static void println(String s) {
		System.out.println(Thread.currentThread().getName() + ":" + s);
	}
}

class BoundResource{
	private final Semaphore semaphore;
	private final int permits;
	private final Random random = new Random(314159);
	
	public BoundResource(int permits) {
		semaphore = new Semaphore(permits);
		this.permits = permits;
	}
	
	public void use() throws InterruptedException {
		semaphore.acquire();
		try {
			doUse();
		} finally {
			semaphore.release();
		}
	}

	private void doUse() throws InterruptedException {
		Log.println("BEGIN: use="+(permits-semaphore.availablePermits()));
		Thread.sleep(random.nextInt(500));
		Log.println("END: use="+(permits-semaphore.availablePermits()));
		
	}
}

class UserThread2 extends Thread{
	
	private final Random random = new Random(26535);
	private final BoundResource resource;
	
	public UserThread2(BoundResource resource) {
		this.resource = resource;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				resource.use();
				Thread.sleep(random.nextInt(3000));
			}
		} catch (Exception e) {
		}
	}
}



