package com.app.test19_concurrency;

import java.util.ArrayList;
import java.util.List;

public class IntrinsicLocks {

	public static void main(String[] args) {
		new IntrinsicLocks();
	}
	
	public IntrinsicLocks()
	{
		for(int i = 0; i < 10; i ++)
		{
			new Thread(r).start();
		}
	}
	
	Runnable r = () -> {
		List<String> list = new ArrayList<>();
		String name = Thread.currentThread().getName();
		for(int i = 0; i < 10; i++) {
			/*
			 *  solo un hilo a la vez puede agregar elementos a la lista
			 *  Y evitamos que la lista estq corrupta
			 */
			synchronized (list) {
				list.add(name + " " + i);
				System.out.println(name + " " + i);
			}
		}
	};
}
