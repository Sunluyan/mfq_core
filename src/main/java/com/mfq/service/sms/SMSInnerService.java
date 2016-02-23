package com.mfq.service.sms;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mfq.utils.DateUtil;
import com.mfq.utils.RunnerUtils;

/**
 * SMS服务，对内提供使用SMSInnerService，对外网提供请使用SMSService
 * 
 * @author xingyongshan
 *
 */
@Service
public class SMSInnerService {

	private static Logger logger = LoggerFactory
            .getLogger(SMSInnerService.class);
	
    @Resource
    MobileMessageClient client; // if use me, please ask author!

    /**
     * 异步发送，返回值不代表发送成功
     * 
     * @param content
     * @param mobile
     * @return
     */
    public boolean sendMessage(final String content, final String mobile) {
        // hack for bad mobile
        /*
         * if("18612258336".equals(mobile)){ return false; }
         */
        RunnerUtils.submit(new Thread() {
            @Override
            public void run() {
                // String result =
                client.sendSingleMessage(content, mobile);
            }
        });
        return true;
    }

    /**
     * 异步发送，返回值不代表发送成功
     * 
     * @param codecontent
     * @param mobile
     * @return
     */
    public boolean sendVcodeMessage(final String codecontent,
            final String mobile, final boolean isReset) {
        RunnerUtils.submit(new Thread() {
            @Override
            public void run() {
                // String result =
                client.sendVcodeMessage(codecontent, mobile, isReset);
            }
        });

        return true;
    }

    /**
     * 异步发送，返回值不代表发送成功 非夜间发送
     * 
     * @param content
     * @param mobile
     * @return
     */
    public boolean sendMessageNotNight(String content, String mobile) {
        return sendMessageByTime(content, mobile,
                DateUtil.getGoodSendDateTime());
    }

    /**
     * 异步发送，返回值不代表发送成功 定时发送
     * 
     * @param content
     * @param mobile
     * @param sendtime
     * @return
     */
    public boolean sendMessageByTime(String content, String mobile,
            String sendtime) {
        Date datetime = null;
        if (StringUtils.trimToNull(sendtime) != null) {
            datetime = DateUtil.convertLong(sendtime);
        }
        return sendMessageByTime(content, mobile, datetime);
    }

    /**
     * 异步发送，返回值不代表发送成功 定时发送－参数重载
     * 
     * @param content
     * @param mobile
     * @param sendtime
     * @return
     */
    public boolean sendMessageByTime(final String content, final String mobile,
            final Date sendtime) {
        RunnerUtils.submit(new Thread() {
            @Override
            public void run() {
                Date datetime = sendtime;
                // String result =
                client.sendSingleMessageBytime(content, mobile, datetime);
            }
        });
        return true;
    }

    /**
     * 异步发送，返回值不代表发送成功 
     * 批量发布短信
     * @param content
     * @param mobilelist
     * @return
     */
    public boolean sendBatchMessage(final String content, final List<String> mobilelist) {
        RunnerUtils.submit(new Thread() {
            @Override
            public void run() {
                String mobiles = StringUtils.join(mobilelist, ",");
                logger.info("====>send BatchMessage");
                client.sendBatchMessage(content, mobiles);
            }
        });
        return true;
    }
}
