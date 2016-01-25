package com.mfq.service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mfq.bean.*;
import com.mfq.bean.app.CouponInfo2App;
import com.mfq.bean.app.OrderInfo2App;
import com.mfq.bean.app.ProductInfo2App;
import com.mfq.bean.app.Refund2App;
import com.mfq.bean.area.City;
import com.mfq.bean.coupon.Coupon;
import com.mfq.bean.user.User;
import com.mfq.bean.user.UserQuota;
import com.mfq.constants.*;
import com.mfq.dao.OrderFreedomMapper;
import com.mfq.dao.OrderInfoMapper;
import com.mfq.dao.area.CityMapper;
import com.mfq.dataservice.context.UserIdHolder;
import com.mfq.net.wukongbao.pojo.WkbConstants;
import com.mfq.service.sms.SMSService;
import com.mfq.service.user.UserQuotaService;
import com.mfq.service.user.UserService;
import com.mfq.utils.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Ref;
import java.text.DecimalFormat;
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
    @Resource
    OrderFreedomService freedomService;

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
        if(info!=null) {
            Product product = productService.findById(info.getPid());
            Hospital hospital = hospitalService.findById(product.getHospitalId());
            Refund2App app =new Refund2App(refund, product.getName(), product.getImg(), hospital.getName(),
                    info.getPrice(), info.getPrice());
            return app;
        }else {
            OrderFreedom freedom = freedomService.selectByOrderNo(orderNo);
            Hospital hospital = hospitalService.findById(freedom.getHospitalId());
            Refund2App app = new Refund2App(refund, freedom.getProname(), "", hospital.getName(),
                    freedom.getPrice(),freedom.getOnlinePay());
            return app;
        }


    }
}


















