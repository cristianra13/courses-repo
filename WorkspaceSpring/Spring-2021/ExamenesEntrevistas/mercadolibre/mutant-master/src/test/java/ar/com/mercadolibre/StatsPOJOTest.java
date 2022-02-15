package ar.com.mercadolibre;

import static org.junit.Assert.*;

import org.junit.Test;

public class StatsPOJOTest {

	@Test
	public void testgetCount_humnan_dna() {
		
		StatsPOJO nuevoStats = new StatsPOJO();
		
		nuevoStats.setCount_humnan_dna(23);
		
		assertEquals(23, nuevoStats.getCount_humnan_dna());
		 
	}
	
	@Test
	public void testgetCount_mutant_dna() {
		
		StatsPOJO nuevoStats = new StatsPOJO();
		
		nuevoStats.setCount_mutant_dna(23); 
		
		
		assertEquals(23, nuevoStats.getCount_mutant_dna());
		 
	}

}
