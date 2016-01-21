package com.mfq.task.base;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.mfq.task.FinanceTask;
import com.mfq.task.OperationTask;
import com.mfq.task.OrderCloseTask;

public class ExecuteTaskService {

    private static final int TIME_THREADS = 5;

    private static ScheduledExecutorService scheduledExecutorService = Executors
            .newScheduledThreadPool(TIME_THREADS);

    private static final int FINANCE_CREATE_TIME = 5;
    private static final int ORDER_WAITPAY_TIME = 2;

    public static void execute() {

        // 分期账单定时任务
        scheduledExecutorService.scheduleAtFixedRate(new FinanceTask(), 5,
                FINANCE_CREATE_TIME, TimeUnit.MINUTES);

        // 更新过时订单状态
        scheduledExecutorService.scheduleAtFixedRate(new OrderCloseTask(), 2,
                ORDER_WAITPAY_TIME, TimeUnit.MINUTES);

        //用户操作定时任务
        scheduledExecutorService.scheduleAtFixedRate(new OperationTask(),1,
                1,TimeUnit.MINUTES);

    }
}