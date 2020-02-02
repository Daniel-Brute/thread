package ReadWriteLockDemo;

import java.util.Random;

public class WriterThread extends Thread{

	private final Random random = new Random();
	private final Data data;
	private final String filler;
	private int index = 0;
	
	public WriterThread(Data data, String filler) {
		this.data = data;
		this.filler = filler;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				char ch = nextChar();
				data.write(ch);
				Thread.sleep(random.nextInt(1000));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private char nextChar() {
		char ch = filler.charAt(index);
		index ++;
		if (index >= filler.length()) {
			index = 0;
		}
		return ch;
	}
}
