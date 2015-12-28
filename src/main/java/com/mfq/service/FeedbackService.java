package com.mfq.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mfq.bean.Feedback;
import com.mfq.dao.FeedbackMapper;

@Service
public class FeedbackService {

    private static final Logger logger = LoggerFactory
            .getLogger(FeedbackService.class);

    @Resource
    FeedbackMapper mapper;

    public long submitFeedback(long uid, String content) throws Exception {
    	
    	Feedback fk = new Feedback();
    	fk.setUid(uid);
    	fk.setCheckFlag(0);
    	fk.setCheckUser("");
    	fk.setContent(content);
    	logger.info(" feedback submit {}|{}", uid ,content);
    	return mapper.insertFeedback(fk);

    }

}