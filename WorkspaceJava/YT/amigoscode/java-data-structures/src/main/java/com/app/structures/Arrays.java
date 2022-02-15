package com.app.structures;

import java.util.List;

public class Arrays {

  public static void main(String[] args) {
    String[] colors = new String[5];
    colors[0] = "purple";
    colors[1] = "blue";
    colors[2] = "red";
    colors[3] = "grey";
    colors[4] = "black";

    System.out.println(java.util.Arrays.toString(colors));
    System.out.println(colors[colors.length-1]);

    int[] numbers = {100, 200};
    System.out.println(java.util.Arrays.toString(numbers));
    for (int i = 0; i < numbers.length; i++) {
      System.out.println(numbers[i]);
    }
    System.out.println();

    for (int i = colors.length -1; i >= 0; i--) {
      System.out.println(colors[i]);
    }
    System.out.println();

    for (String color : colors) {
      System.out.println(color);
    }

    System.out.println();
    java.util.Arrays.stream(colors).forEach(System.out::println);

    System.out.println();
    List<String> colorsStr = java.util.Arrays.asList(colors);
  }
}
