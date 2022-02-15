package com.app.test19_concurrency;

public class RunnableAsLambda {

	public static void main(String[] args) {
		
		new RunnableAsLambda();
	}
	
	public RunnableAsLambda()
	{
		Thread t1 = new Thread(run);
		Thread t2 = new Thread(run);
		Thread t3 = new Thread(run);
		
		t1.start();
		t2.start();
		t3.start();

//		t1.start(); // esto genera en Exception
	}
	
	Runnable run = () -> System.out.println("Hilo corriendo " + System.currentTimeMillis());
}
