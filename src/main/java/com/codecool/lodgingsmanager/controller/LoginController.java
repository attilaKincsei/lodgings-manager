package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        engine.process("login.html", context, response.getWriter());
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
//        CustomerDaoSql customerDaoSql = CustomerDaoSql.getInstance();
//        String username = request.getParameter("username");
//        String password = request.getParameter("password");
//        customerDaoSql.loginCustomer(username, password);
    }
}
