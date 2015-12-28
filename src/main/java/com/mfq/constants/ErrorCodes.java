package com.mfq.constants;

/**
 * 错误信息码. 全站统一
 * @author xingyongshan
 *
 */
public class ErrorCodes {

    public static final String CODE_KEY = "code";
    public static final String MSG_KEY = "msg";
    public static final String DATA_KEY = "data";

    public static final int SUCCESS = 0;
    
    //保留: 1-999  可能冲突 *** 需要整理 ###
    public static final int FAIL = 101;
    public static final int SIGN_VALIDATE_ERROR = 102; // sign验证失败
    
    public static final int INNER_PAY_OK = 200; // 特殊位置的标记形式，独立个体使用！不与0冲突
    
    
    //用户相关
    public static final int USER_ERR_NICK = 1001; // 昵称格式错误
    public static final int USER_ERR_EMAIL = 1002; // 邮件格式错误
    public static final int USER_ERR_PASSWORD = 1003; // 密码格式错误
    public static final int USER_ERR_MOBILE = 1004; // 手机号格式错误

    public static final int USER_NICK_USED = 1101; // 昵称被占用
    public static final int USER_EMAIL_USED = 1102; // 邮件被占用
    public static final int USER_MOBILE_USED = 1104; // 手机号被占用
    public static final int USER_EMAIL_ACTIVED = 1105; // 邮箱已激活

    public static final int USER_MUST_EMAIL_OR_MOBILE = 1201; // 邮箱和手机号必填一项

    public static final int USER_VCODE_WRONG = 1210; // 验证码不匹配 如果验证的key不存在，则返回内容是“验证内容不存在”。
    public static final int USER_VCODE_TOOFAST = 1211; // 验证码请求太频繁
    public static final int USER_VCODE_TOOMUCH = 1212; // 验证码验证次数过多
    public static final int USER_VCODE_EXPIRED = 1213; // 验证码过期

    public static final int USER_NOT_FIND = 1221; // 用户不存在
    public static final int USER_WRONG_PASS = 1222; // 密码错误
    public static final int USER_NOT_ACTIVE = 1223; // 用户未激活
    
    public static final int USER_QUOTALEFT_TOO_LOW = 1301; // 用户可用额度太少（<=100）


    //地区相关
    public static final int LOCATION_NOT_FIND = 1301; //地区不存在

    //全局部分: 统一版  9900 -> 9999
    public static final int CORE_NEED_LOGIN = 9900; // 需要登录
    public static final int CORE_UNLAWFUL_CONTENT = 9901; // 包含非法内容
    public static final int CORE_PARAM_UNLAWFUL = 9902; // 参数错误
    public static final int CORE_UNLAWFULL_OPERATE = 9903; // 访问受限（权限不够)
    public static final int CORE_USER_NOLOGIN = 9904; // 用户未登录
    public static final int CORE_TOKEN_FAIL = 9905; // 用户票据无效
    public static final int CORE_PARAM_NULL = 9906; // 参数为空 由于缺少必须的参数导致错误
    public static final int CORE_MISSING = 9907;    // 资源不存在   请求的资源不存在
    public static final int CORE_DATEFORMAT = 9908; // 日期格式错误或者日期已经过期
    public static final int CORE_JSON_RENDER_ERR = 9909; // JSON数据渲染错误
    public static final int CORE_PIC_ERROR = 9910; // 图片处理错误
    public static final int CORE_ERROR = 9999; //系统错误(未知异常）
    
    
    //充值部分
    public static final int CHARGE_DEPOSIT_SAVE2DB = 2001; // 充值失败
    
    //订单支付环节
    public static final int ORDER_CANT_GO_PAY = 3001; // gopay失败
    public static final int ORDER_MONEY_TOOLOW = 3002; // 订单金额太少（<=100）
    
    //=======================================================================
    //短信系统
    public static final int SMS_DUPLICATE = 10101; //短信内容重复
    public static final int SMS_OVERFLOW = 10102; //短信内容长度超出限制

    //用户收藏
    public static final int FAVORITE_EXISTS = 10201; //已经收藏
    public static final int FAVORITE_TOOMUCH = 10202; //收藏数量太多

    //邮件相关
    public static final int MAIL_EMPTY_TITLE = 11001; //邮件标题不能为空
    public static final int MAIL_EMPTY_CONTENT = 11002; //邮件内容不能为空
    public static final int MAIL_EMPTY_RECEIVER = 11003; //收件人不能为空

    //=======================================================================
    
    //秒杀
    public static final int SECKILL_ACTIVITY_END = 13001; //活动结束
    public static final int SECKILL_NOT_PRODUCT = 13002; //产品抢完了
    public static final int SECKILL_ERROR_PRODUCT = 13003; //产品抢完了
    
    //=======================================================================
    public static final int  FRAUD_ERROR= 8894; //同盾验证失败
}
