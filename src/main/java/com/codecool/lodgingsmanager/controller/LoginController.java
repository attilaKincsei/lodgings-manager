package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.util.PasswordHashing;
import com.codecool.lodgingsmanager.util.UserDataField;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.persistence.NoResultException;
import javax.servlet.http.*;
import java.io.IOException;

import static com.codecool.lodgingsmanager.config.Initializer.GUEST_EMAIL;

public class LoginController extends HttpServlet {

    private final String servletName;
    private final BaseService<User> userService;

    public LoginController(String servletName, BaseService<User> userService) {
        this.servletName = servletName;
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession oldSession = request.getSession(true);

        String errorMessage;

        String loggedInUserEmail = (String) oldSession.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
        if (loggedInUserEmail == null) {
            errorMessage = "You are not logged in. Register or log in, please";
        } else {
            oldSession.invalidate();
            String requestPath = request.getServletPath();
            if (requestPath.equals("/login/incorrect")) {
                errorMessage = "Incorrect user name or password";
            } else if (loggedInUserEmail.equals(GUEST_EMAIL)) {
                errorMessage = "You are not logged in. Register or log in, please";
            } else {
                errorMessage = "You are logged out";
            }
        }

        HttpSession newSession = request.getSession();
        newSession.setAttribute(UserDataField.EMAIL_ADDRESS.getInputString(), GUEST_EMAIL);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        User guestUser = userService.handleGetUserBy(GUEST_EMAIL);

        WebContext context = new WebContext(request, response, request.getServletContext());
        context.setVariable("userData", guestUser);
        context.setVariable("errorMessage", errorMessage);
        engine.process("login.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String email = request.getParameter(UserDataField.EMAIL_ADDRESS.getInputString());
        String password = request.getParameter(UserDataField.PASSWORD.getInputString());

        try {
            User mightBeUser = PasswordHashing.checkPassword(password, email);
            HttpSession newSession = request.getSession(true);
            newSession.setAttribute(UserDataField.EMAIL_ADDRESS.getInputString(), mightBeUser.getEmail());
            newSession.setMaxInactiveInterval(30*60);

            Cookie userEmail = new Cookie(UserDataField.EMAIL_ADDRESS.getInputString(), mightBeUser.getEmail());
            userEmail.setMaxAge(30*60);
            response.addCookie(userEmail);

            response.sendRedirect("/index");

        } catch (NoResultException | NullPointerException ex) {
            System.out.println("User not in database"); // todo: change it to logger
            response.sendRedirect("/login/incorrect");
        }

    }


}