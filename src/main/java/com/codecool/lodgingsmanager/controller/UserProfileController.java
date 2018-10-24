package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.LodgingsDaoDb;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.Lodgings;
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
import java.util.List;

@WebServlet(urlPatterns = {"/profile", "/edit-profile"})
public class UserProfileController extends HttpServlet {


    // TODO: CREATE DROPDOWN MENU FOR USER IN NAV TEMPLATE
    // REFACTOR CONTROLLERS

    private UserDao userDataManager = new UserDaoDb();
    private LodgingsDao lodgingsDataManager = new LodgingsDaoDb();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        WebContext context = new WebContext(request, response, request.getServletContext());

        if (session == null) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());

            User user = userDataManager.findIdBy(userEmail);

            List<Lodgings> lodgingsList = lodgingsDataManager.getAllLodgingsBy((long) user.getId());
            context.setVariable("lodgings", lodgingsList);

            // TODO: same in ever controller: can be created at initialization?
            context.setVariable("userData", user);

            String templateToRender;
            String requestPath = request.getServletPath();

            if (requestPath.equals("/profile")) {
                templateToRender = "user_profile.html";
            } else if (requestPath.equals("/edit-profile")) {
                templateToRender = "edit_profile.html";
            } else {
                templateToRender = "/login";
            }


            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process(templateToRender, context, response.getWriter());

        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
        WebContext context = new WebContext(request, response, request.getServletContext());
        User user = userDataManager.findIdBy(userEmail);
        context.setVariable("userData", user);

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
            userDataManager.update(user);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            System.out.println("New user could not be created");
        }

        response.sendRedirect("/profile");
    }

}
