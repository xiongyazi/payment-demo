package com.xzy.paymentdemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xzy.paymentdemo.entity.RefundInfo;

import java.util.List;

public interface RefundInfoService extends IService<RefundInfo> {

    RefundInfo createRefundByOrderNo(String orderNo, String reason);

    void updateRefund(String content);

    List<RefundInfo> getNoRefundOrderByDuration(int minutes);
}
