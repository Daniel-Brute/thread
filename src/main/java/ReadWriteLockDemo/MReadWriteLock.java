package ReadWriteLockDemo;

public class MReadWriteLock {

	private int readingReaders = 0;
	private int waitingWriters = 0;
	private int writingWriters = 0;
	private boolean preferWriter = true;//写优先
	
	public synchronized void readLock() throws InterruptedException {
		//在读操作之前保证没有写操作，且写操作优先的情况下没有等待的写操作
		while(writingWriters > 0 || (preferWriter && waitingWriters > 0)) {
			wait();
		}
		readingReaders++;
	}
	
	public synchronized void readUnlock() {
		readingReaders --;
		preferWriter = true;
		notifyAll();
	}
	
	public synchronized void writeLock() throws InterruptedException {
		waitingWriters ++;
		//在写操作执行执行之前保证没有读操作和写操作
		try {
			while(readingReaders > 0 || writingWriters > 0) {
				wait();
			}
		} finally {
			waitingWriters--;
		}
		writingWriters++;
	}
	
	public synchronized void writeUnlock() {
		
		writingWriters--;
		preferWriter = false;
		notifyAll();
	}
}
