package com.xrc.database.rest;

import com.xrc.database.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;

/**
 * @author xierongchang
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Resource
    IOrderService orderService;

    private final CountDownLatch countDownLatch = new CountDownLatch(10);

    private ThreadLocal<Integer> currentUser = ThreadLocal.withInitial(() -> null);


    @GetMapping("/aa")
    public String aa() {
        try {
            for (int i = 0; i < 100000; i++) {
                orderService.mainWait(countDownLatch);
            }
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello world!";
    }

    @GetMapping("/createOrders")
    public String createOrders() {
        orderService.createOrder("haha");
        return "hello world";
    }

    @GetMapping("/threadLocal")
    public String threadLocal(@RequestParam("userId") Integer userId) {
        //设置用户信息之前先查询一次ThreadLocal中的用户信息
        String before = Thread.currentThread().getName() + ":" + currentUser.get();
        //设置用户信息到ThreadLocal
        currentUser.set(userId);

        //设置用户信息之后再查询一次ThreadLocal中的用户信息
        String after = Thread.currentThread().getName() + ":" + currentUser.get();


        System.out.println("before:"+before);
        System.out.println("after:"+after);

        return "hello world;";
    }
}
