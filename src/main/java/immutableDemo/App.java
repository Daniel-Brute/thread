package immutableDemo;

public class App {

	public static void main(String[] args) {
		Person person = new Person("zhang","anhui");
		new PrintPersonThread(person).start();
		new PrintPersonThread(person).start();
		new PrintPersonThread(person).start();
		new PrintPersonThread(person).start();
		
	}
}
