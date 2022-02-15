package com.app.functional.v2_superfunctions_classes;

import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) {
    new Main();
  }

  public Main() {
    // TODO
    // 1. - Create integer list
    List<Integer> numbers = createList();
    System.out.println(numbers);
    // 2. - Get Only even
    List<Integer> even = filterEvens(numbers);
    System.out.println(even);
    // 3. - Calcule square of each number
    List<Integer> squares = convertToSquare(even);
    System.out.println(squares);
    // 4. - Show square in display
    List<Integer> showNumbers = showList(squares);
    // 5. - Get square total
    int total =  getSquaresSum(showNumbers);
    System.out.println("Total square sum: " + total);
    
  }

  private List<Integer> createList() {
    // return Arrays.asList(0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144);
    // Inmutable list
    return List.of(0, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144);
  }

  private List<Integer> filterEvens(List<Integer> numbers) {
    List<Integer> result = new ArrayList<>();
    for (int number : numbers) {
      if (number % 2 == 0) {
        result.add(number);
      }
    }

    return result;
  }

  private List<Integer> convertToSquare(List<Integer> even) {
    List<Integer> result = new ArrayList<>();
    for (int number : even) {
      result.add(number * number);
    }
    return result;
  }

  private List<Integer> showList(List<Integer> numbers) {
    for (int number : numbers) {
      System.out.println(number);
    }
    return numbers;
  }

  private int getSquaresSum(List<Integer> numbers) {
    int total = 0;
    for (int number : numbers) {
      total += number;
    }
    return total;
  }
}
