package com.app.aviacion;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        int[][] arr = new int[4][4];

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = j;
            }
        }

        Arrays.stream(arr).forEach(System.out::println);
    }
}
