package com.app.test20_protect_sensitive_data;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class TestProtectData {
	public static void main(String[] args) {
		new TestProtectData();
	}
	
	public TestProtectData() {
		protectData("mi name is Cristian");
	}
	
	void protectData(String text)
	{
		try 
		{
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] digest = md.digest(text.getBytes());
			String hash = (new BigInteger(1, digest)).toString(16);
			System.out.println(hash);
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}	
	}
}
