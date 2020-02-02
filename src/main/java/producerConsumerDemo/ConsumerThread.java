package producerConsumerDemo;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ConsumerThread extends Thread{

	private final Exchanger<char []> exchanger;
	private char []buffer = null;
	private final Random random;
	
	public ConsumerThread(Exchanger<char []> exchanger, char []buffer, int seed) {
		super("ConsumerThread");
		this.exchanger = exchanger;
		this.buffer = buffer;
		this.random = new Random(seed);
	}
	
	@Override
	public void run() {
		
		try {
			while(true) {
				System.out.println(Thread.currentThread().getName() + ": BEFORE EXCHANGE");
				buffer = exchanger.exchange(buffer);
				System.out.println(Thread.currentThread().getName() + ": AFTER EXCHANGE");
			
				for (int i = 0; i < buffer.length; i++) {
					System.out.println(Thread.currentThread().getName() + " : -> " + buffer[i]);
					Thread.sleep(random.nextInt(1000));
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
