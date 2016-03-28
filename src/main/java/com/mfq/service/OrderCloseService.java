package com.mfq.service;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.mfq.bean.OrderInfo;
import com.mfq.constants.OrderStatus;
import com.mfq.constants.ProductType;

@Service
public class OrderCloseService {

    private static final Logger logger = LoggerFactory
            .getLogger(OrderCloseService.class);

    private static final long GENERAL_OVERTIME = 1 * 60 * 60 * 1000; // 1hour
    private static final long SECKILL_OVERTIME = 5 * 60 * 1000; // 5min
    private static final long SPECIAL_OVERTIME = 1 * 60 * 60 * 1000; // 1hour

    @Resource
    OrderService orderService;

//    @Scheduled(cron="0 */60 * * * ?")
//    public void start() {
//        handleGeneralOrder();
//        handleSecKillOrder();
//        handleSpecialOrder();
//    }

    /**
     * 处理普通订单的环节，普通订单失效时间为1小时
     */
    public void handleGeneralOrder() {
        try {
            List<OrderInfo> list = orderService.findByTypeAndStatus(
                    ProductType.NORMAL, OrderStatus.BOOK_OK);
            if (CollectionUtils.isEmpty(list)) {
                logger.info("general_order_size is:{}", 0);
                return;
            }
            logger.info("general_order_size is:{}", list.size());
            for (OrderInfo order : list) {
                if ((new Date()).getTime()
                        - order.getUpdatedAt().getTime() > GENERAL_OVERTIME) { // 超过时间的
                    orderService.updateOrder(order.getId(),
                            OrderStatus.BOOK_OK.getValue(),
                            OrderStatus.CANCEL_OK.getValue());
                }
            }
        } catch (Exception e) {
            logger.error("Handle_General_Order_Exception", e);
        }
    }

    /**
     * 处理秒杀订单的环节
     */
    public void handleSecKillOrder() {
        try {
            List<OrderInfo> list = orderService.findByTypeAndStatus(
                    ProductType.SECKILLING, OrderStatus.BOOK_OK);
            if (CollectionUtils.isEmpty(list)) {
                logger.info("seckill_order_size is:{}", 0);
                return;
            }
            logger.info("seckill_order_size is:{}", list.size());
            for (OrderInfo order : list) {
                if ((new Date()).getTime()
                        - order.getUpdatedAt().getTime() > SECKILL_OVERTIME) { // 超过时间的
                    orderService.updateOrder(order.getId(),
                            OrderStatus.BOOK_OK.getValue(),
                            OrderStatus.CANCEL_OK.getValue());
                }
            }
        } catch (Exception e) {
            logger.error("Handle_SecKill_Order_Exception", e);
        }
    }

    /**
     * 处理特价订单的环节
     */
    public void handleSpecialOrder() {
        try {
            List<OrderInfo> list = orderService.findByTypeAndStatus(
                    ProductType.SPECIAL, OrderStatus.BOOK_OK);
            if (CollectionUtils.isEmpty(list)) {
                logger.info("special_order_size is:{}", 0);
                return;
            }
            logger.info("special_order_size is:{}", list.size());
            for (OrderInfo order : list) {
                if ((new Date()).getTime()
                        - order.getUpdatedAt().getTime() > SPECIAL_OVERTIME) { // 超过时间的
                    orderService.updateOrder(order.getId(),
                            OrderStatus.BOOK_OK.getValue(),
                            OrderStatus.CANCEL_OK.getValue());
                }
            }
        } catch (Exception e) {
            logger.error("Handle_Special_Order_Exception", e);
        }
    }
}