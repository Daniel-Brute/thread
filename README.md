
![avatar](.//pic//所有模式之间的关系.png)

# 1. Single Thread Execution
以共享资源被多线程访问。

safeMethod：可以多个线程调用。

unsafeMethod：只允许单个线程执行，多线程调用会出现线程安全问题，一般用互斥锁实现线程安全。

只允许单个线程执行的代码区成为临界区。

原子性总结：

> 1）基本类型、引用类型的引用和赋值是原子操作。

> 2）long和double的赋值和引用是非原子操作。

> 3）long或double在原子共享时需要将其加入到synchronized中操作，或者声明为volatile。

java的并发包（java.util.concurrent）中的semaphore可以实现资源限制的多线程访问。

**场景：**

> 1）多线程访问

> 2）共享资源发生状态改变，且需要确保其安全性时

# 2. Immutable
immutable指的是被多线程访问的对象。将对象设置为final类型的，即对象是不可变的，那么在多线程访问的时候就不存在线程安全问题，即对象一旦赋值将不会改变。

**场景：**

> 1）实例一经创建，将不会改变。

> 2）实例是被共享的，且被频繁访问的。

Java规范中的immutable类

> 1）java.lang.String

> 2）java.math.BigInteger和java.math.BigDecimal

> 3）java.util.regex.Pattern

> 4）java.lang.Integer等包装类

> 5）java.awt.Color

final类不可继承，final方法不可重写，final字段只可赋值一次，final参数不可直接赋值。
 
Collection中的线程安全：

> List是非线程安全的，使用collections.synchronizedList(List)可以将List转变成线程安全的。

>  java.util.concurrent.CopyOnWriteArrayList：适用于写少读多的场景，因为在执行写操作的时候会赋值整个list，非常消耗程序执行的性能。

# 3. Guarded Suspension
通过线程等待实现实例的安全。

使用wait实现线程等待，被notify和notifyAll后再检查条件是否成立。由于线程使用wait进行等待的期间是呆在停止队列中停止执行的，所以不会浪费java虚拟机执行时间。

java.util.concurrent.LinkedBlockingQueue数据结构是线程安全的，当此队列中的元素为空时进行take操作会进行wai等待。

![avatar](.//pic//LinkedBlockingQueue.png)

# 4. Balking模式
根据守护条件执行被守护的方法

**场景：**
> 1）在被守护的方法不需要执行时和不需要等待的守护条件成立时

> 2）守护条件在第一次成立时 ![avatar](.//pic//balking.png)


** 超时： **
1） wait：可以设置输入参数确定超时时间，即输入的毫秒级的参数为等待时间，否则只能使用notify或者notifyAll来唤醒等待队列中的线程。当interrupt方法被执行时队列中的线程会重新获得锁，但是会抛出InterruptedException异常。

2）指定超时异常（TimeoutException）：当程序执行时间大于指定的超时时间时可以抛出超时异常。

3）synchronized中没有超时也没有异常：即是执行interrupt方法也不会抛出异常，但是在获得锁之后可以手动抛出异常。

4）java.util.concurrent中的超时。
> 4.1）通过异常通知超时（java.util.concurrent.TimeoutException）：

```
java.util.concurrent.Future接口中的get方法
java.util.concurrent.Exchanger类的exchange方法
java.util.concurrent.Cyclicarrier类的await方法
java.util.concurrent.CountDownLatch类的await方法
```

> 4.2）通过返回值通知超时：

```
java.util.concurrent.BlockingQueue接口：当offer返回false或者poll返回null时，表示发生了超时。
java.util.concurrent.Semaphore类：tryAcquire返回false时，表示发生了超时。
java.util.concurrent.locks.lock接口：tryLock返回false时，表示发生了超时。
```

# 5. Producer-Consumer

![avatar](.//pic//producerConsumer.png)

Channel作为一个中间角色协调producer和consumer之间的生产速度和消费速度的差值。主要作用是传递Data。传递的方式有：队列、栈和优先队列。

当consumer只有一个的使用，在consumer的线程会访问的地方无需做互斥处理，这样可以提高程序性能。


** InterruptException： **

典型的加了throws InterruptException的方法有java.lang.Object的wait方法、java.lang.Thread的sleep和join方法。这些方法是在执行时是花费时间且可以取消的。

sleep与interrupt方法：sleep方法是暂停当前程序的执行，而当其他线程执行当前线程的interrupt方法时，当前线程会终止暂停状态，并且抛出InterruptException异常。

wait和interrupt方法：当前线程在执行wait之后会释放锁并处于等待状态，其他线程在执行当前线程的interrupt方法后当前线程会终止等待状态，但是之后在当前线程重新获得锁之后才会抛出interruptException。

join和interrupt方法：interrupt方法会取消join的等待状态，并且转移到catch语句块。

interrupt方法：

![avatar](.//pic//thread interrupt status.png)

如果没有调用sleep、join、wait等方法，或者没有编写检查程序的中断状态并抛出interruptException异常的代码，那么interruptException就不会抛出。

isinterrupted（Thread类的实例方法）：只检查程序中断状态，不改变状态。

Thread.interrupted：当前线序检查程序执行状态并返回，同时清除当前线程的中断状态。

java.util.concurrent中的队列：

![avatar](.//pic//java.util.queue.png)

![avatar](.//pic//ArrayBlockingQueue.png)

# 6. Read-Write Lock

![avatar](.//pic//ReadWriteLock.png)

**在读操作之前保证没有写操作，且写操作优先的情况下没有等待的写操作。**

**在写操作执行执行之前保证没有读操作和写操作。**

**场景：**

> 1）读频繁的

> 2）读比写频繁的

**java.util.concurrent.locks包中的读写锁：**

> 1）java.util.concurrent.locks.ReadWriteLock接口

> 2）java.util.concurrent.locks.ReentrantReadWriteLock类


# 7. Thread Per-Message

![avatar](.//pic//Thread Per-Message.png)

** 场景：**

> 1）没有执行顺序要求。

> 2）不需要返回值。

> 3）发送消息。

** java.util.concurrent包中的Thread Per-Message：**

1) java.lang.Thread

2) java.lang.Runnable：传入到Thread中

```
new Thread(new Runnable(){...}).start()
```

3) java.util.concurrent.ThreadFactory接口：使用工厂方法创建线程,隐藏创建线程的细节。

创建工厂：

```
new ThreadFactory(){
	public Thread newThread(Runnable r){
		return new Thread(r);
	}
}
```

使用工厂创建线程

```
threadFactory.newThread(new Runnable(){...}).start()
```

4) java.util.concurrent.Executors类：提供了默认工厂方法。

```
Executors.defaultThreadFactory();
```

5) java.util.concurrent.Executor接口：隐藏了创建线程的细节和操作。

创建执行器：

```
new Executor(){
	public void execute(Runnable r){
		new Thread(r).start();
	}
}
```

使用执行器：

```
executor.execute(
	new Runnable(){....}
);
```

6) java.util.concurrent.ExecutorService接口

```
ExecutorService executorService = Executors.newCachedThreadPool();

try {
} finally {
	executorService.shutdown();
}

```

7) java.util.concurrent.ScheduledExcutorService接口

```
ScheduledExcutorService scheduledExcutorService = Executors.newScheduledThreadPool(5);

try {
} finally {
	scheduledExcutorService.shutdown();
}

scheduledExcutorService.schedule(new Runnable(){...}, 3L, TimeUnit.SECONDS)
```


# 8. Worker Thread

![avatar](.//pic//workThread.png)

**场景：**
> 1）提高吞吐量

> 2）容量控制：固定的提供服务的数量。

> 3）调用与执行分离

** Runnable(java.lang.Runnable)接口：**

Runnable对象可以作为参数传递（javax.swing.SwingUtilities, java.util.Timer, java.util.concurrent.Executor），可以被放入队列中，可以跨越网络传输，也可以被保存至文件中。

![avatar](.//pic//多态Request.png)

** java.util.concurrent：**

ThreadPoolExecutor(int corePoolSize,
						int maximumPoolSize,
                        long keepAliveTime,
                        TimeUnit unit,
                        BlockingQueue<Runnable> workQueue)

特定场景的具体实现：

Executors.newFixedThreadPool(nThreads);

Executors.newCachedThreadPool();

Executors.newScheduledThreadPool(corePoolSize);

Executors.newSingleThreadExecutor();

# 9. Future

![avatar](.//pic//Future.png)

Host通过Future访问realData, 提供了阻塞功能。

相对于 Per-Message不仅提高了响应时间，还可以得到模拟异步处理结果的返回值。

**java.util.concurrent包：**

![avatar](.//pic//Callable Future FutureTask.png)

java.util.concurrent.Callable接口是含有返回值的多线程调用。

java.util.concurrent.Future接口

java.util.concurrent.FutureTask类实现了Future接口

# 10. Two-Phase Termination

![avatar](.//pic//Two-Phase Termination.png)

使用标志表示线程将要结束是不够的，当线程处于sleep和wait状态的时候，线程是不会中断的。使用interrupt方法可以在这两种情况下线程立即退出。

Thread方法中的join()可以等待程序终止，getState()方法获得程序是否是终止的状态，isAlive()方法判断当前的线程的状态是否已经终止。

** java.util.concurrent.ExecutorService接口中的方法：**

> isShutdown()：如果方法已经被调用，则会返回true，但是即是返回true，程序也不会终止，线程可能处在正在停止中。

> isTermination()：判断线程是否已经停止，如果停止，则会返回true。

**捕获程序整体的终止：**
 
> 未捕获异常的处理器：则线程不会输出调用堆栈而直接退出。

```
	Thread.setDefaultunCaughtExceptionHandler(
		new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread thread, Throwable exception){...}
		}
	);
```

> 退出钩子：java虚拟机退出时（System.exit()或者全部非守护线程终止）启动的线程。

```
    Runtime.getRuntime().addShutdownHook(
        new Thread(){
            public void run(){...}
        }
    
    );
```
**优雅地终止线程：**

> 1）安全地终止：接受到终止请求后不会立即进入终止。使用标志为确定程序需要终止。

> 2）必定会进行终止：中断可中断的wait，使用interrupt。为了确保异常也会执行终止处理，需要使用try{...}finally{...}

> 3）发出终止请求后尽快进入终止处理：中断可中断的sleep，使用interrupt。

**中断状态与InterruptException异常转换**

中断状态 -> InterruptException异常：

```
	if(Thread.currentThread.isInterrupted()){
		throw new InterruptException();
	}
```

InterruptException异常  ->  中断状态：

```
	try{
		Thread.sleep(1000);
	} catch(InterruptException e) {
		Thread.currentThread().interrupt();
	}
```

InterruptException异常 -> InterruptException异常：

```
	InterruptException saveException = null;
	...
	try{
		Thread.sleep(1000);
	} catch(InterruptException e) {
		saveException = e;
	}
	...
	if(saveException != null)
		throw saveException;
```

**java.util.concurrent包与线程同步：**

> java.util.concurrent.CountDownLatch:

await()方法阻塞所有线程在此处。

> java.util.concurrent.CyclicBarrier:

可以周期性地阻塞线程，等到到指定数目的线程之后，会刷新CyclicBarrier的值

# 11. Thread-Specific Storage

![avatar](.//pic//Thread Specific Storage.png)