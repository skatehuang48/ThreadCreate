package com.thread.create;

/**
 * ʵ��runnable�����߳�
 * @author Administrator
 *
 */
public class ThreadRunnable implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName()+"---");
	}
	public static void main(String[] args) throws InterruptedException {
		Thread t = new Thread(new ThreadRunnable());
		t.start();
		t.sleep(1000);
	}

}
