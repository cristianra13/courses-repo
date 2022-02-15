package com.app.test18_I_O;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class FyleSystemClass 
{
	public static void main(String[] args) {
		
		FileSystem fs = FileSystems.getDefault();
		fs.getFileStores().forEach(s -> System.out.println(s.type() + ' ' + s.name()));
		fs.getRootDirectories().forEach(System.out::println);
		String separator = fs.getSeparator();
		System.out.println(separator);
	}
}
