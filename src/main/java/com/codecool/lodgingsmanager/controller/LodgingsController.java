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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/lodgings", "/edit-lodgings"})
public class LodgingsController extends HttpServlet {

    private LodgingsDao lodgingsDataManager = new LodgingsDaoDb();
    private UserDao userDataManager = new UserDaoDb();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        WebContext context = new WebContext(request, response, request.getServletContext());

        // Handling log-in
        HttpSession session = request.getSession(false);

        if (session == null) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            User user = userDataManager.findIdBy(userEmail);
            context.setVariable("userData", user);

            List<Lodgings> allLodgingsList = lodgingsDataManager.getAllLodgingsBy((long) user.getId());
            List<Long> lodgingsIdList = allLodgingsList.stream().mapToLong(Lodgings::getId).map(l -> (Long) l).boxed().collect(Collectors.toList());


            String lodgingsIdString = request.getParameter("lodgingsId");
            List<Lodgings> lodgingsList = new ArrayList<>();
            if (lodgingsIdString != null && lodgingsIdList.contains(Long.parseLong(lodgingsIdString))) {
                Lodgings lodgings = lodgingsDataManager.find(Long.parseLong(lodgingsIdString));
                lodgingsList.add(lodgings);
            } else {
                lodgingsList = allLodgingsList;
            }

            context.setVariable("lodgings", lodgingsList);
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process("lodgings.html", context, response.getWriter());

        }
    }



}
