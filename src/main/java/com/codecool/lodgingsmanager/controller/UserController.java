package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.Initializer;
import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.util.UserDataField;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.codecool.lodgingsmanager.config.Initializer.GUEST_EMAIL;

@WebServlet(urlPatterns = {"/user", "/user/edit", "/user/delete"})
public class UserController extends HttpServlet {

    private final BaseService<User> userService = Initializer.USER_SERVICE;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        WebContext context = new WebContext(request, response, request.getServletContext());

        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            User user = userService.handleGetUserBy(userEmail);

            String requestPath = request.getServletPath();
            String userId = request.getParameter("userId"); // todo: send user id with post request, not safe

            String templateToRender = userService.handleCRUDBy(requestPath, userId); // todo: thing about a better name

            if (templateToRender == null) {
                response.sendRedirect("/login");
            } else {
                TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
                context.setVariable("userData", user);
                engine.process(templateToRender, context, response.getWriter());
            }


        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {


        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()) == null) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            WebContext context = new WebContext(request, response, request.getServletContext());
            User user = userService.handleGetUserBy(userEmail);
            String userClass = user.getClass().getName();
            context.setVariable("userData", user);
            context.setVariable("userClass", userClass);

            String firstName = request.getParameter(UserDataField.FIRST_NAME.getInputString());
            String surname = request.getParameter(UserDataField.SURNAME.getInputString());
            String phoneNumber = request.getParameter(UserDataField.PHONE_NUMBER.getInputString());
            String country = request.getParameter(UserDataField.COUNTRY.getInputString());
            String city = request.getParameter(UserDataField.CITY.getInputString());
            String zipCode = request.getParameter(UserDataField.ZIP_CODE.getInputString());
            String address = request.getParameter(UserDataField.ADDRESS.getInputString());

            user.setFirstName(firstName);
            user.setSurname(surname);
            user.setPhoneNumber(phoneNumber);
            user.setCountry(country);
            user.setCity(city);
            user.setZipCode(zipCode);
            user.setAddress(address);

            try {
                userService.handleUpdate(user);
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                System.out.println("New user could not be created");
            }

            response.sendRedirect("/user");

        }
    }

}