package workThreadDemo;

import java.util.Random;
import java.util.concurrent.ExecutorService;

public class exClientRequest extends Thread {

	private final ExecutorService executorService;
	private static final Random random = new Random();
	
	public exClientRequest(String name, ExecutorService executorService) {
		super(name);
		this.executorService = executorService;
	}
	
	@Override
	public void run() {

		try {
			for (int i = 0; true; i++) {
				exRequest erequest = new exRequest(getName(), i);
				executorService.execute(erequest);
				Thread.sleep(random.nextInt(1000));
			}
		} catch (Exception e) {
			System.out.println(getName()+" : "+e);
		}
	}
}
