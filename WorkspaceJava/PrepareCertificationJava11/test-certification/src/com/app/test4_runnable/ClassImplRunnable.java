package com.app.test4_runnable;

public class ClassImplRunnable implements Runnable 
{

	@Override
	public void run() {
		System.out.println("Run");
	}
	
	public void start() {
		System.out.println("Start");
	}
	
	public static void main(String args [])
	{
		new Thread(new ClassImplRunnable()).start();
	}
}

