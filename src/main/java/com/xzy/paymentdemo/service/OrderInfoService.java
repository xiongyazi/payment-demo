package com.xzy.paymentdemo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.xzy.paymentdemo.entity.OrderInfo;
import com.xzy.paymentdemo.enums.OrderStatus;

import java.util.List;

public interface OrderInfoService extends IService<OrderInfo> {

    OrderInfo createOrderByProductId(Long productId);

    void saveCodeUrl(String orderNo, String codeUrl);

    List<OrderInfo> listOrderByCreateTimeDesc();

    void updateStatusByOrderNo(String orderNo, OrderStatus orderStatus);

    String getOrderStatus(String orderNo);

    List<OrderInfo> getNoPayOrderByDuration(int minutes);

    OrderInfo getOrderByOrderNo(String orderNo);
}
