package futureDemo;

public class Host {

	public Data request(final int count, final char ch) {
		
		System.out.println("request (" + count + "," + ch + ") BEGIN");
		final FutureData future = new FutureData(); 
		
		//异步获得真实的结果，可以直接返回调用，但是结果需要等待异步处理完才能得到
		new Thread() {
			public void run() {
				RealData realData = new RealData(count, ch);
				future.setRealData(realData);
			}
		}.start();
		
		System.out.println("request (" + count + "," + ch + ") END");
		return future;
	}
}
