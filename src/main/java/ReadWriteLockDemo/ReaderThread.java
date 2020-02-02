package ReadWriteLockDemo;

public class ReaderThread extends Thread{

	private Data data;
	public ReaderThread(Data data) {
		this.data = data;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				char[] readbuf = data.read();
				System.out.println(Thread.currentThread().getName() 
						+ " reads " + String.valueOf(readbuf));
			}
		} catch (Exception e) {
		}
	}
	
	
}
