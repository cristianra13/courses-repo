/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MercadoLibre.Service.IsMutant;

import com.MercadoLibre.Service.Utils.ValidDna;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;

/**
 *
 * @author Alejandro Greggio
 */
public class ValidDnaTest {

    public ValidDnaTest() {
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

    /**
     * Test of isValidDna method, of class ValidDna.
     */
    @Test
    public void testIsValidTrueDna() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGJ", "CCCCTA", "TCACTG"};
        IDna mockDna = Mockito.mock(IDna.class);
        Mockito.when(mockDna.IDna()).thenReturn(dna);
        assertTrue(ValidDna.isValidDna(mockDna.IDna()));
        Mockito.verify(mockDna);
    }
    
    @Test
    public void testIsValidFalseDna() {
        String[] dna = {"TTHCGA", "CAG1GC", "TTAGT", "AGAAGG", "CCTCTA", "TCACTG"};
        assertFalse(ValidDna.isValidDna(dna));
    }
    
    
}
