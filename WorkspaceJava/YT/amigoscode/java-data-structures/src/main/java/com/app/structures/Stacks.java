package com.app.structures;

import java.util.Stack;

/**
 * Stack use LIFO -> Last In First Out
 */
public class Stacks {
  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(1);
    stack.push(2);
    stack.push(3);

    System.out.println("Last element of stack: " + stack.peek());
    System.out.println("Stack size: " + stack.size());
    System.out.println("Get last element and remove this: " + stack.pop());
    System.out.println("Stack size: " + stack.size());

    System.out.println(stack.empty());
  }
}
