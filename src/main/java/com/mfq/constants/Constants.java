package com.mfq.constants;

import java.util.Set;

import com.google.common.collect.Sets;
import com.mfq.utils.Config;

/**
 * Created by xingyongshan on 15/8/4.
 */
public class Constants {

    // 数据库默认master连接池名字
    public static String DEFAULT_POOL = "default";
    public static String DEFAULT_DB = "meifenqi";
    
    public static Set<String> APP_TYPE = Sets.newHashSet("Android", "iOS");
    
    // 在线生单－购物部分订单号前缀
    public static String ONLINE_ORDER_PREFIX = "mn";
    // 在线充值－仅充值订单前缀
    public static String RECHARGE_ORDER_PREFIX = "cz";
    // 在线还款－仅还款订单前缀
    public static String REFUND_ORDER_PREFIX = "bl";
    // 随意单前缀
    public static String FREEDOM_ORDER_PREFIX = "fk";
    
    public static String SEC_KEY = "Xo+81y.0AA61j89],f|yu6";
    
    // 网站相关，通用－发短信／邮件等
    public static String SITE_NAME = "美分期";
    public static String SITE_DOMAIN = Config.getItem("site_domain");
    public static String COOKIE_DOMAIN = ".5imfq.com";
    
    //当前登录用户在attribute中的属性名称
    public static String CURRENT_USER_ATTRIBUTE = "user";
    //当前登录用户uid在attribute中的属性名称
    public static String CURRENT_XID_ATTRIBUTE = "xid";

    public static String NXID_ATTRIBUTE = "_nxid";

    /**
     * 忽略权限检测的资源，比如静态资源等，无需用户、资源等上下文
     */
    public static String IS_STATIC_RESOURCE = "_isstaticresource";
    
    public static String DEFAULT_ICON = "avatar/a/default/icon.png";
    
    public static String DEFAULT_IMG = "avatar/a/default/img.png";
    
    public static String DEFAULT_PIC = "avatar/a/default/pic.png";
    
    //七牛回调－app
    public static String QINIU_CALLBACK_FORAPP = "/picture/callback/";
    public static String QINIU_CALLBACK_SECKEY = "qnGjs9x,kDflj+13Us-dKdOk";
    
    //微信短信
    public static String WECHAT_TOKEN = "meifenqi12345";
    public static final long MOBILE_EXPIRE_DURATION = 1000 * 60 * 30; // 30分钟有效
    public static final long Msm_SEND_DURATION = 1000 * 60 * 2; // 重发时间2分钟
    
    public static final String APPID = "wx9df416d077b29b73";
	public static final String APPSECRET = "54a8aa54296e229879761249720b1db7";
	
	public static final String WACHAT_TOKEN_KEY = "WECHAT_TOKEN";
	
	public static final int ACCESS_TOKEN_IN = 7200; //微信access token维持时间
	
	public static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    
	public static final String ACCESS_JSTOKEN_URL="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	
	public static final String WECHAT_KF_URL = "https://api.weixin.qq.com/cgi-bin/customservice/getkflist?access_token=ACCESS_TOKEN";
	
	public static final String WECHAT_SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
	
	public static final String WECHAT_QUERY_USER = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

	public static final String WECHAT_JS_TICKET = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=wx_card";
	
	public static final String WECHAT_FOREVER_MEDIA = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=ACCESS_TOKEN";
	
	public static final String WECHAT_TEMP_MEDIA = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
	
	//身份证认证
	public static final String IDCARD_CHECK = "http://api.id98.cn/api/idcard?appkey=APPKEY&name=NAME&cardno=IDCARD";
	
	public static final String IDCARD_APPKEY = "540ff370d577d8e88e4ae427a887ab77";
}
