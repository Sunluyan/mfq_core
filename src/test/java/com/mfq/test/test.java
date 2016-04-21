package com.mfq.test;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mfq.bean.PolicyInfo;
import com.mfq.dao.PolicyInfoMapper;

public class test {

	public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {

		ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
		PolicyInfo p = new PolicyInfo();
	}

}
