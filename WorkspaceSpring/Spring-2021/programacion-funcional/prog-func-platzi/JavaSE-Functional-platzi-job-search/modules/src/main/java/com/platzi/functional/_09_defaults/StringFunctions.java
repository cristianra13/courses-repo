package com.platzi.functional._09_defaults;

public class StringFunctions
{
    public static void main(String[] args)
    {
        StringOperation six = () -> 6;
        six.operate("Texto a imprimir");


        DoOperation operateFive = x -> System.out.println(x);
        operateFive.execute(4, "Programing in home");
    }

}

@FunctionalInterface
interface StringOperation
{
    int getAmount(); // metodo abstracto sin definición

    /*
        Default nos permite definir un cuerpo
        para un método en una interfaz
     */
    default void operate(String texto){
       int x = getAmount();
       while( x-- > 0 ){
           System.out.println(x + " - " + texto);
       }
    }
}

@FunctionalInterface
interface DoOperation {
    void take(String texto); // metodo abstracto

    default void execute(int x, String texto){
        while( x-- > 0 ){
            take(texto);
        }
    }
}
