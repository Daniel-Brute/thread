package balkingDemo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Data {

	private final String filename;
	private String context;
	private boolean changed;
	
	public Data(String filename, String context) {
		this.filename = filename;
		this.context = context;
		this.changed = true;
	}
	
	public synchronized void change(String newContext) {
		context = newContext;
		changed = true;
	} 
	
	public synchronized void save() throws IOException {
		if(!changed)//守护条件
			return;
		doSave();
		changed = false;
	}

	private void doSave() throws IOException {
		System.out.println(Thread.currentThread().getName()+"calls do Save, context = " + context);
		Writer writer = new FileWriter(filename);
		writer.write(context);
		writer.close();
	}
}
