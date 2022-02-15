/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MercadoLibre.Service.IsMutant;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MutantTest {

    public MutantTest() {
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
    public void testIsMutantTrue() {

        //los comentarios son las 3 etapas de testing GIVEN Dado el caso WHEN Cundo ocurre THEN el resultado
        //given
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        assertTrue(Mutant.isMutant(dna));
    

    }

    @Test
    public void testIsMutantFalse() {

        String[] dna = {"TTGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"};
        assertFalse(Mutant.isMutant(dna));

    }

 
    
   

}
