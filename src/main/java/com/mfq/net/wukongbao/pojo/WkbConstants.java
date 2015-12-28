package com.mfq.net.wukongbao.pojo;

import java.math.BigDecimal;

import com.mfq.utils.Config;

public class WkbConstants {
	
	
	
	//悟空保参数
	public static String INSURE_URL =Config.getItem("insure_url");  
	
	public static String QUERY_POLICY = "http://open.wkbins.com/openapiMFQ/policyQuery";  //查询保单
	
	public static String SETTLECLAIM = "http://open.wkbins.com/openapiMFQ/settleClaimQuery";  //理赔查询
	
	public static String APP_KEY = Config.getItem("policy_key");
	
	public static String INSURE_PRODUCT =  Config.getItem("policy_product"); 
	
	public static String SERVICE_NAME = "meifenqi";
	
	public static String CHARSET = "UTF-8";
	
	public static String SIGN_TYPE = "RSA";
	
	public static String NOTIFY_URL = com.mfq.constants.Constants.SITE_DOMAIN+"/policy/callback";
	
	public static String VERSION = "1.0.0";
	
	public static String FORMAT = "JSON";
	
	public static BigDecimal PREMIUM = BigDecimal.valueOf(19.00);  //分
	
	public static String HOSPITAL_LEVEL = "医院";
	
	public static String WKB_PUK_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCj0SfLvqKDEYzLk+cN7K+8aVfH4dZ8RfQLgUhisuo5lsFDuOBhDNELKcmxMO5gDYd6Np6yZ91eww1V8zmYTkRqGpsCvj9L1vHqkYtKy6q9g7nlQpvl+c543ZFvp6a8XilR0O0xBDDQWhSJ8JCZpMXYuAgt/UGxAYTV/ncGS4VBtQIDAQAB";
	
	public static String DEV_PRI_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMvM1+ffxP4wcVIHyUSnrTFZNd7S"
	+"/jzV8yHnt6v1t/Lg5cHh3BsoDv4eiACxW7MGB1h5L8X6me7eB/LV2Oheo6Nd6aREvzN8DeQ6Qhy5"
	+"jKlwd6QeFerc0mNzkk1oslcnsTyVPTUeOiTVJUNCtL3KXNS4Osg+jP7on46PcKx3moSPAgMBAAEC"
	+"gYB6GvdZRCchLSEYRvX+HoCUSqEOKliNVprfu5e+ffCCouDF828PDkc9VkmiMKGCd6A7EBDhi65j"
	+"AcC9bAcT4nAjxbv+XjrflJ2N+XbNQmRp9aKRV2nzN28Gd2H/HS7HzbN7vq4ADZ7ERI///DlWPn5y"
	+"5UAHZvet3jfb4maW+AzhgQJBAOoex3W9fDCRKyEJ9xaB6AsY/n6DMfmo08w0u2zH5ThrXTTW8i3y"
	+"R+Tql1LZv9S4SdVvsRmGfteNz+yX3N/86KECQQDe2Kui1ahUQVaeinI4xiqmDdlqWfigmGVGAuRV"
	+"F5FKfL7UWKggNS2x6asJJ/JeZU5Ytua/2L1xdRcXxFfmjm8vAkEAxjJiVxmfnhn3MhQWmnZFM8fo"
	+"AsAcOaekDbcCiDe9pIK3uDjTcvQtyuySSLBQhsuwrcDNY7eJf//s6vUxJmoboQJAfl9m7f+15pnq"
	+"8htW7OVMGEDwU4RUH1Zs0AghzjOAPDqDyU3BggpGMIdva9DQfEF87YhDUPYrcGTTRLIemuuZFwJB"
	+"AJ4+CQJtLUIbGqjPRnk2AXlAzI1LMQPcUQbmWb4lT2c50ssF/twCinwA2gQzRTWIJo9/vxA4vAEc"
	+"KsW5HE9brQw=";
	
	public static String DEV_PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLzNfn38T+MHFSB8lEp60xWTXe0v481fMh57er"
			+"9bfy4OXB4dwbKA7+HogAsVuzBgdYeS/F+pnu3gfy1djoXqOjXemkRL8zfA3kOkIcuYypcHekHhXq"
			+"3NJjc5JNaLJXJ7E8lT01Hjok1SVDQrS9ylzUuDrIPoz+6J+Oj3Csd5qEjwIDAQAB";
	
//	公钥
//	MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDLzNfn38T+MHFSB8lEp60xWTXe0v481fMh57er
//	9bfy4OXB4dwbKA7+HogAsVuzBgdYeS/F+pnu3gfy1djoXqOjXemkRL8zfA3kOkIcuYypcHekHhXq
//	3NJjc5JNaLJXJ7E8lT01Hjok1SVDQrS9ylzUuDrIPoz+6J+Oj3Csd5qEjwIDAQAB
//	
//	私钥
//	MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMvM1+ffxP4wcVIHyUSnrTFZNd7S
//	/jzV8yHnt6v1t/Lg5cHh3BsoDv4eiACxW7MGB1h5L8X6me7eB/LV2Oheo6Nd6aREvzN8DeQ6Qhy5
//	jKlwd6QeFerc0mNzkk1oslcnsTyVPTUeOiTVJUNCtL3KXNS4Osg+jP7on46PcKx3moSPAgMBAAEC
//	gYB6GvdZRCchLSEYRvX+HoCUSqEOKliNVprfu5e+ffCCouDF828PDkc9VkmiMKGCd6A7EBDhi65j
//	AcC9bAcT4nAjxbv+XjrflJ2N+XbNQmRp9aKRV2nzN28Gd2H/HS7HzbN7vq4ADZ7ERI///DlWPn5y
//	5UAHZvet3jfb4maW+AzhgQJBAOoex3W9fDCRKyEJ9xaB6AsY/n6DMfmo08w0u2zH5ThrXTTW8i3y
//	R+Tql1LZv9S4SdVvsRmGfteNz+yX3N/86KECQQDe2Kui1ahUQVaeinI4xiqmDdlqWfigmGVGAuRV
//	F5FKfL7UWKggNS2x6asJJ/JeZU5Ytua/2L1xdRcXxFfmjm8vAkEAxjJiVxmfnhn3MhQWmnZFM8fo
//	AsAcOaekDbcCiDe9pIK3uDjTcvQtyuySSLBQhsuwrcDNY7eJf//s6vUxJmoboQJAfl9m7f+15pnq
//	8htW7OVMGEDwU4RUH1Zs0AghzjOAPDqDyU3BggpGMIdva9DQfEF87YhDUPYrcGTTRLIemuuZFwJB
//	AJ4+CQJtLUIbGqjPRnk2AXlAzI1LMQPcUQbmWb4lT2c50ssF/twCinwA2gQzRTWIJo9/vxA4vAEc
//	KsW5HE9brQw=
}
