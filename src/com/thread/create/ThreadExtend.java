package com.thread.create;
/**
 * 通过继承Thread创建线程
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
