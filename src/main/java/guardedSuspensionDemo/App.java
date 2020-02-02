package guardedSuspensionDemo;

public class App {

	public static void main(String[] args) {
		
		RequestQueue requestQueue = new RequestQueue();
		new ClientThread(requestQueue,"zhang",3141592L).start();
		new ServerThread(requestQueue,"wang",3141592L).start();
		
	}
}
