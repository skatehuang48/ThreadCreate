package com.thread.create;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 通过实现Callable接口和使用future创建线程
 * 步骤：
 * 1.新建类实现Callable接口（Callable的泛型是call方法的返回值类型）
 * 2.新建FutureTask实例，Callable的子类实例作为传参
 * 3.new Thread(FutureTask实例),再调用.start()启动线程
 * 4.FutureTask.get()获取call的返回值
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
			System.out.println("callable接口返回值"+ft.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
