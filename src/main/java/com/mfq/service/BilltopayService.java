package com.mfq.service;

import com.mfq.bean.Billtopay;
import com.mfq.bean.BilltopayExample;
import com.mfq.dao.BilltopayMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by liuzhiguo1 on 16/2/26.
 */
@Service
public class BilltopayService {

    @Resource
    BilltopayMapper mapper;
    @Resource
    OrderService orderService;

    public String makePayNo(String billsNo){
        String payNo = orderService.makeOrderNo(1);
        payNo = payNo.replace("mn","pa");

        Billtopay billtopay = new Billtopay();
        billtopay.setBillNo(billsNo);
        billtopay.setPayNo(payNo);
        mapper.insertSelective(billtopay);
        return payNo;
    }

    public String payNoToBillsNo(String payNo){
        BilltopayExample example = new BilltopayExample();
        example.or().andPayNoEqualTo(payNo);
        Billtopay billtopay = mapper.selectByExampleWithBLOBs(example).get(0);
        return billtopay.getBillNo();
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        BilltopayService service = ac.getBean(BilltopayService.class);
        System.out.println(service.payNoToBillsNo("pa2016022711063050800001"));

    }
}
