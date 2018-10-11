package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.util.PasswordHashing;
import com.codecool.lodgingsmanager.util.SessionHandler;
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

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        SessionHandler sessionHandler = SessionHandler.getInstance(request.getSession());
        User user = userDataManager.find(Integer.parseInt(sessionHandler.getSessionValue("user_id")));

        context.setVariable("userData", user);

        engine.process("user_profile.html", context, response.getWriter());
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//
//        String email = request.getParameter(UserDataField.EMAIL_ADDRESS.getInputString());
//        String password = request.getParameter(UserDataField.PASSWORD.getInputString());
//
//        User mightBeUser = PasswordHashing.checkPassword(password, email);
//        if (mightBeUser == null) {
//            throw new NullPointerException("User not found in database");
//        }
//
//        loginUser(request.getSession(), mightBeUser);
//        response.sendRedirect("/index");
//    }
//
//    private void loginUser(HttpSession httpSession, User user) {
//        SessionHandler sessionHandler = SessionHandler.getInstance(httpSession);
//        sessionHandler.addAttributeToSession(UserDataField.USER_ID.getInputString(), user.getId());
//    }


}
