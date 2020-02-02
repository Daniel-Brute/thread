package producerConsumerDemo;

import java.util.Random;

public class MakerThread extends Thread{

	private final Random random;
	private Table table;
	private int id;
	public MakerThread(String name, Table table, int seed) {
		super(name);
		this.table = table;
		random = new Random(seed);
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(random.nextInt(1000));
				String cake = "[ Cake No." + nextId() + " by " + getName() + "]";
				table.put(cake);
			}
		} catch (Exception e) {
		}
	}

	private synchronized int nextId() {
		return id++;
	}
} 
