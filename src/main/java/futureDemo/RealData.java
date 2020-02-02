package futureDemo;

public class RealData implements Data{

	private final String context;
	
	public RealData(int count, char ch) {
		
		System.out.println("make RealData( " + count + ", " + ch +" ) BEGIN");
		char[] buffer = new char[count];
		for (int i = 0; i < count; i++) {
			buffer[i] = ch;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("make RealData( " + count + ", " + ch +" ) END");
		this.context = new String(buffer);
		
	}
	
	public String getContext() {

		return context;
	}

}
