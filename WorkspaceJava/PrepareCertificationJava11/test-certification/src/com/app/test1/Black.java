package com.app.test1;

public interface Black {
	  default void getColor(){
	    System.out.print("Black");
	  }
	}