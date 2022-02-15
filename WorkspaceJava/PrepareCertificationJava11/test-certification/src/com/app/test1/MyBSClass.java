package com.app.test1;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MyBSClass 
{
	public static void main(String[] args) 
	{
		byte[] b = new byte[256];
		try (
				FileInputStream fis = new FileInputStream(args[0]); 
				FileOutputStream fos = new FileOutputStream(args[1])) {
				int count = 0;
				int read = 0;
				
				while ((read = fis.read(b)) != -1)
				{
					fos.write(b);
					count += read;
				}
				System.out.println("Output: " + count);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}