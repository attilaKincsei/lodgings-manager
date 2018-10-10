package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.util.UserDataField;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = {"/", "/index"})
public class MainPageController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Handling log-in
        HttpSession session = request.getSession(false);

        String loggedInEmailAddress;

        if (session == null || session.isNew()) {
            response.sendRedirect("/login");
            loggedInEmailAddress = "Guest@lodgings_manager.com";
        } else {
            loggedInEmailAddress = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
        }


        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("fullName", loggedInEmailAddress);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        engine.process("index.html", context, response.getWriter());
    }

}
