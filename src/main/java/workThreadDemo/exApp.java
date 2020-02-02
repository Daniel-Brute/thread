package workThreadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class exApp {

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		try {
			new exClientRequest("Alice", executorService).start();
			new exClientRequest("Bobby", executorService).start();
			new exClientRequest("Chris", executorService).start();
			
			Thread.sleep(1000000);
		} catch(Exception e) {
			
		} finally {
			executorService.shutdown();
		}
	}
}
