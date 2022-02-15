package com.tdd.app;

import com.tdd.app.models.NumerosRomanos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class NumerosRomanosTest
{
    private NumerosRomanos numerosRomanos = new NumerosRomanos();

    @Test
    public void pasarUnoARomanos(){
        String romano = numerosRomanos.convertirARomanos(1);
        assertEquals(romano, "I");
    }

    @Test
    public void pasarDosARomanos(){
        String romano = numerosRomanos.convertirARomanos(2);
        assertEquals(romano, "II");
    }

    @Test
    public void pasarTresARomanos(){
        String romano = numerosRomanos.convertirARomanos(3);
        assertEquals(romano, "III");
    }

    @Test
    public void pasarCuatroARomanosSuccess(){
        String romano = numerosRomanos.convertirARomanos(4);
        assertEquals(romano, "IV");
    }

    @Test
    public void pasarCuatroARomanosFail(){
        String romano = numerosRomanos.convertirARomanos(4);
        assertNotEquals(romano, "III");
    }

    @Test
    public void palindromo(){
        String palabra = "Anita lava la tina";
        String resultado = numerosRomanos.palindromo(palabra);
        assertEquals(resultado, "anit al aval atin");
    }

    @Test
    public void test(){
        String palabra = "palabra";
        String[] arrayPal = new String[palabra.length()];
        System.out.println(palabra.length());

        for(int i = 0; i < palabra.length(); i++){
            System.out.println(palabra.charAt(i));
            arrayPal[i] = String.valueOf(palabra.charAt(i));
        }
        Arrays.stream(arrayPal).forEach(System.out::println);
    }

}
