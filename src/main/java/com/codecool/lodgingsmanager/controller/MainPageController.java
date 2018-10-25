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
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import static com.codecool.lodgingsmanager.config.Initializer.GUEST_EMAIL;


@WebServlet(urlPatterns = {"/", "/index"})
public class MainPageController extends HttpServlet {

    private LodgingsDao lodgingsDataManager = new LodgingsDaoDb();
    private UserDao userDataManager = new UserDaoDb();



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Handling log-in
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            User user = userDataManager.findIdBy(userEmail);

            WebContext context = new WebContext(request, response, request.getServletContext());
            context.setVariable("userData", user);

            List<Lodgings> lodgingsList = lodgingsDataManager.getAllLodgingsBy(user.getId());
            context.setVariable("lodgings", lodgingsList);


            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process("index.html", context, response.getWriter());
        }
    }

}
