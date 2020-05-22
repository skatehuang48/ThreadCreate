package com.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ͨ��ʵ��Callable�ӿں�ʹ��future�����߳�
 * ���裺
 * 1.�½���ʵ��Callable�ӿڣ�Callable�ķ�����call�����ķ���ֵ���ͣ�
 * 2.�½�FutureTaskʵ����Callable������ʵ����Ϊ����
 * 3.new Thread(FutureTaskʵ��),�ٵ���.start()�����߳�
 * 4.FutureTask.get()��ȡcall�ķ���ֵ
 * @author Administrator
 *
 */
public class ThreadCallable implements Callable<String>{

	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		for(int i = 0 ; i < 5 ; i++){
			Thread.sleep(1000);
			System.out.println(Thread.currentThread().getName()+"---"+i);
		}
		return Thread.currentThread().getName();
	}

	public static void main(String[] args) {
		FutureTask<String> ft = new FutureTask<String>(new ThreadCallable());
		Thread t = new Thread(ft);
		t.setName("oooooo");
		t.start();
		try {
			System.out.println("callable�ӿڷ���ֵ"+ft.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
