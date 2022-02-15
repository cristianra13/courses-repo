/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.mercadolibre;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Pablo
 */
public class DetectorAdnTest {
    
    public DetectorAdnTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testIsMutant() {
        /*
        {
            "dna": [
                "CACTAAGACA",
                "AGAGGGGCTG",
                "CTGTATAGTC",
                "TGCTAACCGA",
                "GCCGGATAGA",
                "GCCTTCATGG",
                "ACGCACAAAT",
                "TGAGCATTTA",
                "GGGTAATATG",
                "ATTCGACCCG"
            ]
        }
        */
        System.out.println("IsMutant");
        
        System.out.println("Encontró cadena G en Horizontal:1,3");
        System.out.println("Encontró cadena G en DiagonalInversa:1,3");
        
        AdnPOJO dna = new AdnPOJO();
        
        List<String> lista = new ArrayList();
        
        lista.add("CACTAAGACA");
        lista.add("AGAGGGGCTG");
        lista.add("CTGTATAGTC");
        lista.add("TGCTAACCGA");
        lista.add("GCCGGATAGA");
        lista.add("GCCTTCATGG");
        lista.add("ACGCACAAAT");
        lista.add("TGAGCATTTA");
        lista.add("GGGTAATATG");
        lista.add("ATTCGACCCG");
        
        dna.setDna(lista);
        
        DetectorAdn instance = new DetectorAdn();
        boolean expResult = true;
        boolean result = instance.IsMutant(dna);
        assertEquals(expResult, result);
     
    }
    
    /*
      Matriz no cuadrada
    */
    @Test
    public void testIsMutantNoCuadrada() {
        
        /*
        {
            "dna": [
                "CACTAAGACA",
                "AGAGGGGCTG",
                "CTGTATAGTC",
                "TGCTAACCGA",
                "GCCGGTAGA",
                "GCCTTCATGG",
                "ACGCACAAAT",
                "TGAGCATTTA",
                "GGGTAATATG",
                "ATTCGACCCG"
            ]
        }
        */
        
        System.out.println("****testIsMutantNoCuadrada***");
        
    
                
        AdnPOJO dna = new AdnPOJO();
        
        List<String> lista = new ArrayList();
        
        lista.add("CACTAAGACA");
        lista.add("AGAGGGGCTG");
        lista.add("CTGTATAGTC");
        lista.add("TGCTAACCGA");
        lista.add("GCCGGTAGA");
        lista.add("GCCTTCATGG");
        lista.add("ACGCACAAAT");
        lista.add("TGAGCATTTA");
        lista.add("GGGTAATATG");
        lista.add("ATTCGACCCG");
        
        dna.setDna(lista);
        
        DetectorAdn instance = new DetectorAdn();
        boolean expResult = false;
        boolean result = instance.IsMutant(dna);
        assertEquals(expResult, result);
     
    }
    
    /*
    *Valida Cadena con un caracter no valido (alfabetico)
    */
    @Test
    public void testIsMutantCaracterNoGen() {
       
        /*
            {
                "dna": [
                    "CACTAAGACA",
                    "AGAGGGGCTG",
                    "CTGTATAGTC",
                    "TGCTAACCGA",
                    "GCCGGATAGA",
                    "GCCTTCATGG",
                    "ACGCACAAAT",
                    "TGAGCATTTA",
                    "GGGTAATATG",
                    "ATTCGxCCCG"
                ]
            }
        */
        System.out.println("****IsMutant testIsMutantCaracterNoGen****");
        
        
                
        AdnPOJO dna = new AdnPOJO();
        
        List<String> lista = new ArrayList();
        
        lista.add("CACTAAGACA");
        lista.add("AGAGGGGCTG");
        lista.add("CTGTATAGTC");
        lista.add("TGCTAACCGA");
        lista.add("TGCTAACCGA");
        lista.add("GCCTTCATGG");
        lista.add("ACGCACAAAT");
        lista.add("TGAGCATTTA");
        lista.add("GGGTAATATG");
        lista.add("ATTCGCvCCG");
        
        dna.setDna(lista);
        
        DetectorAdn instance = new DetectorAdn();
        boolean expResult = false;
        boolean result = instance.IsMutant(dna);
        assertEquals(expResult, result);
     
    }
    
    @Test
    public void testIsMutantMinusculas() {
        System.out.println("****IsMutant testIsMutantMinusculas****");
 
        AdnPOJO dna = new AdnPOJO();
        
        List<String> lista = new ArrayList();
        
        lista.add("CACTAAGACA");
        lista.add("AGAggggCTG");
        lista.add("CTgTATAGTC");
        lista.add("TgCTAACCGA");
        lista.add("gCCGGATAGA");
        lista.add("GCCTTCATGG");
        lista.add("ACGCACAAAT");
        lista.add("TGAGCATTTA");
        lista.add("GGGTAATATG");
        lista.add("ATTCGACCCG");
        
        dna.setDna(lista);
        
        DetectorAdn instance = new DetectorAdn();
        boolean expResult = true;
        boolean result = instance.IsMutant(dna);
        assertEquals(expResult, result);
     
    }
    
    @Test
    public void testIsMutantNula() {
        System.out.println("****IsMutant  testIsMutantNula****");
        
                
        AdnPOJO dna = new AdnPOJO();        
        
        DetectorAdn instance = new DetectorAdn();
        boolean expResult = false;
        boolean result = instance.IsMutant(dna);
        assertEquals(expResult, result);
     
    }
    
    /*
     * Valida matriz minina Humana
     */
    @Test
    public void testIsMutantMatrizMinimaHumana() {
        System.out.println("****IsMutant Prueba Matriz minima 4x4 No Humana****");
        /*
         {
            "dna": [
                "TCGT",
                "TGGC",
                "TTAT",
                "CGAG"
            ]
        }
        */
        AdnPOJO dna = new AdnPOJO();
        
        List<String> lista = new ArrayList();
        
        lista.add("TCGT");
        lista.add("TGGC");
        lista.add("TTAT");
        lista.add("CGAG");        
        
        dna.setDna(lista);
        
        DetectorAdn instance = new DetectorAdn();
        boolean expResult = false;
        boolean result = instance.IsMutant(dna);
        assertEquals(expResult, result);
     
    }
    
    @Test
    public void testIsMutantMatrizMinimaPassed() {
        System.out.println("****IsMutant Prueba Matriz minima 4x4 Passed****");
        /*
         {
            "dna": [
                "TCGT",
                "TGTC",
                "TTAT",
                "TGAG"
            ]
        }
        */
        AdnPOJO dna = new AdnPOJO();
        
        List<String> lista = new ArrayList();
        
        lista.add("TCGT");
        lista.add("TGTC");
        lista.add("TTAT");
        lista.add("TGAG");        
        
        dna.setDna(lista);
        
        DetectorAdn instance = new DetectorAdn();
        boolean expResult = true;
        boolean result = instance.IsMutant(dna);
        assertEquals(expResult, result);
     
    }
    
    @Test
    public void testIsMutantMatrizMinimaPassedExtremo() {
        System.out.println("****IsMutant Prueba Matriz minima 4x4 Passed con cadena en extremo derecho y diagonal inversa****");
        /*
         {
            "dna": [
                "TCGT",
                "AGTT",
                "TTAT",
                "TGAT"
            ]
        }
        */
        AdnPOJO dna = new AdnPOJO();
        
        List<String> lista = new ArrayList();
        
        lista.add("TCGT");
        lista.add("AGTT");
        lista.add("TTAT");
        lista.add("TGAT");        
        
        dna.setDna(lista);
        
        DetectorAdn instance = new DetectorAdn();
        boolean expResult = true;
        boolean result = instance.IsMutant(dna);
        assertEquals(expResult, result);
     
    }
    
    @Test
    public void testIsMutantMatrizMinimaUnSoloGen() {
        System.out.println("****IsMutant Prueba Matriz minima 4x4 con un solo GEN****");
        /*
         {
            "dna": [
                "TCGT",
                "AGTC",
                "TTAT",
                "TGAG"
            ]
        }
        */
        AdnPOJO dna = new AdnPOJO();
        
        List<String> lista = new ArrayList();
        
        lista.add("TCGT");
        lista.add("TGGC");
        lista.add("TTAT");
        lista.add("CGAG");        
        
        dna.setDna(lista);
        
        DetectorAdn instance = new DetectorAdn();
        boolean expResult = false;
        boolean result = instance.IsMutant(dna);
        assertEquals(expResult, result);
     
    }
    
    @Test
    public void testIsMutantExtremoDerechoVertical() {
        
        
        /*
        {
            "dna": [
                "ACTGAAGACA",
                "TGCAGGGCTT",
                "CAGTATAGTT",
                "ACTGAAGACT",
                "GCCGGATAGA",
                "GCCTTCATGG",
                "ACGCACAAAT",
                "TGAGCATTTA",
                "GGGTAATATG",
                "ATTCGACCCG"
            ]
        }
        */
        System.out.println("****IsMutant testIsMutantExtremoDerechoVertical****");
        
              
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
        
        DetectorAdn instance = new DetectorAdn();
        boolean expResult = true;
        boolean result = instance.IsMutant(dna);
        assertEquals(expResult, result);
     
    }
    
     /*
    *Valida Cadena con un caracter no valido (numerico)
    */
    @Test
    public void testIsMutantCaracterNumerico() {
       
        /*
            {
                "dna": [
                    "CACTAAGACA",
                    "AGAGGGGCTG",
                    "CTGTATAGTC",
                    "TGCTAACCGA",
                    "GCCGGATAGA",
                    "GCCTTCATGG",
                    "ACGCACAAAT",
                    "TGAGCATTTA",
                    "GGGTAATATG",
                    "ATTCG1CCCG"
                ]
            }
        */
        System.out.println("****IsMutant testIsMutantCaracterNumerico****");
        
        
                
        AdnPOJO dna = new AdnPOJO();
        
        List<String> lista = new ArrayList();
        
        lista.add("CACTAAGACA");
        lista.add("AGAGGGGCTG");
        lista.add("CTGTATAGTC");
        lista.add("TGCTAACCGA");
        lista.add("TGCTAACCGA");
        lista.add("GCCTTCATGG");
        lista.add("ACGCACAAAT");
        lista.add("TGAGCATTTA");
        lista.add("GGGTAATATG");
        lista.add("ATTCGxCCCG");
        
        dna.setDna(lista);
        
        DetectorAdn instance = new DetectorAdn();
        boolean expResult = false;
        boolean result = instance.IsMutant(dna);
        assertEquals(expResult, result);
     
    }
    
}