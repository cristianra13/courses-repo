package com.app.test19_concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicActions {

	public static void main(String[] args) {
		new AtomicActions();
	}
	
	public AtomicActions()
	{
		new Thread(r).start();
		new Thread(r).start();
	}
	
	Runnable r = () -> {
		Shared2 s = new Shared2();
		int y = 0;
		while(y < 10)
			y =  s.x.incrementAndGet();
		System.out.println("y: " + y);
	};
}

class Shared2 {
	public AtomicInteger x = new AtomicInteger(0);
}