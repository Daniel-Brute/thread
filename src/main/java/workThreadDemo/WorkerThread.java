package workThreadDemo;

public class WorkerThread extends Thread {

	private final Channel channel;
	
	public WorkerThread(String name, Channel channel) {
		super(name);
		this.channel = channel;
	}
	//do request worker
	@Override
	public void run() {
		while(true) {
			Request request = channel.takeRequest();
			request.execute();
		}
	}
}
