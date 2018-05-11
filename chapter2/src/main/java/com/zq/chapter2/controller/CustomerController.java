package com.zq.chapter2.controller;




import com.zq.chapter2.model.Customer;
import com.zq.chapter2.service.CustomerService;


import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.tags.Param;


import javax.inject.Inject;

import javax.xml.ws.Action;
import java.util.List;

@Controller
public class CustomerController {

    @Inject
    private CustomerService customerService;



     @Action(output = "get:/customer")
    public View index(Param param){
        List<Customer> customerList = customerService.getCustomerList();
//        return new View("customer.jsp").addModel("customerList",customerList); 这一句的作用我明白 但是语法是？
        return null;
     }



}
