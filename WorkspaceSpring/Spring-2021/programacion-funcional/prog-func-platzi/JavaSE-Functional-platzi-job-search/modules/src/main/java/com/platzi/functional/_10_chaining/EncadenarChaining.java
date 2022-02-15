package com.platzi.functional._10_chaining;

public class EncadenarChaining
{
    public static void main(String[] args)
    {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hola que más");


        /*
            Podemos hcaer esto, porque internamente,
            cada uno de sus metodos está mandando a llamar
            y devuelve el mismo objeto.

            Sirve para composición de funciones
         */
        Chainer chainer = new Chainer();
        chainer.sayHi().sayBye();
    }
}

class Chainer
{
    public Chainer sayHi(){
        System.out.println("Hola");
        return this; // con esto retornamos está misma instancia
    }

    public Chainer sayBye(){
        System.out.println("Bye");
        return this;
    }
}
