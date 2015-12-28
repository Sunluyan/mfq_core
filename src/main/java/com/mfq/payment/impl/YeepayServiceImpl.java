package com.mfq.payment.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.mfq.annotation.PayAPIImpl;
import com.mfq.bean.OrderInfo;
import com.mfq.bean.PayCallbackResult;
import com.mfq.bean.Product;
import com.mfq.constants.CardType;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.OrderType;
import com.mfq.dataservice.context.AppContext;
import com.mfq.payment.BasePaymentService;
import com.mfq.payment.PayAPIType;
import com.mfq.payment.util.yeepay.PayAPIURIList;
import com.mfq.payment.util.yeepay.RespondJson;
import com.mfq.payment.util.yeepay.common.RandomUtil;
import com.mfq.payment.util.yeepay.encrypt.AES;
import com.mfq.payment.util.yeepay.encrypt.EncryUtil;
import com.mfq.payment.util.yeepay.encrypt.RSA;
import com.mfq.service.OrderService;
import com.mfq.service.PayRecordService;
import com.mfq.service.ProductService;
import com.mfq.utils.HttpUtil;
import com.mfq.utils.JsonUtil;
import com.mfq.utils.RequestUtils;

@PayAPIImpl(payAPIType = PayAPIType.YEEPAY)
@Service
public class YeepayServiceImpl extends BasePaymentService {

    private static final Logger logger = LoggerFactory
            .getLogger(YeepayServiceImpl.class);

    private static ResourceBundle yeepayResb = ResourceBundle
            .getBundle("payment.yeepay.payapi");

    // 从配置文件读取易宝分配的公钥
    private static String yibaoPublicKey = yeepayResb
            .getString("payapi.pay_yibao_publickey");

    // 从配置文件读取商户自己的私钥
    private static String merchantPrivateKey = yeepayResb
            .getString("payapi.pay_merchant_privatekey");

    // 商户账户编号
    private static String merchantaccount = yeepayResb
            .getString("payapi.pay_merchantaccount");

    // 从配置文件读取移动终端wap接口URL前缀
    private String urlPrefix = yeepayResb
            .getString("payapi.paymobile_urlprefix");

    // 商户自己随机生成的AESkey
    private String merchantAesKey = RandomUtil.getRandom(16);

    @Resource
    OrderService orderService;
    @Resource
    ProductService productService;
    @Resource
    PayRecordService payRecordService;

    /**
     * 必须的参数：orderNo, amount, quota, sign -- json格式
     */
    @Override
    public String goPay(HttpServletRequest request,
            HttpServletResponse response, Map<String, Object> params,
            OrderType orderType) {
        String ret = "";
        try {

            TreeMap<String, Object> map = new TreeMap<String, Object>();
            String orderNo = (String) params.get("order_no");
            String pname = "";
            if (orderType == OrderType.RECHARGE) {
                pname = "美分期个人余额充值－" + String.valueOf(params.get("amount"));
            }else if(orderType == OrderType.REFUND){
            	pname = "美分期个人账单还款－" + String.valueOf(params.get("amount"));
            }else {
                OrderInfo order = orderService.findByOrderNo(orderNo);
                Product product = productService.findById(order.getPid());
                pname = product.getName();
            }

            map.put("merchantaccount", merchantaccount);
            map.put("orderid", orderNo);
            map.put("productcatalog", "1");
            map.put("productname", pname);
            map.put("identityid", AppContext.getUuid()); // params.get("identityid")
            map.put("userip", AppContext.getIp());
            map.put("terminalid", AppContext.getUuid()); // params.get("terminalid")
            map.put("transtime", (int) (System.currentTimeMillis() / 1000));
            int amount = (int)(Float.parseFloat(params.get("amount").toString())*100);
            map.put("amount", amount);
            map.put("identitytype", 2);
            map.put("terminaltype", 1); // 0、IMEI；1、MAC；2、UUID；3、OTHER,
                                        // (Integer) params.get("terminaltype")
            map.put("productdesc", "");
            map.put("fcallbackurl", "");
            map.put("callbackurl", buildPayCallbackURL());
            map.put("paytypes", "");
            map.put("currency", 156); // 币种，默认156，人民币
            map.put("orderexpdate", 60); // 订单过期时间，如60分钟
            map.put("cardno", "");
            map.put("idcardtype", "");
            map.put("idcard", "");
            map.put("owner", "");

            // 生成RSA签名

            String sign = EncryUtil.handleRSA(map, merchantPrivateKey);
            map.put("sign", sign);

            // 生成data
            String info = JSON.toJSONString(map);
            logger.info("order:{},业务数据明文：{}", orderNo, info);

            String data = AES.encryptToBase64(info, merchantAesKey);

            logger.info("含有签名的业务数据密文data:" + data);

            // 使用RSA算法将商户自己随机生成的AESkey加密
            String encryptkey = RSA.encrypt(merchantAesKey, yibaoPublicKey);
            logger.info("YEEPAY generate encryptkey:{}", encryptkey);

            String mobilePayUrl = urlPrefix
                    + PayAPIURIList.PAYWEB_PAY.getValue();

            // 浏览器重定向,发送get方式请求，访问一键支付网页支付地址
            String redirectUrl = mobilePayUrl + "?" + "merchantaccount="
                    + URLEncoder.encode(merchantaccount, "UTF-8") + "&data="
                    + URLEncoder.encode(data, "UTF-8") + "&encryptkey="
                    + URLEncoder.encode(encryptkey, "UTF-8");

            logger.info("redirect:{}", redirectUrl);

            // 通过页面发送post请求，访问一键支付网页支付地址
            // 使用post方式访问一键支付网页支付地址，可以防止被钓鱼，所以请尽量使用post方式
            Map<String, String> model = Maps.newHashMap();
            model.put("mobilePayUrl", mobilePayUrl);
            model.put("merchantaccount", merchantaccount);
            model.put("data", data);
            model.put("encryptkey", encryptkey);
            logger.info("YEEPAY goPay param info:{}", model);
            ret = JsonUtil.successResultJson(model);
        } catch (Exception e) {
            logger.error("YEEPAY_GOPAY_ERROR", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "YEEPAY_GOPAY_ERROR", null);
        }
        return ret;
    }

    @Override
    public PayCallbackResult payCallback(HttpServletRequest request,
            HttpServletResponse response, String ret) {
        PayCallbackResult result = new PayCallbackResult();
        result.setApiType(PayAPIType.YEEPAY);
        String resp = "failed";
        try {
            String data = RequestUtils.getString(request, "data", null);
            String encryptkey = RequestUtils.getString(request, "encryptkey",
                    null);
            String body = checkYbResult(data, encryptkey);
            if (StringUtils.isNotBlank(body)) {
                JSONObject json = JSONObject.parseObject(body);
                if (json == null || !StringUtils.equalsIgnoreCase(
                        json.getString("merchantaccount"), merchantaccount)) {
                    logger.error("YEEPAY支付给商户结果非法，json={}", json);
                    return result;
                }
                if (json.getInteger("status") != 1) {
                    logger.error("YEEPAY支付给商户结果为支付失败！非正常交易逻辑，请关注！");
                    return result;
                }
                result.setStatus(json.getInteger("status"));
                result.setMerchantaccount(json.getString("merchantaccount"));
                result.setOrderNo(json.getString("orderid"));
                result.setTradeNo(json.getString("yborderid"));
                result.setAmount(new BigDecimal(json.getInteger("amount"))
                        .divide(new BigDecimal(100)));
                result.setBankCode(json.getString("bankcode"));
                result.setBank(json.getString("bank"));
                result.setCardType(
                        CardType.fromId(json.getInteger("cardtype")));
                result.setLastno(json.getString("lastno"));
                // MD居然内部还有一个sign，也要进行校验！
                result.setSign(json.getString("sign"));
                resp = "success";
            } else {
                logger.warn("YEEPAY支付回调验签未通过或解析失败");
            }
        } catch (Exception e) {
            logger.error("YEEPAY callback process error", e);
        }
        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/plain");
            response.getWriter().write(resp);
        } catch (IOException ex) {
            logger.error("YEEPAY response error", ex);
        }
        logger.info("YEEPAY callback response:{}", resp);
        return result;
    }

    @Override
    public String queryPay(String orderNo) {
        try {
            // 完整的请求地址
            String requestURL = urlPrefix
                    + PayAPIURIList.PAYAPI_QUERYORDER.getValue();

            TreeMap<String, Object> map = new TreeMap<String, Object>();
            map.put("merchantaccount", merchantaccount);
            map.put("orderid", orderNo);

            // 生成RSA签名
            String sign = EncryUtil.handleRSA(map, merchantPrivateKey);
            map.put("sign", sign);

            String info = JSON.toJSONString(map);
            String data = AES.encryptToBase64(info, merchantAesKey);

            // 使用RSA算法将商户自己随机生成的AESkey加密
            String encryptkey = RSA.encrypt(merchantAesKey, yibaoPublicKey);

            StringBuilder builder = new StringBuilder(requestURL).append("?")
                    .append("merchantaccount=").append(merchantaccount)
                    .append("&data=").append(data).append("&encryptkey=")
                    .append(encryptkey);

            // 请求查询订单支付状态接口
            String ybresult = HttpUtil.getFromInputStream(builder.toString(),
                    true);

            // 将一键支付返回的结果进行验签，解密并返回
            return checkYbResult(ybresult);
        } catch (Exception e) {
            logger.error("QueryPay_Exception", e);
        }
        return null;
    }

    @Override
    public void refund() {

    }

    @Override
    public String buildPayCallbackURL() {
        return StringUtils.replace(callbackTmpl, "{tpp}",
                PayAPIType.YEEPAY.getCode());
    }

    /**
     * 将一键支付返回打结果仅限验证，并解密
     * 
     * @param ybResult
     * @return
     */
    private String checkYbResult(String ybResult) {
        if (StringUtils.isBlank(ybResult)) {
            return null;
        }
        if (StringUtils.containsIgnoreCase(ybResult, "error")) {
            logger.error("ybresult contains error!");
            return null;
        }
        // 将易宝返回的字符串转为json对象，并通过解密获取明文处理结果
        RespondJson rj = JSONObject.parseObject(ybResult, RespondJson.class);
        String yb_encryptkey = rj.getEncryptkey();
        String yb_data = rj.getData();
        return checkYbResult(yb_data, yb_encryptkey);
    }

    /**
     * 将一键支付返回的结果进行验证，并解密
     * 
     * @param ybResult
     * @return
     */
    private String checkYbResult(String yb_data, String yb_encryptkey) {
        if (StringUtils.isBlank(yb_data)
                || StringUtils.isBlank(yb_encryptkey)) {
            return null;
        }
        try {
            // 对易宝返回的结果进行验签
            boolean passSign = EncryUtil.checkDecryptAndSign(yb_data,
                    yb_encryptkey, yibaoPublicKey, merchantPrivateKey);

            if (passSign) {
                // 验签通过
                String yb_aeskey = RSA.decrypt(yb_encryptkey,
                        merchantPrivateKey);
                logger.info("YEEPAY支付给商户{}返回结果中使用的aeskey为：{}", merchantaccount,
                        yb_aeskey);
                String payresult_view = AES.decryptFromBase64(yb_data,
                        yb_aeskey);
                logger.info("YEEPAY支付给商户{}结果（aes解密后的明文）：{}", merchantaccount,
                        payresult_view);

                return payresult_view;
            } else {
                logger.info("验签未通过！");
                return null;
            }
        } catch (Exception e) {
            logger.error("CHECK_YB_RESULT_EXCEPTION", e);
        }
        return null;
    }
}