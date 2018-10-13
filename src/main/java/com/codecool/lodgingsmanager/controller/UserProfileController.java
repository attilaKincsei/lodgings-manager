package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.LodgingsDaoDb;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.Landlord;
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

@WebServlet(urlPatterns = {"/profile"})
public class UserProfileController extends HttpServlet {

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
            System.out.println("\n\n----------------------------------------------------------");
            System.out.println(userEmail);
            User user = userDataManager.findIdBy(userEmail);
            System.out.println(user.getId());
            List<Lodgings> lodgingsList = lodgingsDataManager.getAllLodgingsBy(user.getId());
            context.setVariable("lodgings", lodgingsList);
            context.setVariable("userData", user);

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process("user_profile.html", context, response.getWriter());

        }
    }
}
