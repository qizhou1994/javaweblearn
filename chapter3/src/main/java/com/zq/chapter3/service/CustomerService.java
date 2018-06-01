package com.zq.chapter3.service;

import com.zq.chapter3.model.Customer;
import com.zq.smart_framework.annotation.Service;
import com.zq.smart_framework.bean.FileParam;
import com.zq.smart_framework.helper.DatabaseHelper;
import com.zq.smart_framework.helper.UploadHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by zq on 2018/5/20.
 */
@Service
public class CustomerService {
   //获取客户列表

    public List< Customer> getCustomerList() {
        String sql = "SELECT * FROM customer";
        return DatabaseHelper.queryEntityList(Customer.class, sql);
    }

   // 获取客户

    public Customer getCustomer(long id) {
        String sql = "SELECT * FROM customer WHERE id = ?";
        return DatabaseHelper.queryEntity(Customer.class, sql, id);
    }

   //创建客户
/*
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(Customer.class, fieldMap);
    }*/

    public boolean createCustomer(Map<String, Object> fieldMap, FileParam fileParam) {
        boolean result = DatabaseHelper.insertEntity(Customer.class, fieldMap);
        if(result){
            UploadHelper.uploadFile("/temp/upload",fileParam);
        }
        return result;
    }

    //更新客户

    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
    }

    //删除客户

    public boolean deleteCustomer(long id) {
        return DatabaseHelper.deleteEntity(Customer.class, id);
    }
}
