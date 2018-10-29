package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.Initializer;
import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
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

@WebServlet(urlPatterns = {"/profile", "/edit-profile"})
public class UserProfileController extends HttpServlet {

    private final BaseService<User> userHandler = Initializer.USER_HANDLER;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        WebContext context = new WebContext(request, response, request.getServletContext());

        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());

            User user = userHandler.handleBy(userEmail);
            context.setVariable("userData", user);

            String templateToRender;
            String requestPath = request.getServletPath();

            switch (requestPath) {
                case "/profile":
                    templateToRender = "user_profile.html";
                    break;
                case "/edit-profile":
                    templateToRender = "edit_profile.html";
                    break;
                default:
                    templateToRender = "/login";
                    break;
            }


            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process(templateToRender, context, response.getWriter());

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
            User user = userHandler.handleBy(userEmail);
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
                userHandler.handleUpdate(user);
            } catch (NullPointerException npe) {
                npe.printStackTrace();
                System.out.println("New user could not be created");
            }

            response.sendRedirect("/profile");

        }
    }

}