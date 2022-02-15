package com.app.test4_runnable;

public class ClassHerencia extends AbstractClass {

	public static void main(String... args) {
		new ClassHerencia().imprimirAlgo("imprimir texto");
	}
	
	@Override
	public void imprimirAlgo(String texto) {
		System.out.println(texto);
	}

	

}
