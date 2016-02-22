package com.mfq.payment.impl;

import com.google.common.collect.Maps;
import com.mfq.annotation.PayAPIImpl;
import com.mfq.bean.*;
import com.mfq.constants.ErrorCodes;
import com.mfq.constants.OrderType;
import com.mfq.constants.PayStatus;
import com.mfq.dataservice.context.AppContext;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.helper.SignHelper;
import com.mfq.payment.BasePaymentService;
import com.mfq.payment.PayAPIType;
import com.mfq.payment.util.unionpay.BeeCloudConfigure;
import com.mfq.payment.util.wechat.Configure;
import com.mfq.payment.util.wechat.HttpsUtil;
import com.mfq.payment.util.wechat.PayReqData;
import com.mfq.service.*;
import com.mfq.utils.*;
import net.sf.json.util.JSONUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuzhiguo1 on 15/12/28.
 */
@PayAPIImpl(payAPIType = PayAPIType.UNIONPAY)
@Service
public class UnionpayServiceImpl extends BasePaymentService {
    public static final Logger logger =
            LoggerFactory.getLogger(UnionpayServiceImpl.class);


    @Resource
    OrderService orderService;
    @Resource
    ProductService productService;
    @Resource
    PayRecordService payRecordService;
    @Resource
    PayService payService;


    public String genNonceStr() {
        return "123";
    }

    public static String getBCAppSign(String app_id, long timestamp, String app_secret) {
        String sign = MD5Util.md5Digest(app_id + timestamp + app_secret).toLowerCase();
        return sign;
    }

    public static String getBCWebhookSign(String app_id, long timestamp, String app_secret) {
        String sign = MD5Util.md5Digest(app_id + app_secret + timestamp).toLowerCase();
        return sign;
    }

    @Override
    public String goPay(HttpServletRequest request, HttpServletResponse response, Map<String, Object> params, OrderType orderType) {
        String ret = null;
        try {
            String orderNo = (String) params.get("order_no");
            String pname = "";
            params.put("amount",0.01);//// TODO: 16/2/22 强制改成1分,别忘了改回来. 
            Map<String, Object> optional = new HashMap<>();
            optional.put("orderNo", orderNo);
            optional.put("amount", params.get("amount"));
            optional.put("uid", UserIdHolder.getLongUid());
            if (orderType == OrderType.RECHARGE) {
                pname = "美分期个人余额充值－" + String.valueOf(params.get("amount"));
            } else if (orderType == OrderType.REFUND) {
                pname = "美分期还款－" + String.valueOf(params.get("amount"));
            } else if(orderType == OrderType.ONLINE){
                OrderInfo order = orderService.findByOrderNo(orderNo);
                Product product = productService.findById(order.getPid());
                pname = product.getName();
            }

            //报文前准备

            String app_secret = null;
            if(Config.isDev()){
                app_secret = BeeCloudConfigure.APPSECRET;
            }else{
                app_secret = BeeCloudConfigure.TESTSECRET;
            }

            //开始制作请求报文
            String app_id = BeeCloudConfigure.APPID;
            long timestamp = new Date().getTime();
            String app_sign = getBCAppSign(app_id, timestamp, app_secret);
            String channel = BeeCloudConfigure.CHANNEL;
            Integer total_fee = (int) (Float.parseFloat(params.get("amount").toString()) * 100);
            String bill_no = orderNo;
            String title = pname;
            if (title.length() > 16) {
                title.substring(0, 16);
            }


            Map<String, Object> httpParams = new HashMap<>();
            httpParams.put("app_id", app_id);
            httpParams.put("timestamp", timestamp);
            httpParams.put("app_sign", app_sign);
            httpParams.put("channel", channel);
            httpParams.put("total_fee", total_fee);
            httpParams.put("bill_no", bill_no);
            httpParams.put("title", title);
            httpParams.put("optional", optional);//发过去的豹纹中有 orderNo 和 uid

            ret = JsonUtil.successResultJson(httpParams);
            logger.info("ret = {}", ret);
        } catch (Exception e) {
            logger.error("UnionpayERROR", e);
            ret = JsonUtil.toJson(ErrorCodes.CORE_ERROR, "UnionpayError",
                    null);
        }
        return ret;
    }

    @Override
    public PayCallbackResult payCallback(HttpServletRequest request, HttpServletResponse response, String ret) {

        return null;
    }


    @Override
    public void refund() {

    }

    @Override
    public String queryPay(String orderNo) {
        return null;
    }

    @Override
    public String buildPayCallbackURL() {
        return null;
    }

    /**
     * beeCloud的回调Service
     *
     * @param result
     * @return
     */
    public String beeCloudCallback(BeeCloudResult result, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String ret = "";
        logger.info("BeeCloudResult : {}", result);
        //1,支付成功.2,付款是为了做什么.3,根据不同的目的修改不同的东西
        if (result.getTrade_success()) {
            String orderNo = result.getOptional().get("orderNo").toString();
            if (payService.getOrderType(orderNo) == OrderType.RECHARGE) {//充值

                payService.updateRechargePayOk(result, PayStatus.PAID);

            } else if (payService.getOrderType(orderNo) == OrderType.REFUND) {//分期还款

                payService.updateOrderRefundOk(result);

            } else if (payService.getOrderType(orderNo) == OrderType.ONLINE) {//订单支付

                payService.updateOrderPayOk(result);

            }else{
                throw new Exception("订单号类型错误");
            }
        } else {
            throw new Exception("支付未成功");
        }
        return ret;
    }

    //检查BeeCloud的签名是否正确
    public static boolean checkSign(Long timestamp, String signFromServer) {
        String appid = BeeCloudConfigure.APPID;
        String app_secret = BeeCloudConfigure.APPSECRET;
        String sign = getBCWebhookSign(appid, timestamp, app_secret);
        return sign.equals(signFromServer);
    }

    public static void main(String[] args) {
//        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        //UnionpayServiceImpl service = ac.getBean(UnionpayServiceImpl.class);
//        String sign="e427a5facfc899d06a97b651606de80f";
        long timestamp = 1451471248000l;
//        service.checkSign(timestamp,sign);
        System.out.println(getBCWebhookSign("967f5a80-ae09-4c46-b29e-cb0843887eed", timestamp, "31b648a9-2837-461d-a129-e46a5bab5fca"));


    }


}
