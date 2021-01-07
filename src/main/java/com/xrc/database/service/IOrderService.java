package com.xrc.database.service;

import com.baomidou.mybatisplus.service.IService;
import com.xrc.database.domain.Orders;

import java.util.concurrent.CountDownLatch;

/**
 * @author xierongchang
 */
public interface IOrderService extends IService<Orders> {

    /**
     * 创建订单
     * @param name 线程名称
     */
    void createOrder(String name);

    /**
     * 异步创建订单
     */
    void syncCreateOrders();

    /**
     * 阻塞
     * @param latch
     */
    void mainWait(CountDownLatch latch);
}
