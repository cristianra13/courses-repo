package com.app.test1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MyChannelClass {
	public static void main(String[] args) 
	{
		try (
				FileChannel fcIn = new FileInputStream(args[0]).getChannel();
				FileChannel fcOut = new FileOutputStream(args[1]).getChannel()
			) 
		{
			ByteBuffer buff = ByteBuffer.allocate((int) fcIn.size());
			System.out.println("Output: " + buff.remaining());
		} catch (Exception e) 
		{
			System.out.println(e);
		}
	}
}