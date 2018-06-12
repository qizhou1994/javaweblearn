package com.zq.chapter5.soap;


import com.zq.chapter5.model.Customer;

/**
 * 客户 SOAP 服务接口
 *
 * @author huangyong
 * @since 1.0.0
 */
public interface CustomerSoapService {

    /**
     * 根据 客户ID 获取客户对象
     *
     * @param customerId 客户ID
     * @return 客户对象
     */
    Customer getCustomer(long customerId);
}
