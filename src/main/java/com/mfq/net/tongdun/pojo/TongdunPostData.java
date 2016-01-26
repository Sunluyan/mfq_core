package com.mfq.net.tongdun.pojo;

import java.util.Date;

/**
 * 
partner_code string 32 必填 同盾分配的合作⽅标⽰
secret_key string 32 必填 同盾分配的API密钥
event_id string 64 必填 事件ID
token_id string 64 可选 获取设备信息的会话标识
ip_address string 15 必填 IP地址
id_number string 18 可选 ⾝份证
account_login string 32 可选 登录账户名
account_name string 32 可选 ⽤户姓名
account_email string 32 可选 邮箱
account_phone string 15 可选 电话号码
account_mobile string 15 可选 ⼿机号
account_password string 32 可选 密码摘要
transaction_id string 64 可选 交易流⽔
organization string 200 可选 ⼯作单位
biz_license string 15 可选 ⼯商注册号
org_code string 9 可选 组织机构代码
accountaddressstreet string 256 可选 买家的街道地址信息
accountaddresscounty string 20 可选 买家的县或镇地址信息
accountaddresscity string 20 可选 买家的城市地址信息
accountaddressprovince string 15 可选 买家的省份地址信息
accountaddresscountry string 15 可选 买家的国家地址信息
accountzipcode string 10 可选 买家的邮编信息
payee_userid string 10 可选 卖家或收款⼈ID
payee_name string 10 可选 卖家或收款⼈姓名
payee_email string 10 可选 卖家或收款⼈邮箱
payee_mobile string 10 可选 卖家或收款⼈⼿机
payee_phone string 10 可选 卖家或收款⼈座机
payeeidnumber string 10 可选 卖家或收款⼈⾝份证
payeecardnumber string 10 可选 卖家或收款⼈银⾏卡号
deliver_name string 32 可选 收货⼈姓名
deliver_mobile string 15 可选 收货⼈⼿机号
deliver_phone string 15 可选 收货⼈座机号
deliveraddressstreet string 256 可选 收货⼈街道地址信息
deliveraddresscounty string 20 可选 收货⼈县或镇地址信息
deliveraddresscity string 20 可选 收货⼈城市地址信息
deliveraddressprovince string 15 可选 收货⼈省份地址信息
deliveraddresscountry string 15 可选 收货⼈国家地址信息
deliverzipcode string 10 可选 收货⼈邮编信息
pay_id string 64 可选 内部⽀付流⽔号
pay_method string 32 可选 ⽀付⽅式
pay_amount double 32 可选 ⽀付⾦额
pay_currency string 32 可选 ⽀付货币
card_number string 32 可选 银⾏卡号
cc_bin string 6 可选 BIN卡号
eventoccurtime date 20 可选 事件发⽣时间
useragentcust string 200 可选 User-Agent
refer_cust string 200 可选 Referer


 * @author liuzhiguo1
 *
 */
public class TongdunPostData {
	public static final String partner_code = "meifenqi";
	public static final String secret_key_android = "c38cc2d9fdd745f1b3bb2cca3caffb18";
	public static final String secret_key_ios = "5b15208213984b7e8b794fdc93f22af3";
	public static final String secret_key_web = "6c955c79ce044546890b2b7f3e44dc82";
	public static final String event_id_android_login = "login_professional_android";
	public static final String event_id_android_register = "register_professional_android";
	public static final String event_id_android_loan = "loan_and";
	public static final String event_id_ios_login = "login_professional_ios";
	public static final String event_id_ios_register = "register_professional_ios";
	public static final String event_id_ios_loan = "loan_ios";
	public static final String event_id_web_register = "register_professional_web";
	public static final String event_id_web_login = "login_professional_web";

	
	
	private String event_id  ;//64 必填 事件ID
	private String token_id  ;//64 可选 获取设备信息的会话标识
	private String ip_address ;// 15 必填 IP地址
	private String id_number ;// 18 可选 ⾝份证
	private String account_login ;// 32 可选 登录账户名
	private String account_name ;// 32 可选 ⽤户姓名
	private String account_email ;// 32 可选 邮箱
	private String account_phone  ;//15 可选 电话号码
	private String account_mobile ;// 15 可选 ⼿机号
	private String account_password ;// 32 可选 密码摘要
	private String transaction_id;// ;// 64 可选 交易流⽔
	private String organization ;// 200 可选 ⼯作单位
	private String biz_license ;// 15 可选 ⼯商注册号
	private String org_code ;// 9 可选 组织机构代码
	private String accountaddressstreet ;// 256 可选 买家的街道地址信息
	private String accountaddresscounty ;// 20 可选 买家的县或镇地址信息
	private String accountaddresscity ;// 20 可选 买家的城市地址信息
	private String accountaddressprovince ;// 15 可选 买家的省份地址信息
	private String accountaddresscountry ;// 15 可选 买家的国家地址信息
	private String accountzipcode  ;//10 可选 买家的邮编信息
	private String payee_userid  ;//10 可选 卖家或收款⼈ID
	private String payee_name ;// 10 可选 卖家或收款⼈姓名
	private String payee_email ;// 10 可选 卖家或收款⼈邮箱
	private String payee_mobile ;// 10 可选 卖家或收款⼈⼿机
	private String payee_phone ;// 10 可选 卖家或收款⼈座机
	private String payeeidnumber ;// 10 可选 卖家或收款⼈⾝份证
	private String payeecardnumber ;// 10 可选 卖家或收款⼈银⾏卡号
	private String deliver_name ;// 32 可选 收货⼈姓名
	private String deliver_mobile ;// 15 可选 收货⼈⼿机号
	private String deliver_phone ;// 15 可选 收货⼈座机号
	private String deliveraddressstreet ;// 256 可选 收货⼈街道地址信息
	private String deliveraddresscounty ;// 20 可选 收货⼈县或镇地址信息
	private String deliveraddresscity ;// 20 可选 收货⼈城市地址信息
	private String deliveraddressprovince ;// 15 可选 收货⼈省份地址信息
	private String deliveraddresscountry ;// 15 可选 收货⼈国家地址信息
	private String deliverzipcode ;// 10 可选 收货⼈邮编信息
	private String pay_id ;// 64 可选 内部⽀付流⽔号
	private String pay_method ;// 32 可选 ⽀付⽅式
	private double pay_amount  ;//32 可选 ⽀付⾦额
	private String pay_currency ;// 32 可选 ⽀付货币
	private String card_number ;// 32 可选 银⾏卡号
	private String cc_bin;//  6 可选 BIN卡号
	private Date eventoccurtime ;// 20 可选 事件发⽣时间
	private String useragentcust ;// 200 可选 User-Agent
	private String refer_cust ;// 200 可选 Referer
	
	
	public String getEvent_id() {
		return event_id;
	}
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	public String getToken_id() {
		return token_id;
	}
	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getId_number() {
		return id_number;
	}
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
	public String getAccount_login() {
		return account_login;
	}
	public void setAccount_login(String account_login) {
		this.account_login = account_login;
	}
	public String getAccount_name() {
		return account_name;
	}
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	public String getAccount_email() {
		return account_email;
	}
	public void setAccount_email(String account_email) {
		this.account_email = account_email;
	}
	public String getAccount_phone() {
		return account_phone;
	}
	public void setAccount_phone(String account_phone) {
		this.account_phone = account_phone;
	}
	public String getAccount_mobile() {
		return account_mobile;
	}
	public void setAccount_mobile(String account_mobile) {
		this.account_mobile = account_mobile;
	}
	public String getAccount_password() {
		return account_password;
	}
	public void setAccount_password(String account_password) {
		this.account_password = account_password;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getBiz_license() {
		return biz_license;
	}
	public void setBiz_license(String biz_license) {
		this.biz_license = biz_license;
	}
	public String getOrg_code() {
		return org_code;
	}
	public void setOrg_code(String org_code) {
		this.org_code = org_code;
	}
	public String getAccountaddressstreet() {
		return accountaddressstreet;
	}
	public void setAccountaddressstreet(String accountaddressstreet) {
		this.accountaddressstreet = accountaddressstreet;
	}
	public String getAccountaddresscounty() {
		return accountaddresscounty;
	}
	public void setAccountaddresscounty(String accountaddresscounty) {
		this.accountaddresscounty = accountaddresscounty;
	}
	public String getAccountaddresscity() {
		return accountaddresscity;
	}
	public void setAccountaddresscity(String accountaddresscity) {
		this.accountaddresscity = accountaddresscity;
	}
	public String getAccountaddressprovince() {
		return accountaddressprovince;
	}
	public void setAccountaddressprovince(String accountaddressprovince) {
		this.accountaddressprovince = accountaddressprovince;
	}
	public String getAccountaddresscountry() {
		return accountaddresscountry;
	}
	public void setAccountaddresscountry(String accountaddresscountry) {
		this.accountaddresscountry = accountaddresscountry;
	}
	public String getAccountzipcode() {
		return accountzipcode;
	}
	public void setAccountzipcode(String accountzipcode) {
		this.accountzipcode = accountzipcode;
	}
	public String getPayee_userid() {
		return payee_userid;
	}
	public void setPayee_userid(String payee_userid) {
		this.payee_userid = payee_userid;
	}
	public String getPayee_name() {
		return payee_name;
	}
	public void setPayee_name(String payee_name) {
		this.payee_name = payee_name;
	}
	public String getPayee_email() {
		return payee_email;
	}
	public void setPayee_email(String payee_email) {
		this.payee_email = payee_email;
	}
	public String getPayee_mobile() {
		return payee_mobile;
	}
	public void setPayee_mobile(String payee_mobile) {
		this.payee_mobile = payee_mobile;
	}
	public String getPayee_phone() {
		return payee_phone;
	}
	public void setPayee_phone(String payee_phone) {
		this.payee_phone = payee_phone;
	}
	public String getPayeeidnumber() {
		return payeeidnumber;
	}
	public void setPayeeidnumber(String payeeidnumber) {
		this.payeeidnumber = payeeidnumber;
	}
	public String getPayeecardnumber() {
		return payeecardnumber;
	}
	public void setPayeecardnumber(String payeecardnumber) {
		this.payeecardnumber = payeecardnumber;
	}
	public String getDeliver_name() {
		return deliver_name;
	}
	public void setDeliver_name(String deliver_name) {
		this.deliver_name = deliver_name;
	}
	public String getDeliver_mobile() {
		return deliver_mobile;
	}
	public void setDeliver_mobile(String deliver_mobile) {
		this.deliver_mobile = deliver_mobile;
	}
	public String getDeliver_phone() {
		return deliver_phone;
	}
	public void setDeliver_phone(String deliver_phone) {
		this.deliver_phone = deliver_phone;
	}
	public String getDeliveraddressstreet() {
		return deliveraddressstreet;
	}
	public void setDeliveraddressstreet(String deliveraddressstreet) {
		this.deliveraddressstreet = deliveraddressstreet;
	}
	public String getDeliveraddresscounty() {
		return deliveraddresscounty;
	}
	public void setDeliveraddresscounty(String deliveraddresscounty) {
		this.deliveraddresscounty = deliveraddresscounty;
	}
	public String getDeliveraddresscity() {
		return deliveraddresscity;
	}
	public void setDeliveraddresscity(String deliveraddresscity) {
		this.deliveraddresscity = deliveraddresscity;
	}
	public String getDeliveraddressprovince() {
		return deliveraddressprovince;
	}
	public void setDeliveraddressprovince(String deliveraddressprovince) {
		this.deliveraddressprovince = deliveraddressprovince;
	}
	public String getDeliveraddresscountry() {
		return deliveraddresscountry;
	}
	public void setDeliveraddresscountry(String deliveraddresscountry) {
		this.deliveraddresscountry = deliveraddresscountry;
	}
	public String getDeliverzipcode() {
		return deliverzipcode;
	}
	public void setDeliverzipcode(String deliverzipcode) {
		this.deliverzipcode = deliverzipcode;
	}
	public String getPay_id() {
		return pay_id;
	}
	public void setPay_id(String pay_id) {
		this.pay_id = pay_id;
	}
	public String getPay_method() {
		return pay_method;
	}
	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}
	public double getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(double pay_amount) {
		this.pay_amount = pay_amount;
	}
	public String getPay_currency() {
		return pay_currency;
	}
	public void setPay_currency(String pay_currency) {
		this.pay_currency = pay_currency;
	}
	public String getCard_number() {
		return card_number;
	}
	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}
	public String getCc_bin() {
		return cc_bin;
	}
	public void setCc_bin(String cc_bin) {
		this.cc_bin = cc_bin;
	}
	public Date getEventoccurtime() {
		return eventoccurtime;
	}
	public void setEventoccurtime(Date eventoccurtime) {
		this.eventoccurtime = eventoccurtime;
	}
	public String getUseragentcust() {
		return useragentcust;
	}
	public void setUseragentcust(String useragentcust) {
		this.useragentcust = useragentcust;
	}
	public String getRefer_cust() {
		return refer_cust;
	}
	public void setRefer_cust(String refer_cust) {
		this.refer_cust = refer_cust;
	}
	public TongdunPostData() {
		super();
	}
	public TongdunPostData(
			String event_id, String ip_address) {
		super();
		this.event_id = event_id;
		this.ip_address = ip_address;
	}
	
	
	

	
	
	
}
