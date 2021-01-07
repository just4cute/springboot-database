package com.xrc.database.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.tools.corba.se.idl.constExpr.Or;
import com.xrc.database.dao.OrderDao;
import com.xrc.database.domain.Orders;
import com.xrc.database.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author xierongchang
 */
@Service("orderService")
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderDao, Orders> implements IOrderService {

    @Resource
    OrderDao orderDao;

    @Override
    public void createOrder(String name) {
        Orders orders = new Orders();
        orders.setId(IdUtil.simpleUUID());
        orders.setCreateTime(new Date());
        orders.setNo(IdUtil.simpleUUID());
        orders.setPrice(RandomUtil.randomBigDecimal());
        orders.setUpdateTime(new Date());
        orders.setName(name);
        orderDao.insert(orders);
    }

    @Override
    @Async("taskExecutor")
    public void syncCreateOrders() {
        log.info("start executeAsync");
        try {
            System.out.println("当前运行的线程名称：" + Thread.currentThread().getName());
            Orders orders = new Orders();
            orders.setId(IdUtil.simpleUUID());
            orders.setCreateTime(new Date());
            orders.setNo(IdUtil.simpleUUID());
            orders.setPrice(RandomUtil.randomBigDecimal());
            orders.setUpdateTime(new Date());
            orders.setName(Thread.currentThread().getName() + RandomUtil.randomNumbers(10));
            orderDao.insert(orders);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("end executeAsync");
    }


    @Override
    @Async("taskExecutor")
    public void mainWait(CountDownLatch countDownLatch) {
        try {
            System.out.println("线程" + Thread.currentThread().getId() + "开始执行");
            for (int i = 1; i < 100000; i++) {
                System.out.println("线程" + Thread.currentThread().getId() + i + "开始执行");
                Orders orders = new Orders();
                orders.setId(IdUtil.simpleUUID());
                orders.setCreateTime(new Date());
                orders.setNo(IdUtil.simpleUUID());
                orders.setPrice(RandomUtil.randomBigDecimal());
                orders.setUpdateTime(new Date());
                orders.setName(Thread.currentThread().getName() + RandomUtil.randomNumbers(10));
                orderDao.insert(orders);
            }
            System.out.println("线程" + Thread.currentThread().getId() + "执行结束");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }
    }

}
