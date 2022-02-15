package com.app.test18_I_O;

import java.io.IOException;
import java.nio.file.Path;

public class FileSystemPaths {

	public static void main(String[] args) throws IOException {
		
		Path someFile = Path.of("/", "users", "joe", "docs", "some.txt");
		
		Path justSomeFile = someFile.getFileName();
		System.out.println(justSomeFile);
		
		Path docsFolder = someFile.getParent();
		System.out.println(docsFolder);
		
		Path currentFolder = Path.of(".");
		System.out.println(currentFolder);
		
		Path acmeFile = docsFolder.resolve("../pics/acme.jpg");
		System.out.println(acmeFile);
		
		Path otherFile = someFile.resolveSibling("other.txt");
		System.out.println(otherFile);
		
		Path normalizedAcmeFile = acmeFile.normalize();
		System.out.println(normalizedAcmeFile);
		
		Path verifiedPath = acmeFile.toRealPath();
		System.out.println(verifiedPath);
		
		Path betweenSomeAndAcme = someFile.relativize(acmeFile);
		System.out.println(betweenSomeAndAcme);
	}
}
