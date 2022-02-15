package com.app.test5_herencia;

final class ProtectedClass implements Runnable 
{

	@Override
	public void run() {
		System.out.println("go");
		
	}
	
	
	public static void main(String[] args)
	{
		Thread t = new Thread(new ProtectedClass());
		t.run();
		t.run();
		t.start(); 
	}
	
}
