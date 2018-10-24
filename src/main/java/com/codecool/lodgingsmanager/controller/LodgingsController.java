package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.service.LodgingsService;
import com.codecool.lodgingsmanager.service.UserService;
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

@WebServlet(urlPatterns = {"/lodgings", "/edit-lodgings"})
public class LodgingsController extends HttpServlet {

    private BaseService<User> userHandler = new UserService();
    private BaseService<Lodgings> lodgingsHandler = new LodgingsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Handling log-in
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            String lodgingsIdString = request.getParameter("lodgingsId");

            User user = userHandler.handleBy(userEmail);
            List<Lodgings> lodgingsList = lodgingsHandler.handleBy(lodgingsIdString, user.getId());

            WebContext context = new WebContext(request, response, request.getServletContext());
            context.setVariable("userData", user);
            context.setVariable("lodgings", lodgingsList);
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process("lodgings.html", context, response.getWriter());

        }
    }

}
