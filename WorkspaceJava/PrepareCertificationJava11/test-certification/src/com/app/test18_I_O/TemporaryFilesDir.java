package com.app.test18_I_O;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TemporaryFilesDir {
	
	public static void main(String[] args) {
		
		Path p1;
		try 
		{
			// creamos el directorio temporal
			p1 = Files.createTempDirectory("TEMP");
			System.out.println(p1.getParent()); // ruta del directorio
			
			// creamos el archivo temporal 
			Path p2 = Files.createTempFile(p1, "TEMP", ".tmp");
			System.out.println(p2.getFileName());
			
			
			// eliminamos el directorio y el archivo
			if(Files.deleteIfExists(p2))
				System.out.println("archivo eliminado");
			
			if(Files.deleteIfExists(p1))
				System.out.println("directorio eliminado");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		
	}

}
