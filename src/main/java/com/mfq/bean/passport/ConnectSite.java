package com.mfq.bean.passport;

public enum ConnectSite {
    Weibo(1),
    Qq(2),
    Renren(3),
    Kaixin(4),
    Douban(5),
    Baidu(6),
    Weixin(7),
    WeixinUnion(8);

    private final int value;

    private ConnectSite(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ConnectSite fromValue(int value) {
        for(ConnectSite site : ConnectSite.values()){
            if(value == site.getValue()){
                return site;
            }
        }
        return null;
    }
}
