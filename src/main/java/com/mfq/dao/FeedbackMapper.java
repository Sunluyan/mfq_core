package com.mfq.dao;

import com.mfq.annotation.MFQDao;
import com.mfq.bean.Feedback;
import org.springframework.stereotype.Component;

@MFQDao
@Component
public interface FeedbackMapper {
    
    public long insertFeedback(Feedback feedback);
 
}
