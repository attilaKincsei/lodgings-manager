package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.util.UserDataField;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.codecool.lodgingsmanager.config.Initializer.GUEST_EMAIL;

public class ToDoController extends HttpServlet {

    private final String servletName;
    private final BaseService<Lodgings> lodgingsService;

    public ToDoController(String servletName, BaseService<Lodgings> lodgingsService) {
        this.servletName = servletName;
        this.lodgingsService = lodgingsService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Handling log-in
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {

            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            User user = lodgingsService.handleGetUserBy(userEmail);

            WebContext context = new WebContext(request, response, request.getServletContext());
            context.setVariable("userData", user);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process("add_todo.html", context, response.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {


    }

    }

}