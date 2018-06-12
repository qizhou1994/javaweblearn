package com.zq.chapter5.soap;


import com.zq.chapter5.model.Customer;
import com.zq.chapter5.service.CustomerService;
import com.zq.smart_framework.annotation.Inject;
import com.zq.smart_framework.annotation.Service;

/**
 * 客户 SOAP 服务接口实现
 *
 * @author huangyong
 * @since 1.0.0
 */
//@Soap
@Service
public class CustomerSoapServiceImpl implements CustomerSoapService {

    @Inject
    private CustomerService customerService;

    public Customer getCustomer(long customerId) {
        return customerService.getCustomer(customerId);
    }
}
