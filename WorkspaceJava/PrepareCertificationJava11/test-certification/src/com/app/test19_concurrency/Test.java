package com.app.test19_concurrency;

public class Test {

	public static void main(String[] args) {
		
		Lateral lateral = new Lateral();
		new Thread(lateral).start();
		new Thread(lateral).start();
		new Thread(lateral).start();
		new Thread(lateral).start();
		new Thread(lateral).start();
	}
}
