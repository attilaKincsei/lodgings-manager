package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.Initializer;
import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.model.builder.AddressBuilder;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.util.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationController extends HttpServlet {

    private final String servletName;
    private final BaseService<User> userService;

    public RegistrationController(String servletName, BaseService<User> userService) {
        this.servletName = servletName;
        this.userService = userService;
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        User guestUser = userService.handleGetUserBy(Initializer.GUEST_EMAIL);

        context.setVariable("userData", guestUser);

        engine.process("registration.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userType = request.getParameter(FieldType.USER_TYPE.getInputString());

        String firstName = request.getParameter(FieldType.FIRST_NAME.getInputString());
        String surname = request.getParameter(FieldType.SURNAME.getInputString());
        String email = request.getParameter(FieldType.EMAIL_ADDRESS.getInputString());
        String phoneNumber = request.getParameter(FieldType.PHONE_NUMBER.getInputString());
        String country = request.getParameter(FieldType.COUNTRY.getInputString());
        String city = request.getParameter(FieldType.CITY.getInputString());
        String zipCode = request.getParameter(FieldType.ZIP_CODE.getInputString());
        String address = request.getParameter(FieldType.ADDRESS.getInputString());

        String password = request.getParameter(FieldType.PASSWORD.getInputString());
        String passwordHash = PasswordHashing.hashPassword(password);


        AddressBuilder fullAddress = new AddressBuilder(country, city, zipCode, address);

        User newUser = UserFactory.createUserInstanceBy(
                UserType.valueOf(userType),
                firstName,
                surname,
                email,
                phoneNumber,
                passwordHash,
                fullAddress
        );

        userService.handleAddPost(newUser);

        response.sendRedirect("/login");
    }
}
