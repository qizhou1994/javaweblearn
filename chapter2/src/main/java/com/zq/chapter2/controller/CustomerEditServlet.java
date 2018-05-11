package com.zq.chapter2.controller;

import com.zq.chapter2.model.Customer;
import com.zq.chapter2.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/customeredit")
public class CustomerEditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CustomerEditServlet post");

        Map<String,Object> fieldMap = new HashMap<>();
        System.out.println("request.getParameterMap() = " + request.getParameterMap());
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String contact = request.getParameter("contact");
        String telephone = request.getParameter("telephone");
        String email = request.getParameter("email");
        String remark = request.getParameter("remark");


        fieldMap.put("id",id);
        fieldMap.put("name",name);
        fieldMap.put("contact",contact);
        fieldMap.put("telephone",telephone);
        fieldMap.put("email",email);
        fieldMap.put("remark",remark);
        for (String key:fieldMap.keySet()){
            System.out.println("key1 = " + key + ",value = " + fieldMap.get(key));
        }
        CustomerService.getInstance().updateCustomer(Long.parseLong(id),fieldMap);
        response.sendRedirect("/customer");
//        request.getRequestDispatcher("/customer").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        Customer customer = CustomerService.getInstance().getCustomer(Long.parseLong(id));
        request.setAttribute("customer",customer);
        request.getRequestDispatcher("/WEB-INF/view/customer_edit.jsp").forward(request,response);
    }
}
