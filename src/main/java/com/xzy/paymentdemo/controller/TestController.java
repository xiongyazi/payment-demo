package com.xzy.paymentdemo.controller;

import com.xzy.paymentdemo.config.WxPayConfig;
import com.xzy.paymentdemo.vo.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "测试控制器")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Resource
    private WxPayConfig wxPayConfig;

    @GetMapping
    public R getWxpayConfig(){
        String apiV3Key = wxPayConfig.getApiV3Key();
        return R.ok().data("apiV3Key",apiV3Key);
    }
}
