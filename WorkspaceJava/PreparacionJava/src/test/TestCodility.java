package test;

import java.util.Arrays;

public class TestCodility
{
    public static void main(String[] args)
    {
        new TestCodility();
    }

    public TestCodility()
    {
        // data
        int[] firstArray = {3, 8, 9, 7, 6};
        int numberRotations = 1;

        //int result = binaryGap(9);

        int[] result = arrays(firstArray, numberRotations);
        Arrays.stream(firstArray).forEach(System.out::print);
        System.out.println();
        Arrays.stream(result).forEach(System.out::print);
        System.out.println();
    }

    public int binaryGap(int n)
    {
        char[] arrChars = Integer.toBinaryString(n).toCharArray();

        boolean hasGap = false;
        int localBinaryGap = 0;  // contador actual de la brecha
        int maxBinaryGap = 0;

        for(int i = 0; i < arrChars.length; i++)
        {
            if(arrChars[i] == '1')
            {
                if(hasGap)
                {
                    maxBinaryGap = Math.max(maxBinaryGap, localBinaryGap);
                }
                hasGap = true;
                localBinaryGap = 0;
            }
            else {
                localBinaryGap++;
            }
        }
        return maxBinaryGap;
    }

    public int[] arrays(int [] A, int K)
    {

        int[] newArr = new int[A.length];

        /*
            int longitud = A.length;
            K = K % longitud;
            Esto se hace para saber cuantas rotaciones vamos a hacer,
            es decir, si el arreglo tiene 3 elementos y K = 3, el arreglo va a volver a su
            posición original, pasa lo mismo cuando decimos que K = 9, como este es modulo de 3
            entonces va a retornar el arreglo original, con K = K % longitud, cuando este nos devuelva
            un valor diferente a 0, esa va a a ser la cantidad de veces que vamos a rotar
        */
        int longitud = A.length;
        if(longitud == 0) return A;

        K = K % longitud;
        for(int i = 0; i < longitud; i++)
        {
            int pos = (longitud - K + i) % longitud;
            newArr[i] = A[pos];
        }
        return newArr;
    }

}
