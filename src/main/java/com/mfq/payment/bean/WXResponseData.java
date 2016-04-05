package com.mfq.payment.bean;

/**
 * Created by liuzhiguo1 on 16/4/5.
 */
public class WXResponseData {

    String return_code;
    String return_msg;

    public WXResponseData() {
        super();
    }

    public WXResponseData(String return_code, String return_msg) {
        this.return_code = return_code;
        this.return_msg = return_msg;
    }

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    @Override
    public String toString() {
        return "return_code =" + this.return_code + ", return_msg=" + this.return_msg;
    }
}
