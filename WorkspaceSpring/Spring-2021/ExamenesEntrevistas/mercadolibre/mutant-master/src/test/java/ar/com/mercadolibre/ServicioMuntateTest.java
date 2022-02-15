package ar.com.mercadolibre;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.junit.Test;

public class ServicioMuntateTest {

	/*
	 * Valida que la Matriz de Prueba generada sea de la dimensión solicitada
	 */	 
	@Test
	public void testObtenerMatrizPrueba() {
		
		ServicioMuntate nuevoServicio = new ServicioMuntate();
		
		AdnPOJO dna = new AdnPOJO();
		
		
		dna = nuevoServicio.ObtenerMatrizPrueba(4);	

		
		List<String> cadenaAdn = dna.getDna();		
	
		
		assertEquals(4, cadenaAdn.size());		
	
	}
	
	/*Prueba ProcesarAdn caso exitoso para obtener response = 200
	 * */
	@Test
	public void testProcesarAdn200 () {
		
		ServicioMuntate nuevoServicio = new ServicioMuntate();
		
		 
		
		 AdnPOJO dna = new AdnPOJO();
	        
	        List<String> lista = new ArrayList();
	        
	        lista.add("ACTGAAGACA");
	        lista.add("TGCAGGGCTT");
	        lista.add("CAGTATAGTT");
	        lista.add("ACTCAAGACT");
	        lista.add("TGCAGGGCTT");
	        lista.add("CACTATAGTC");
	        lista.add("ACTGAAGACA");
	        lista.add("TGCAGGGCTA");
	        lista.add("CAGTATAGTA");;
	        lista.add("ATTCGACCCA");
	        
	        dna.setDna(lista);
		
		Response respuesta = nuevoServicio.ProcesarAdn(dna);
		
		assertEquals(200, respuesta.getStatus());		
	
	}
	
	/*
	*/
	
	/*Prueba ProcesarAdn caso NO exitoso para obtener response = 403
	 * */
	@Test
	public void testProcesarAdn403 () {
		
		ServicioMuntate nuevoServicio = new ServicioMuntate();		
		
		AdnPOJO dna = new AdnPOJO();
	    
	    List<String> lista = new ArrayList();
	    
	    lista.add("TCGT");
	    lista.add("TGGC");
	    lista.add("TTAT");
	    lista.add("CGAG");        
	    
	    dna.setDna(lista);
		
		Response respuesta = nuevoServicio.ProcesarAdn(dna);
		
		assertEquals(403, respuesta.getStatus());		
	
	}
	
	/*Prueba testTestMutantApi con matriz de 100 x 100
	 * */
	@Test
	public void testTestMutantApi  () {
		
		ServicioMuntate nuevoServicio = new ServicioMuntate();		
				
		Response respuesta = nuevoServicio.TestMutantApi(100);
		
		assertEquals(200, respuesta.getStatus());		
	
	}
	
	/*Prueba Stats
	 * */
	@Test
	public void testStats() {
		
		ServicioMuntate nuevoServicio = new ServicioMuntate();	
		
		StatsPOJO estadisticas = new StatsPOJO();	
			
		assertNotNull(nuevoServicio.Stats());
		 
	
	}	

	
	
}
