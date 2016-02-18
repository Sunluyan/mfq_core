package com.mfq.service.tesk;

import com.mfq.bean.user.User;
import com.mfq.service.sms.SMSService;
import com.mfq.service.user.UserService;
import com.mfq.utils.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by hui
 */
@Service
public class TaskSmsService {

    Logger logger = LoggerFactory.getLogger(TaskSmsService.class);

    @Resource
    SMSService smsService;
    @Resource
    UserService userService;
    public boolean sendBachSms(String mobile, String msg){

        boolean rest = false;

        try {
            smsService.sendBatchSms(mobile, msg);

        }catch (Exception e){
            logger.error("task sms batch si error {}", e);
        }


        return rest;
    }

    private  static int page =0;
    private final int size = 5;
    private static String isRun=Config.getItem("task_start");

////    @Scheduled(cron="10 */1 * * * ?")
//    @Transactional
//    public void doTask(){
//        int start = page*size;
//        logger.info("task is run .... p "+page+","+size+","+isRun+","+start);
//        if(!"1".equals(isRun)){
//            return;
//        }
//
//        List<User> users = userService.queryUsersByPage(start, size);
//        if(users==null||users.size()<size){
//            isRun="0";
//        }else {
//            page++;
//        }
//        StringBuffer st = new StringBuffer("");
//        for (User u:users){
//            st.append(u.getMobile()+",");
//        }
//        String sts =st.substring(0,st.length()-1);
//        String content = Config.getItem("sms_content");
//        if(content==null||"".equals(content)){
//            return;
//        }
//
//        sendBachSms(sts, content);
//    }


    public static void main(String[] args) {

        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        UserService userService = ac.getBean(UserService.class);
//        List<User> users = userService.queryUsersByPage(page, size);

        System.out.println("");
    }
}