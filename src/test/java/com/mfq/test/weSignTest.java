package com.mfq.test;

import java.util.Map;

import com.google.common.collect.Maps;
import com.mfq.helper.SignHelper;

public class weSignTest {

	public static void main(String[] args) {
		Map<String, Object> map = Maps.newHashMap();
        map.put("appid", "wx1c8dcf726a7d540e");
        map.put("partnerid", "1268884501");
        map.put("prepay_id", "wx201509221656586f84f7eb7a0701053666");
        map.put("package", "Sign=WXPay");
        map.put("noncestr", "8AA8268D93E011F1D89FF51FE7D86178");
        map.put("timestamp", "1442912219");
        
    
		String sign = SignHelper.makeWxSign(map, "XU89Ab71M0pox18IozANB212gXMY6h31");
		System.out.println(sign);
	}

}
