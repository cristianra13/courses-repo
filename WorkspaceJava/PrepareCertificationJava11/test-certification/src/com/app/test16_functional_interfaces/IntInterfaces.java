package com.app.test16_functional_interfaces;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.UnaryOperator;

public class IntInterfaces 
{
	public static void main(String[] args) 
	{
		new IntInterfaces();
	}
	
	public IntInterfaces()
	{
		var numOne = 12;
		var numTwo = 16;
		var numThree = 2;
		
		double resDob = intFunc.apply(3);
		System.out.println(resDob);
		
		boolean resIntPred = intPred.test(1);
		System.out.println(resIntPred);
		
		double resIntToLong = intToLong.applyAsDouble(33);
		System.out.println(resIntToLong);
		
		int resUnaryOp = substract.apply(3);
		System.out.println(resUnaryOp);
	}
	
	
	IntFunction<Double> intFunc = (var i) -> Double.valueOf(i * i);
	
	IntPredicate intPred = (var x) -> x == 1;
	
	IntToDoubleFunction intToLong = (var x) -> x * x;
	
	UnaryOperator<Integer> substract = x -> (x * x) - (x + x);
}
