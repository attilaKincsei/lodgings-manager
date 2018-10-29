package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.Initializer;
import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.util.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {

    private final BaseService<User> userHandler = Initializer.USER_HANDLER;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        User guestUser = userHandler.handleBy(Initializer.GUEST_EMAIL);

        context.setVariable("userData", guestUser);

        engine.process("registration.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userType = request.getParameter(UserDataField.USER_TYPE.getInputString());

        String firstName = request.getParameter(UserDataField.FIRST_NAME.getInputString());
        String surname = request.getParameter(UserDataField.SURNAME.getInputString());
        String email = request.getParameter(UserDataField.EMAIL_ADDRESS.getInputString());
        String phoneNumber = request.getParameter(UserDataField.PHONE_NUMBER.getInputString());
        String country = request.getParameter(UserDataField.COUNTRY.getInputString());
        String city = request.getParameter(UserDataField.CITY.getInputString());
        String zipCode = request.getParameter(UserDataField.ZIP_CODE.getInputString());
        String address = request.getParameter(UserDataField.ADDRESS.getInputString());

        String password = request.getParameter(UserDataField.PASSWORD.getInputString());
        String passwordHash = PasswordHashing.hashPassword(password);


        User newUser = UserFactory.createUserInstanceBy(
                UserType.valueOf(userType),
                firstName,
                surname,
                email,
                phoneNumber,
                country,
                city,
                zipCode,
                address,
                passwordHash
        );

        userHandler.handleAddNewUser(newUser);

        response.sendRedirect("/login");
    }
}
