package com.app.test5_herencia.sub;

public class TestHerenciaVarI {
	public int i =0;
	
	public TestHerenciaVarI(String text) {
		i = 2;
	}
}

class Sub extends TestHerenciaVarI{

	// se le debe enviar el valor al contructor padre
	public Sub(String text) {
		super("");
		i = 2;
	}
	
	public static void main(String[] args) {
		Sub sub = new Sub("helolo");
		System.out.println(sub.i);
	}
	
}