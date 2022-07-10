package com.xzy.paymentdemo.task;

import com.xzy.paymentdemo.entity.OrderInfo;
import com.xzy.paymentdemo.service.OrderInfoService;
import com.xzy.paymentdemo.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Component
public class WxPayTask {

    @Resource
    private OrderInfoService orderInfoService;

    @Resource
    private WxPayService wxPayService;

    @Scheduled(cron = "0/30 * * * * ?")
    public void orderConfirm() throws Exception {
        log.info("orderConfirm被执行。。。");

        List<OrderInfo> orderInfoList = orderInfoService.getNoPayOrderByDuration(5);

        for (OrderInfo orderInfo : orderInfoList) {
            String orderNo = orderInfo.getOrderNo();
            log.warn("超时订单 ===> {}",orderNo);

            wxPayService.checkOrderStatus(orderNo);
        }


    }

}


