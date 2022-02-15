package com.app.test19_concurrency;

public class VolatileVars {

	public static void main(String[] args) {
		Shared shared = new Shared();
		
		new Thread(() -> {
			while(shared.y < 1) {
				int x = shared.x;
			}
		}).start();
		
		new Thread(() -> {
			shared.x = 2;
			shared.y = 2;
		}).start();
	}
}

class Shared {
	public int x;
	public volatile int y;
}
