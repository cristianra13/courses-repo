/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.mercadolibre;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Pablo
 */
public class GeneradorAleatorio {
    
    /*public static String[] MatrizAleatoria(int Dimension) {


        String[] cadenaAdn = new String[Dimension];

        Random aleatorio = new Random();

        String letrasADN = "ATCG";
        for (int j = 0; j < Dimension; j++) {
            StringBuilder buffer = new StringBuilder();

            for (int i = 0; i < Dimension; i++) {
                buffer.append(letrasADN.charAt(aleatorio.nextInt(letrasADN.length())));
            }
            cadenaAdn[j] = buffer.toString();
        }
        return cadenaAdn;
    }*/
   
    public static List<String> ListaMatrizAleatoria(int Dimension) {
        
        List<String> cadena= new ArrayList<String>();     

        Random aleatorio = new Random();

        String letrasADN = "ATCG";
        for (int j = 0; j < Dimension; j++) {
            StringBuilder buffer = new StringBuilder();

            for (int i = 0; i < Dimension; i++) {
                buffer.append(letrasADN.charAt(aleatorio.nextInt(letrasADN.length())));
            }
            
            cadena.add(buffer.toString());
        }
        
        return cadena;
    }
    
}


 