package futureDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 *  控制异步访问
 * @author 32216
 *
 */
public class cFutureData extends FutureTask<RealData> implements Data {

	public cFutureData(Callable<RealData> callable) {
		super(callable);
	}

	public synchronized String getContext() {
		
		String string = null;
		try {
			string = get().getContext();
		} catch (InterruptedException e) {

			e.printStackTrace();
		} catch (ExecutionException e) {

			e.printStackTrace();
		}
		return string;
	}

}
