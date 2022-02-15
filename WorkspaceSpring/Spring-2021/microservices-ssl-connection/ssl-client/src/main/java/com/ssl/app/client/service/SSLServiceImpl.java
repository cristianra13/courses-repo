package com.ssl.app.client.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.http.HttpHost;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.transport.http.HttpsUrlConnectionMessageSender;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;




@Service
public class SSLServiceImpl implements ISSLService 
{
	private static final String SERVICE_URI = "https://localhost:8089/get-data";
	private static final String PASSWORD = "client";
	private static final String CERT_PATH = "C:/RSA/client.jks";
	

	@Override
	public String consumeServerService()
	{
		//RestTemplate restTemplate = getRestTemplate();
		try 
		{
			RestTemplate restTemplate = getRest();
			RequestEntity<Object> requestEntity = null;
			ResponseEntity<String> response = restTemplate.exchange(SERVICE_URI, HttpMethod.GET, requestEntity, String.class);
			System.out.println(response.getStatusCode().toString()+"::::"+response.getBody().trim());
			return response.getStatusCode().toString()+"::::"+response.getBody().trim();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public RestTemplate getRest()
	{
		try
		{
			/*
			 * De está forma funciona, se debe agregar el metodo para validar el host
			 */
			File file = new File(CERT_PATH);
			
			SSLContext sslContext = SSLContextBuilder.create().loadKeyMaterial(file,PASSWORD.toCharArray(), PASSWORD.toCharArray()).loadTrustMaterial(file).build();
			
			HostnameVerifier hostnameVerifier = new HostnameVerifier() 
			{
				@Override
				public boolean verify(String hostname, SSLSession session) 
				{
					System.out.println("Hostname: " + hostname);
					// Validamos la url, acá se puede agregar una seríe de 
					if("localhost".equals(hostname))
						return true;
					else
						return false;
				}
			};
			
			//HttpClient client = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE).build();
			HttpClient client = HttpClients.custom().setSSLContext(sslContext).setSSLHostnameVerifier(hostnameVerifier).build();
			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
			requestFactory.setHttpClient(client);
			RestTemplate restTemplate = new RestTemplate(requestFactory);
			return restTemplate;
			
			
//			RestTemplate restTemplate = new RestTemplate();
//			File file = new File(CERT_PATH);
//			InputStream is = new FileInputStream(file);
//			KeyStore keystore = KeyStore.getInstance("jks");
//			keystore.load(is, PASSWORD.toCharArray());
//			
////			SSLContextBuilder contextBuilder = SSLContextBuilder.create().loadTrustMaterial(file, PASSWORD.toCharArray()).loadKeyMaterial(keystore, PASSWORD.toCharArray());
////			contextBuilder.build();
//			
//			SSLContextBuilder sslContextBuilder = new SSLContextBuilder();	    
//			sslContextBuilder.loadKeyMaterial(keystore, PASSWORD.toCharArray());
//			sslContextBuilder.loadTrustMaterial(new TrustSelfSignedStrategy());
//			
//			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContextBuilder.build());
//		    CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
//		    
//		    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(httpClient));
//		    return restTemplate;
			
			
			
//			RestTemplate restTemplate = new RestTemplate();
//			File file = new File(CERT_PATH);
//			
//			SSLContext sslContext = SSLContextBuilder
//			        .create()
//			        .loadTrustMaterial(file, PASSWORD.toCharArray())
//			        .build();
//			
//			CloseableHttpClient client = HttpClients.custom()
//			        .setSSLContext(sslContext)
//			        .build();
//			
//			HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
//			requestFactory.setHttpClient(client);
//			
//			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
//			return restTemplate;
			
			
//			RestTemplate restTemplate = new RestTemplate();
//			TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
//			KeyStore ks = KeyStore.getInstance("JKS");
//			FileInputStream fis = new FileInputStream(CERT_PATH);
//			ks.load(fis, PASSWORD.toCharArray());
//			// or ks.load(fis, "thepassword".toCharArray());
//			fis.close();
//
//			tmf.init(ks);
//
//			SSLContext sslContext = SSLContext.getInstance("TLS");
//			sslContext.init(null, tmf.getTrustManagers(), null);
//			CloseableHttpClient clietBuilder = HttpClientBuilder.create().setSSLContext(sslContext).build();
//			
//			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(clietBuilder));
//			return restTemplate;
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	public RestTemplate getRestTemplate()
	{
		try {
			RestTemplate restTemplate = new RestTemplate();
			File file = new File(CERT_PATH);
			InputStream is = new FileInputStream(file);
			KeyStore keystore = KeyStore.getInstance("jks");
			keystore.load(is, PASSWORD.toCharArray());
			
			SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(keystore).loadKeyMaterial(keystore, PASSWORD.toCharArray()).build();
			//SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(file).loadKeyMaterial(keystore, PASSWORD.toCharArray()).build();
			
//			SSLContextBuilder contextBuilder = SSLContextBuilder.create();
//			contextBuilder.loadTrustMaterial(file).loadKeyMaterial(keystore, PASSWORD.toCharArray());
//			SSLContext contextSsl = contextBuilder.build();
			
//			CloseableHttpClient clientBuilder = HttpClients.custom().setSSLContext(contextSsl).build();
			
			
			
			HttpClient client = HttpClients.custom().setHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).setSslcontext(sslContext).build();
			
			restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
			
			return restTemplate;
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		} 
		catch (CertificateException e) 
		{
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		catch (KeyStoreException e) 
		{
			e.printStackTrace();
		}
		catch (KeyManagementException e) 
		{
			e.printStackTrace();
		} 
		catch (UnrecoverableKeyException e) 
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	/*
	 * Este metodo funciona, pero la url debe estar dentro del jks,
	 * es decir, si es localhost, cuando se cree el certificado, esta debe estar adentro
	 */
	public RestTemplate executeCalled()
	{
		try 
		{
			RestTemplate builder = new RestTemplate();
			SSLContext sslContext = SSLContextBuilder
	                .create()
	                .loadKeyMaterial(ResourceUtils.getFile("classpath:localhost.jks"), PASSWORD.toCharArray(), PASSWORD.toCharArray())
	                .loadTrustMaterial(ResourceUtils.getFile("classpath:localhost.jks"), PASSWORD.toCharArray())
	                .build();

	        HttpClient client = HttpClients.custom()
	                .setSSLContext(sslContext)
	                .build();

	        builder.setRequestFactory(new HttpComponentsClientHttpRequestFactory(client));
	        return builder;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
}
