package futureDemo;

import java.util.concurrent.Callable;

public class cHost {
	
	public Data request(final int count, final char ch) {
		
		System.out.println("request (" + count + "," + ch + ") BEGIN");
		
		cFutureData cfuture = new cFutureData(
				new Callable<RealData>() {
					public RealData call() {
						return new RealData(count, ch);
					}
				}); 
		
		//异步获得真实的结果，可以直接返回调用，但是结果需要等待异步处理完才能得到
		new Thread(cfuture).start();
		
		System.out.println("request (" + count + "," + ch + ") END");
		return cfuture;
	}
}
