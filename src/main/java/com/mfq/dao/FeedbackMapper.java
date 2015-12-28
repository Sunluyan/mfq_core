package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.Feedback;

@MFQDao
public interface FeedbackMapper {
    
    public long insertFeedback(Feedback feedback);
 
}
