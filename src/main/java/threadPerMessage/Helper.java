package threadPerMessage;

public class Helper {

	public void handle(int count, char ch) {
		
		System.out.println("handle (" + count + "," + ch + ") BEGIN");
		
		for (int i = 0; i < count; i++) {
			slowly();
			System.out.print(ch);
		}
		
		System.out.println("");
		System.out.println("handle (" + count + "," + ch + ") END");

	}
	
	private void slowly() {
		try {
			Thread.sleep(100);
		} catch (Exception e) {
		}
	}
	
}
