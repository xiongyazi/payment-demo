package com.xzy.paymentdemo;

import com.xzy.paymentdemo.config.WxPayConfig;
import com.xzy.paymentdemo.entity.OrderInfo;
import com.xzy.paymentdemo.service.OrderInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.security.PrivateKey;
import java.util.List;

@SpringBootTest
class PaymentDemoApplicationTests {

    @Resource
    private WxPayConfig wxPayConfig;

    @Resource
    private OrderInfoService orderInfoService;

    @Test
    void contextLoads() {

//        List<OrderInfo> orderInfoList = orderInfoService.listOrderByCreateTimeDesc(1);
//
//        for (OrderInfo orderInfo : orderInfoList) {
//            System.out.println(orderInfo.toString());
//        }
    }

}
