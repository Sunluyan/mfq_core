package com.mfq.service.sms.provider;

import java.util.Date;
import java.util.Map;

/**
 * Message Provider Interface.
 * @author xingyongshan
 *
 */
public abstract class MessageProvider {

    String name = "";
    
    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public abstract void loadConfiguration(Map<String, Object> setting);
    public abstract String getBalance();
    public abstract String sendBatchMessage(String content, String mobiles);
    public abstract String sendVcodeMessage(String content, String mobile);
    public abstract String sendSingleMessage(String content, String mobile);
    public abstract String sendSingleMessageBytime(String content, String mobile, Date sendtime);
}
