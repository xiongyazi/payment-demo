package com.xzy.paymentdemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xzy.paymentdemo.entity.Product;
import com.xzy.paymentdemo.mapper.ProductMapper;
import com.xzy.paymentdemo.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

}
