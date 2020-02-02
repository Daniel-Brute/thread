package workThreadDemo;

import java.util.concurrent.Executors;

public class App {

	public static void main(String[] args) {
		Channel channel = new Channel(5);
		channel.startWorkers();
		new ClientThread("A",channel).start();
		new ClientThread("B",channel).start();
		new ClientThread("C",channel).start();
		
	}
}