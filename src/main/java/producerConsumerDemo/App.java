package producerConsumerDemo;

public class App {

	public static void main(String[] args) {
		Table table = new Table(3);
		new MakerThread("thread-1", table, 31415).start();
		new MakerThread("thread-2", table, 92653).start();
		new MakerThread("thread-3", table, 58979).start();
		new EaterThread("thread-1", table, 32384).start();
		new EaterThread("thread-2", table, 62643).start();
		new EaterThread("thread-3", table, 38327).start();
	}
}
