/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.MercadoLibre.Service.Utils;

import java.util.Random;

/**
 *
 * @author Alejandro Greggio
 */
public class createDna {

    static public String[] newDna() {
        Random random = new Random(System.currentTimeMillis());
        // Producir nuevo int aleatorio entre 0 y 99
      
        String[] dna = new String[random.nextInt(8) + 4];

        for (int i = 0; i < dna.length; i++) {
            dna[i] = "";
            for (int j = 0; j < dna.length; j++) {

                //crea letras (A,T,C,G)
                switch (random.nextInt(4)) {

                    case 0:
                        dna[i] += "A";
                        break;
                    case 1:
                        dna[i] += "T";
                        break;
                    case 2:
                        dna[i] += "C";
                        break;
                    case 3:
                        dna[i] += "G";
                        break;
                }

            }

        }

        return dna;

    }
}
