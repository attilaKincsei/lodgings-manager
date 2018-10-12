package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.Landlord;
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

@WebServlet(urlPatterns = {"/profile"})
public class UserProfileController extends HttpServlet {

    private UserDaoDb userDataManager = new UserDaoDb();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            WebContext context = new WebContext(request, response, request.getServletContext());
            User user = userDataManager.findIdBy(userEmail);
            if(user.getUserType().equals("LANDLORD")) { // TODO enum?
                Landlord landlord = (Landlord) user;
                context.setVariable("lodgings", landlord.getLodgings());
            }

            context.setVariable("userData", user);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process("user_profile.html", context, response.getWriter());

        }
    }
}
