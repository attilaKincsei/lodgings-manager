package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.util.UserDataField;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import static com.codecool.lodgingsmanager.config.Initializer.GUEST_EMAIL;

public class MainPageController extends HttpServlet {

    private final String servletName;
    private final BaseService<User> userService;
    private final BaseService<Lodgings> lodgingsService;

    public MainPageController(String servletName, BaseService<User> userService, BaseService<Lodgings> lodgingsService) {
        this.servletName = servletName;
        this.userService = userService;
        this.lodgingsService = lodgingsService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Handling log-in
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            User user = userService.handleGetUserBy(userEmail);

            WebContext context = new WebContext(request, response, request.getServletContext());
            context.setVariable("userData", user);

            List<Lodgings> lodgingsList = lodgingsService.handleGetAllBy(user.getId());
            context.setVariable("lodgings", lodgingsList);


            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process("index.html", context, response.getWriter());
        }
    }


}