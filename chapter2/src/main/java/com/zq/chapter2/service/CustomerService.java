package com.zq.chapter2.service;

import com.zq.chapter2.helper.DatabaseHelper;
import com.zq.chapter2.model.Customer;
import com.zq.chapter2.util.PropsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;


public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private static CustomerService customerService;


    private CustomerService(){

    }

    public static CustomerService getInstance(){
        if(customerService == null){
            synchronized (CustomerService.class) {   //保证了同一时间只能只能有一个对象访问此同步块
                if(customerService == null){
                    customerService = new CustomerService();
                }
            }
        }
        return customerService;
    }

/*    private static final String DRIVER;

    private static final String URL;

    private static final String USERNAME;

    private static final String PASSWORD;

    *//**
     * 获取数据库登录信息
     * 连接数据库驱动
     *//*

    static {
        Properties conf = PropsUtil.loadProps("config.properties");
        DRIVER = conf.getProperty("jdbc.driver");
        URL = conf.getProperty("jdbc.url");
        USERNAME = conf.getProperty("jdbc.username");
        PASSWORD = conf.getProperty("jdbc.password");


        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            LOGGER.error("can note load jdbc driver",e);
        }
    }*/

    /**
     * 获取客户列表
     *
     * @param keyWord
     * @return
     */
    public List<Customer> getCustomerList(String keyWord) {

        return null;
    }

    public List<Customer> getCustomerList() {
            String sql = "SELECT * FROM customer";
            return DatabaseHelper.queryEntityList(Customer.class,sql);

           /* 通过 Apache Commons DbUtils 优化处理
           PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet resultSet = stmt.executeQuery();

            while (resultSet.next()){
                Customer customer = new Customer();
                customer.setId(resultSet.getLong("id"));
                customer.setContact(resultSet.getString("contact"));
                customer.setEmail(resultSet.getString("email"));
                customer.setName(resultSet.getString("name"));
                customer.setTelephone(resultSet.getString("telephone"));
                customer.setRemark(resultSet.getString("remark"));
                customerList.add(customer);
            }

            return customerList;
        } catch (SQLException e) {
            e.printStackTrace();
            LOGGER.error("execute sql fail",e);
            */


    }

    /**
     * 获取客户
     */
    public Customer getCustomer(long id) {
        String sql = "SELECT * FROM customer WHERE id = " + id;
        return DatabaseHelper.queryEntity(Customer.class,sql);
    }

    /**
     * 创建客户
     */
    public boolean createCustomer(Map<String, Object> fieldMap) {
        return DatabaseHelper.insertEntity(Customer.class,fieldMap);
    }

    /**
     * 更新客户
     */
    public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
        return DatabaseHelper.updateEntity(Customer.class,id,fieldMap);
    }

    /**
     * 删除客户
     */
    public boolean deleteCustomer(long id) {
        return DatabaseHelper.deteleEntity(Customer.class,id);
    }


}
