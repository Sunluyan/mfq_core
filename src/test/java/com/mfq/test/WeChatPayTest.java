package com.mfq.test;

import java.net.URLEncoder;
import java.security.KeyStore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.alibaba.fastjson.JSON;
import com.mfq.payment.util.yeepay.common.RandomUtil;
import com.mfq.payment.util.yeepay.encrypt.AES;
import com.mfq.payment.util.yeepay.encrypt.EncryUtil;
import com.mfq.payment.util.yeepay.encrypt.RSA;

public class WeChatPayTest {

	public static void main(String[] args) {/*
		Keystore keyStore = KeyStore.getInstance("PKCS12");
		
		//指定读取证书格式为PKCS12 
		KeyStore keyStore = KeyStore.getInstance("PKCS12");//读取本机存放的PKCS12证书文件
		 
		FileInputStream instream = new FileInputStream(new File("D:/apiclient_cert.p12")); try { 
		//指定PKCS12的密码(商户ID) 
		keyStore.load(instream, "10010000".toCharArray()); } finally { 
		instream.close(); } 
		SSLContext sslcontext = SSLContexts.custom() 
		.loadKeyMaterial(keyStore, "10010000".toCharArray()).build(); //指定TLS版本 
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory( sslcontext,new String[] { "TLSv1" },null, 
		SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER); //设置httpclient的SSLSocketFactory 
		CloseableHttpClient httpclient = HttpClients.custom() .setSSLSocketFactory(sslsf) .build();
		
		*/
	}

}
