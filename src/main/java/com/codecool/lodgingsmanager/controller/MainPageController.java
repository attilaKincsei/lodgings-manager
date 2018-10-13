package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
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

        if (session == null) {
            response.sendRedirect("/login");
        } else {
            String loggedInEmailAddress = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            WebContext context = new WebContext(request, response, request.getServletContext());
            context.setVariable("emailAddress", loggedInEmailAddress);
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process("index.html", context, response.getWriter());

        }
    }

}
