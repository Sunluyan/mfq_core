package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.wechat.WeChatMsg;

@MFQDao
public interface WeChatMsgMapper {

    public Long saveWeChatMsg(WeChatMsg msg);
}
