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
 * �̳߳ش����̣߳�
 *  ������ʹ��Executors�����̳߳�
 *   public static ExecutorService newFixedThreadPool(int nThreads) {
        return new ThreadPoolExecutor(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
 *  FixedThreadPool��SingleThreadPool����ȴ������������Integer.MAX_VALUE ���ܵ����ڴ������
 *  ��CachedThreadPool��shceduledThreadPool���������߳�������Integer.MAX_VALUE ͬ��������ڴ������
 */
public class ThreadPool {

	public static void main(String[] args) {
		//ThreadPoolExecutor����AbstractExecutorServiceʵ����ExecutorService�ӿ�
		//������ʹ��Executors�����̳߳أ�����Ĭ���̳߳ش�С���̵߳ȴ���������д�С�������OOM(�ڴ����)
		//����ʹ��Executors�����̳߳���Ҫ��Ϊ�˱������е�Ĭ��ʵ�֣����Ը���ThreadPoolExecutor���췽��ָ����������.
		//��Ҫָ�������̳߳صĴ�С������̳߳ص����������ִ���ʱ�䡢�ȴ����������Ĵ�С��
		//�����������һ���ύ���߳���������ǰ���õ��߳���ʱ�ͻ��׳��ܾ�ִ�е��쳣 java.util.concurrent.RejectedExecutionException �н�����Ѿ����˱��޷������µ�����
		BlockingQueue<Runnable> quee = new ArrayBlockingQueue<>(10);
		ExecutorService executorService = new ThreadPoolExecutor(3, 5, 10, TimeUnit.SECONDS, quee);
		for (int i = 0; i < 4; i++) {
			final String threadName = "runnable--�߳�--"+i;
			executorService.execute(new Runnable() {
				public void run() {
					System.out.println(threadName);
				}
			});
		}
		for (int i = 0; i < 4; i++) {
			final int n = (i+1)*2;
			final String threadName = "futureTask--�߳�--"+n;
			FutureTask<String> ft = new FutureTask<String>(new Callable<String>() {

				@Override
				public String call() throws Exception {
					System.out.println(threadName);
					return "����ֵ��"+n;
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
