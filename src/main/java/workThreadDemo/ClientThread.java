package workThreadDemo;

import java.util.Random;

/**
 * 分发工作
 * @author 32216
 *
 */
public class ClientThread extends Thread{

	private final Channel channel;
	private static final Random random = new Random();
	public ClientThread(String name, Channel channel) {
		super(name);
		this.channel = channel;
	}
	
	@Override
	public void run() {
		try {
			for (int i = 0; true; i++) {
				Request request = new Request(getName(), i);
				channel.putRequest(request);
				Thread.sleep(random.nextInt(1000));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
