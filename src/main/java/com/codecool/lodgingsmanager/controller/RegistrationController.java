package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.util.UserDataField;
import com.codecool.lodgingsmanager.util.UserFactory;
import com.codecool.lodgingsmanager.util.UserType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class RegistrationController extends HttpServlet {

    private UserDaoDb userDataManager = new UserDaoDb();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
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

        // Todo: check passwords matching (unnecessary)
        // Todo: generate password hash with jBcrypt
        String password = request.getParameter(UserDataField.PASSWORD.getInputString());
        String passwordConfirmation = request.getParameter(UserDataField.PASSWORD_CONFIRMATION.getInputString());
        String passwordHash = "";


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
                password // todo: change this to passwordHash
        );

        System.out.println(newUser);
        System.out.println(newUser.getClass().getName());
        try {
            userDataManager.add(newUser);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            System.out.println("New user could not be created");
        }

        response.sendRedirect("/login");
    }

}
