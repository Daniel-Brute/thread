package threadPerMessage;

public class Host {

	private Helper helper = new Helper();
	
	public void request(final int count, final char ch) {
		
		System.out.println("handle (" + count + "," + ch + ") BEGIN");
		
		new Thread() {
			@Override
			public void run() {
				helper.handle(count, ch);
			}
			
		}.start();
		
		System.out.println("handle (" + count + "," + ch + ") END");
		
	}
}
