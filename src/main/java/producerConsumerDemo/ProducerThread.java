package producerConsumerDemo;

import java.util.Random;
import java.util.concurrent.Exchanger;

public class ProducerThread extends Thread{
	private final Exchanger<char []> exchanger;
	private char []buffer = null;
	private char index = 0;
	private final Random random;
	
	public ProducerThread(Exchanger<char []> exchanger, char []buffer, int seed) {
		
		super("ProducerThread");
		this.exchanger = exchanger;
		this.buffer = buffer;
		this.random = new Random(seed);
	}
	
	@Override
	public void run() {
		
		try {
			while(true) {
				for (int i = 0; i < buffer.length; i++) {
					buffer[i] = nextChar();
					System.out.println(Thread.currentThread().getName() + " : " +buffer[i] + " -> ");
				}
				
				System.out.println(Thread.currentThread().getName() + ": BEFORE EXCHANGE");
				buffer = exchanger.exchange(buffer);
				System.out.println(Thread.currentThread().getName() + ": AFTER EXCHANGE");
			
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private char nextChar() throws InterruptedException {
		
		char ch = (char)('A' + index % 26);
		index ++;
		Thread.sleep(random.nextInt(1000));
		return ch;
	}
}
