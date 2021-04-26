package com.ss.springcloud.service.impl;

import com.ss.springcloud.dao.PaymentDao;
import com.ss.springcloud.entities.Payment;
import com.ss.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class  PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    public int create(Payment payment){
        return paymentDao.create(payment);
    }

    public Payment getPaymentById(long id){
        return paymentDao.getPaymentById(id);
    }

}
