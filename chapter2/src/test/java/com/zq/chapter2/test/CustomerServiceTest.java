package com.zq.chapter2.test;

import com.zq.chapter2.helper.DatabaseHelper;
import com.zq.chapter2.model.Customer;
import com.zq.chapter2.service.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerServiceTest {

    private final CustomerService customerService;

    public CustomerServiceTest(){
        customerService = new CustomerService();
    }

    @Before
    public void init() throws IOException {
        //初始化数据库
        String file = "sql/customer_init.sql";
        DatabaseHelper.executeSqlFile(file);
    }

    @Test
    public void getCustomerListTest ()throws Exception{
        List<Customer> customerList = customerService.getCustomerList();
        Assert.assertEquals(2,customerList.size());
    }

    @Test
    public void getCustomerTest ()throws Exception{
        long id = 1;
        Customer customer = customerService.getCustomer(id);
        Assert.assertNotNull(customer);
    }

    @Test
    public void createCustomerTest()throws Exception{

        Map<String,Object> fieldMap = new HashMap<String,Object>();
        fieldMap.put("name","customer100");
        fieldMap.put("contact","John");
        fieldMap.put("telephone","111111111");
        boolean result = customerService.createCustomer(fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void updateCustomerTest()throws Exception{
        long id = 1;
        Map<String,Object> fieldMap = new HashMap<String,Object>();
        fieldMap.put("contact","Eric");
        boolean result = customerService.updateCustomer(id,fieldMap);
        Assert.assertTrue(result);
    }

    @Test
    public void deleteCustomerTest()throws Exception{
        long id = 1;
        boolean result = customerService.deleteCustomer(id);
        Assert.assertTrue(result);
    }


}
