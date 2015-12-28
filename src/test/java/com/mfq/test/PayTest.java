package com.mfq.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PayTest {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
		 File file=new File("D:/apiclient_cert.p12");
         if(!file.exists()||file.isDirectory())
             throw new FileNotFoundException();
         FileInputStream fis=new FileInputStream(file);
         byte[] buf = new byte[1024];
         StringBuffer sb=new StringBuffer();
         while((fis.read(buf))!=-1){
             sb.append(new String(buf));    
             buf=new byte[1024];//重新生成，避免和上次读取的数据重复
         }
         System.out.println(sb.toString());
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

}
