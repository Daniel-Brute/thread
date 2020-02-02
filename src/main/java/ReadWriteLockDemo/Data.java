package ReadWriteLockDemo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Data {

	private final char[] buffer;
	private final MReadWriteLock lock = new MReadWriteLock();
	/* java.util.concurrent.locks中的读写锁
	 * private ReadWriteLock Jlock = new ReentrantReadWriteLock(true);//fair 
	 * private final Lock readLock = Jlock.readLock(); 
	 * private final Lock writeLock = Jlock.writeLock();
	 */
	
	public Data(int size) {
		this.buffer = new char[size];
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = '*';
		}
	}
	
	public char[] read() throws InterruptedException {
		lock.readLock();
		try {
			return doRead();
		} finally {
			lock.readUnlock();
		}
	}

	public void write(char ch) throws InterruptedException {
		lock.writeLock();
		try {
			doWrite(ch);
		} finally {
			lock.writeUnlock();
		}
	}
	
	private void doWrite(char ch) {
		
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = ch;
			slowly();
		}
	}

	private char[] doRead() {
		char[] newBuffer = new char[buffer.length];
		for (int i = 0; i < buffer.length; i++) {
			newBuffer[i] = buffer[i];	
		}
		slowly();
		return newBuffer;
	}

	private void slowly() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
