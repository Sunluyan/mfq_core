package com.mfq.service;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.mfq.bean.OrderFreedom;
import com.mfq.bean.OrderFreedomExample;
import com.mfq.dao.OrderFreedomMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liuzhiguo1 on 16/1/11.
 */
@Service
public class OrderFreedomService {

    private static final Logger logger = LoggerFactory.getLogger(OrderFreedomService.class);

    @Resource
    OrderFreedomMapper orderFreedomMapper;

    public List<OrderFreedom> selectByUid(long uid) {
        OrderFreedomExample example = new OrderFreedomExample();
        example.or().andUidEqualTo(uid);
        return orderFreedomMapper.selectByExample(example);
    }

    public OrderFreedom selectByOrderNo(String orderNo) {
        OrderFreedomExample example = new OrderFreedomExample();
        example.or().andOrderNoEqualTo(orderNo);
        List<OrderFreedom> list = orderFreedomMapper.selectByExample(example);

        if (list != null && list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring/spring.xml");
        OrderFreedomMapper mapper = ac.getBean(OrderFreedomMapper.class);
        List<OrderFreedom> list = mapper.selectByExample(new OrderFreedomExample());


    }

}
