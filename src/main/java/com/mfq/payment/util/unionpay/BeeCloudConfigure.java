package com.mfq.payment.util.unionpay;

import com.mfq.utils.Config;

import java.util.ResourceBundle;

/**
 * 这里放置各种配置数据
 */
public class BeeCloudConfigure {

//    private static ResourceBundle unionpayResb = ResourceBundle
//            .getBundle("payment.beecloud.payapi");
    
    //这个就是自己要保管好的私有Key了（切记只能放在自己的后台代码里，不能放在任何可能被看到源代码的客户端程序中）
    // 每次自己Post数据给API的时候都要用这个key来对所有字段进行签名，生成的签名会放在Sign这个字段，API收到Post数据的时候也会用同样的签名算法对Post过来的数据进行签名和验证
    // 收到API的返回的时候也要用这个key来对返回的数据算下签名，跟API的Sign数据进行比较，如果值不一致，有可能数据被第三方给篡改
    public static String APPSECRET  = Config.getItem("app_secret");

    //BeeCloud 分配的APPID
    public static String APPID = Config.getItem("app_id");

    //请求服务器地址
    public static final String Mode = "HTTPS://apibj.beecloud.cn";

    //支付请求API地址
    public static final String URL = "/2/rest/bill";

    //支付方式
    public static final String CHANNEL = "UN_APP";




}
