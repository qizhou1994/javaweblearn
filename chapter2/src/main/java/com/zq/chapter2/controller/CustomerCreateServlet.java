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

@WebServlet("/customer_create")
public class CustomerCreateServlet extends HttpServlet {


    /**
     * 进入 创建客户 界面
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/WEB-INF/view/customer_create.jsp").forward(req,resp);
    }


    /**
     * 处理 客户创建请求
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("CustomerServlet post");

        Map<String,Object> fieldMap = new HashMap<>();
        System.out.println("request.getParameterMap() = " + req.getParameterMap());
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String contact = req.getParameter("contact");
        String telephone = req.getParameter("telephone");
        String email = req.getParameter("email");
        String remark = req.getParameter("remark");


        fieldMap.put("id",id);
        fieldMap.put("name",name);
        fieldMap.put("contact",contact);
        fieldMap.put("telephone",telephone);
        fieldMap.put("email",email);
        fieldMap.put("remark",remark);
        for (String key:fieldMap.keySet()){
            System.out.println("key1 = " + key + ",value = " + fieldMap.get(key));
        }
        CustomerService.getInstance().createCustomer(fieldMap);

//        this.doGet(req, resp);
//        resp.sendRedirect("/customer");

        req.getRequestDispatcher("/customer").forward(req,resp);

    }
}
