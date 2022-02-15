package com.app.structures;

import java.util.ArrayList;
import java.util.List;

public class Lists {

  public static void main(String[] args) {
    List<String> colorsUnmodifiable = List.of("blue", "yellow", "grey");
    colorsUnmodifiable.add("green");

    // List<String> colors = new ArrayList();
    ArrayList<String> colors = new ArrayList();
    colors.add("blue");
    colors.add("purple");
    colors.add("yellow");
//    colors.add(1);
//    colors.add(new Object());

    System.out.println(colors.size());
    System.out.println(colors.contains("yellow"));
    System.out.println(colors.contains("grey"));
    System.out.println(colors);

    System.out.println();
    for (String color : colors) {
      System.out.println(color);
    }

    System.out.println();
    colors.forEach(System.out::println);

    System.out.println();
    for (int i = 0; i < colors.size(); i++) {
      System.out.println(colors.get(i));
    }
  }
}
