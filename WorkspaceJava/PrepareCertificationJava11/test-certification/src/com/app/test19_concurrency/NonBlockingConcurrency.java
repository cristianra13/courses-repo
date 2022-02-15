package com.app.test19_concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class NonBlockingConcurrency {

	public static void main(String[] args) {
		List<String> lista = new ArrayList<>();
		lista.add("Uno");
		lista.add("Dos");
		lista.add("Tres");
		
		List<String> copyLista = new CopyOnWriteArrayList<>(lista);
		copyLista.add("Cuatro");
		
		System.out.println(lista);
		System.out.println(copyLista);

	}
}
