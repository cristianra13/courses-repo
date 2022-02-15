package com.tdd.app.models;

public class NumerosRomanos
{
    public String convertirARomanos(int numero){
        switch(numero){
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
            case 4:
                return "IV";
        }
        return null;
    }
    public String sumarI( int numero){
        String resultado = "";
        for(int i = 0; i < numero; i++){
            resultado += "I";
        }
        return resultado;
    }

    public String palindromo(String palabra){
        char[] newPal = palabra.toCharArray();
        String palin = "";
        for (int i = newPal.length-1; i > 0; i--){
            palin += newPal[i];
        }
        return palin;
    }
}
