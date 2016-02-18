package com.mfq.service;

import com.google.common.collect.Lists;
import com.mfq.bean.*;
import com.mfq.bean.app.Refund2App;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RefundService {

    private static final Logger logger = LoggerFactory
            .getLogger(RefundService.class);

    @Resource
    ProductService productService;
    @Resource
    HospitalService hospitalService;
    @Resource
    OrderService orderService;

    public List<Refund2App> create2RefundApps(List<Refund> list){
        List<Refund2App> data = Lists.newArrayList();
        for(Refund refund:list){
            data.add(createRefundApp(refund));
        }
        return data;
    }


    public Refund2App createRefundApp(Refund refund){
        String orderNo = refund.getOrderNo();
        OrderInfo info = orderService.findByOrderNo(orderNo);
        Product product = productService.findById(info.getPid());
        Hospital hospital = hospitalService.findById(product.getHospitalId());
        Refund2App app =new Refund2App(refund, product.getName(), product.getImg(), hospital.getName(),
                info.getPrice(), info.getPrice());
        return app;

    }
}


















