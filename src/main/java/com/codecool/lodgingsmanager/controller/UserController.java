package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.util.FieldType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.codecool.lodgingsmanager.config.Initializer.GUEST_EMAIL;


public class UserController extends HttpServlet {

    private final String servletName;
    private final BaseService<User> userService;

    public UserController(String servletName, BaseService<User> userService) {
        this.servletName = servletName;
        this.userService = userService;
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        WebContext context = new WebContext(request, response, request.getServletContext());

        if (session == null || session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString());
            System.out.println("\n--------------------------\n" + userEmail);
            User user = userService.handleGetUserBy(userEmail);

            String requestPath = request.getServletPath();
            String userId = request.getParameter("userId"); // todo: send user id with post request, not safe

            String templateToRender = userService.handleCrudGetBy(requestPath, userId); // todo: thing about a better name

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
        if (session == null || session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString()) == null) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString());
            String firstName = request.getParameter(FieldType.FIRST_NAME.getInputString());
            String surname = request.getParameter(FieldType.SURNAME.getInputString());

            String oldPassword = request.getParameter(FieldType.PASSWORD.getInputString());
            String newPassword = request.getParameter(FieldType.PASSWORD_CONFIRMATION.getInputString());

            String phoneNumber = request.getParameter(FieldType.PHONE_NUMBER.getInputString());
            String country = request.getParameter(FieldType.COUNTRY.getInputString());
            String city = request.getParameter(FieldType.CITY.getInputString());
            String zipCode = request.getParameter(FieldType.ZIP_CODE.getInputString());
            String address = request.getParameter(FieldType.ADDRESS.getInputString());

            String requestPath = request.getServletPath();

            boolean isOldPasswrdCorrect = userService.handleAddAndEditPost(
                    userEmail, firstName, surname, oldPassword, newPassword, phoneNumber, country, city, zipCode, address, requestPath,
                    "fake1", "fake2", "fake3");

            if (isOldPasswrdCorrect) {
                response.sendRedirect("/user");
            } else {
                response.sendRedirect("/user/edit");
            }

        }
    }

}