package test;

import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Test1
{
    // variables de prueba
    String texto = " este, es, un, texto, de, prueba ";

    /*
        Algunos metodos de Java 11
     */
    public void isEmpty(){
        var text = "   ";
        System.out.println(text.isBlank());

        text = "Hi";
        System.out.println(texto.isBlank());
    }

    public void repetir(){
        var repetido = texto.repeat(3);
        System.out.println(repetido);
    }

    public void convertirALista(){
        var lista = texto.lines().collect(Collectors.toList());
        System.out.println(lista);
    }

    public void removerEspacions(){
        System.out.println(texto.strip());
        System.out.println(texto.stripLeading());
        System.out.println(texto.stripTrailing());
        System.out.println(texto.trim());
    }

    public static void main(String args[])
    {
        Test1 test = new Test1();

        /*test.isEmpty();
        test.repetir();
        test.convertirALista();
        test.removerEspacions();*/

        Lambdas lambdas = new Lambdas();
        //lambdas.probar("IMPAR");
        lambdas.sumar();

    }
}
