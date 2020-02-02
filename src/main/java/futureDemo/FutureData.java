package futureDemo;
/**
 *  控制异步访问
 * @author 32216
 *
 */
public class FutureData implements Data{

	private RealData realData = null;
	private boolean ready = false;
	
	public synchronized void setRealData(RealData realData) {
		
		if(ready)
			return;
		this.realData = realData;
		this.ready = true;
		notifyAll();
		
	}
	
	public synchronized String getContext() {
		
		while(!ready) {
			try {
				wait();
			} catch (Exception e) {
			}
		}
		return realData.getContext();
	}

}
