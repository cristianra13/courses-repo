package com.app.test20_protect_sensitive_data;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class ProtectData {

	public static void main(String[] args) {
		new ProtectData();
	}
	
	public ProtectData() {
		encryptData("mi nombre es cristian");
	}
	
	void encryptData(String text){
		
		try 
		{
			SecretKey key = KeyGenerator.getInstance("AES").generateKey();
			Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] value = text.getBytes();
			byte[] encryptedValue = cipher.doFinal(value);
			System.out.println(encryptedValue);
			
			GCMParameterSpec ps = cipher.getParameters().getParameterSpec(GCMParameterSpec.class);
			cipher.init(Cipher.DECRYPT_MODE, key, ps);
			byte[] decryptedValue = cipher.doFinal(encryptedValue);
			System.out.println(decryptedValue.toString());
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		} 
		catch (NoSuchPaddingException e) 
		{
			e.printStackTrace();
		} 
		catch (InvalidKeyException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalBlockSizeException e) 
		{
			e.printStackTrace();
		} 
		catch (BadPaddingException e) 
		{
			e.printStackTrace();
		}
		catch (InvalidParameterSpecException e) 
		{
			e.printStackTrace();
		} 
		catch (InvalidAlgorithmParameterException e) 
		{
			e.printStackTrace();
		}
	}

}
