package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.util.PasswordHashing;
import com.codecool.lodgingsmanager.util.UserDataField;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login"})
public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        String emailAddress = "Guest@lodgings_manager.com";
        String errorMessage;

        if (session != null && session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()) != null) {
            emailAddress = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            session.removeAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            errorMessage = "You have just logged out. Register or log in, please";
        } else {
            errorMessage = "You are not logged in. Register or log in, please";
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("emailAddress", emailAddress);
        context.setVariable("errorMessage", errorMessage);
        engine.process("login.html", context, response.getWriter());
        }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(true);

        String email = request.getParameter(UserDataField.EMAIL_ADDRESS.getInputString());
        String password = request.getParameter(UserDataField.PASSWORD.getInputString());

        User mightBeUser = PasswordHashing.checkPassword(password, email);
        if (mightBeUser == null) {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            WebContext context = new WebContext(request, response, request.getServletContext());
            context.setVariable("errorMessage", "The email or password is incorrect.");
            engine.process("login.html", context, response.getWriter());
        } else {
            session.setAttribute(UserDataField.EMAIL_ADDRESS.getInputString(), mightBeUser.getEmail());
            session.setMaxInactiveInterval(30*60);

            Cookie userEmail = new Cookie(UserDataField.EMAIL_ADDRESS.getInputString(), mightBeUser.getEmail());
            userEmail.setMaxAge(30*60);
            response.addCookie(userEmail);


            response.sendRedirect("/profile");

        }

    }


}
