/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MercadoLibre.Service.IsMutant;

/**
 *
 * @author Alejandro Greggio
 */
public class NewDna implements IDna{

    @Override
    public String[] IDna() {
        String[] dna = {"TTHCGA", "CAG1GC", "TTAGT", "AGAAGG", "CCTCTA", "TCACTG"};
        return dna;
    }
    
}
