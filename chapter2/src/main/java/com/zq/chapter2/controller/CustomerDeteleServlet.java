package com.zq.chapter2.controller;

import com.zq.chapter2.model.Customer;
import com.zq.chapter2.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet( "/customerdelete")
public class CustomerDeteleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        CustomerService.getInstance().deleteCustomer(Long.parseLong(id));
        request.getRequestDispatcher("/customer").forward(request,response);
    }
}
