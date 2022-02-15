package com.app.test7_warapper_classes;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Locale;

public interface BigDecimalExamples 
{
	public static void main(String... a)
	{
		BigDecimal price = BigDecimal.valueOf(12.99);
		BigDecimal taxRate = BigDecimal.valueOf(0.2);
		BigDecimal tax = price.multiply(taxRate);
		price = price.add(tax).setScale(2, RoundingMode.HALF_UP);
		System.out.println(price);
		
		System.out.println();
		
		
		BigInteger price2 = BigInteger.valueOf(99);
		BigInteger intT = price2.add(BigInteger.valueOf(3));
		
		System.out.println(intT+" " +price2.floatValue());
		
	}

}
