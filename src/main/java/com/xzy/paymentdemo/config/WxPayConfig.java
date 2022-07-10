package com.xzy.paymentdemo.config;

import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.ScheduledUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;

@Configuration
@PropertySource("classpath:wxpay.properties") //读取配置文件
@ConfigurationProperties(prefix = "wxpay") //读取wxpay节点
@Data //使用set方法将wxpay节点中的值填充到当前类的属性中
@Slf4j
public class WxPayConfig {

    // 商户号
    private String mchId;

    // 商户API证书序列号
    private String mchSerialNo;

    // 商户私钥文件
    private String privateKeyPath;

    // APIv3密钥
    private String apiV3Key;

    // APPID
    private String appid;

    // 微信服务器地址
    private String domain;

    // 接收结果通知地址
    private String notifyDomain;


    /**
     * 获取商户的私钥文件
     *
     * @param filename
     * @return
     */
    private PrivateKey getPrivateKey(String filename) {
        try {
            return PemUtil.loadPrivateKey(new FileInputStream(filename));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("私钥文件不存在", e);
        }
    }


    /**
     * 获取签名验证器
     * @return
     */
    @Bean
    public ScheduledUpdateCertificatesVerifier getVerifier(){

        //获取商户私钥
        PrivateKey privateKey = getPrivateKey(privateKeyPath);

        //私钥签名对象
        PrivateKeySigner privateKeySigner = new PrivateKeySigner(mchSerialNo, privateKey);

        //身份认证对象
        WechatPay2Credentials wechatPay2Credentials = new WechatPay2Credentials(mchId, privateKeySigner);

        // 使用定时更新的签名验证器，不需要传入正式
        return new ScheduledUpdateCertificatesVerifier(wechatPay2Credentials,apiV3Key.getBytes(StandardCharsets.UTF_8));
    }


    /**
     * 获取http请求对象
     * @param verifier
     * @return
     */

    @Bean(name = "wxPayNoSignClient")
    public CloseableHttpClient getWxPayNoSignClient(ScheduledUpdateCertificatesVerifier verifier){
        log.info("初始化wxPayNoSignClient");
        //获取商户私钥
        PrivateKey privateKey = getPrivateKey((privateKeyPath));

        //用于构造HttpClient
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create().withMerchant(mchId, mchSerialNo, privateKey).withValidator((response) -> true);

        CloseableHttpClient wxPayNoSignClient = builder.build();
        log.info("wxPayNoSignClient初始化完成");
        return wxPayNoSignClient;

    }

}
