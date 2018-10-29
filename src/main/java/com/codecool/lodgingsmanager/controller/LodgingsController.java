package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.Initializer;
import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.Lodgings;
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
import java.util.List;

import static com.codecool.lodgingsmanager.config.Initializer.GUEST_EMAIL;

@WebServlet(urlPatterns = {"/lodgings", "/edit-lodgings"}) // todo: edit lodgings is not implemented
public class LodgingsController extends HttpServlet {

    private final BaseService<User> userHandler = Initializer.USER_HANDLER;
    private final BaseService<Lodgings> lodgingsHandler = Initializer.LODGINGS_HANDLER;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Handling log-in
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            String lodgingsIdString = request.getParameter("lodgingsId");

            User user = userHandler.handleGetSingleObjectBy(userEmail);
            List<Lodgings> lodgingsList = lodgingsHandler.handleGetListBy(lodgingsIdString, user.getId());

            WebContext context = new WebContext(request, response, request.getServletContext());
            context.setVariable("userData", user);
            context.setVariable("lodgings", lodgingsList);
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process("lodgings.html", context, response.getWriter());

        }
    }

}