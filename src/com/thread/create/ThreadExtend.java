package com.thread.create;
/**
 * ͨ���̳�Thread�����߳�
 * @author Administrator
 *
 */
public class ThreadExtend extends Thread{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		System.out.println(Thread.currentThread().getName()+"-------------------------------------");
	}
	public static void main(String[] args) {
		Thread t = new ThreadExtend();
		t.setName("AAA");
		t.start();
	}
}
