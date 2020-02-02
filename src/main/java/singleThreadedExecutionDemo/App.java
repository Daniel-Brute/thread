package singleThreadedExecutionDemo;

public class App {

	public static void main(String[] args) {
		System.out.println("start single threaded execution test");
		Gate gate = new Gate();
		new UserThread(gate, "zhang","zhaanxi").start();
		new UserThread(gate, "wang","webei").start();
		new UserThread(gate, "li","luhan").start();
	}
}
