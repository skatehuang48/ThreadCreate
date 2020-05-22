package com.thread.create;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池创建线程：
 *  不允许使用Executors创建线程池
 *   public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
 *  FixedThreadPool和SingleThreadPool允许等待的请求队列是Integer.MAX_VALUE 可能导致内存溢出。
 *  而CachedThreadPool和shceduledThreadPool允许创建的线程数量是Integer.MAX_VALUE 同样会出现内存溢出。
 */
public class ThreadPool {

	public static void main(String[] args) {
		//ThreadPoolExecutor父类AbstractExecutorService实现了ExecutorService接口
		//不允许使用Executors创建线程池，他会默认线程池大小和线程等待的请求队列大小，会造成OOM(内存溢出)
		//避免使用Executors创建线程池主要是为了避免其中的默认实现，可以改用ThreadPoolExecutor构造方法指定参数即可.
		//需要指定核心线程池的大小、最大线程池的数量、保持存活的时间、等待队列容量的大小。
		//在这种情况下一旦提交的线程数超过当前可用的线程数时就会抛出拒绝执行的异常 java.util.concurrent.RejectedExecutionException 有界队列已经满了便无法处理新的任务。
		BlockingQueue<Runnable> quee = new ArrayBlockingQueue<>(10);
		ExecutorService executorService = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS, quee);
		for (int i = 0; i < 4; i++) {
			final String threadName = "runnable--线程--"+i;
			executorService.execute(new Runnable() {
				public void run() {
					System.out.println(threadName);
				}
			});
		}
		for (int i = 0; i < 4; i++) {
			final int n = (i+1)*2;
			final String threadName = "futureTask--线程--"+n;
			FutureTask<String> ft = new FutureTask<String>(new Callable<String>() {

				@Override
				public String call() throws Exception {
					System.out.println(threadName);
					return "返回值："+n;
				}
			});
			executorService.submit(ft);
			try {
				System.out.println(ft.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
