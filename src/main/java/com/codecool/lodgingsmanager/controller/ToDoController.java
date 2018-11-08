package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.PropertyManager;
import com.codecool.lodgingsmanager.model.ToDo;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.service.LodgingsService;
import com.codecool.lodgingsmanager.service.ToDoService;
import com.codecool.lodgingsmanager.util.FieldType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codecool.lodgingsmanager.config.Initializer.GUEST_EMAIL;

public class ToDoController extends HttpServlet {

    private final String servletName;
    private final BaseService<User> userService;
    private final LodgingsService lodgingsService;
    private final ToDoService toDoService;

    public ToDoController(String servletName, BaseService<User> userService, LodgingsService lodgingsService, ToDoService toDoService) {
        this.servletName = servletName;
        this.userService = userService;
        this.lodgingsService = lodgingsService;
        this.toDoService = toDoService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Handling log-in
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {

            String userEmail = (String) session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString());
            User user = userService.handleGetUserBy(userEmail);

            WebContext context = new WebContext(request, response, request.getServletContext());
            context.setVariable("userData", user);
            //context.setVariable("lodginsId", request.getParameter("lodgingsId"));

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            engine.process("add_todo.html", context, response.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {

            String name = request.getParameter("name");
            String description = request.getParameter("description");
            long price = Long.parseLong(request.getParameter("price"));

            SimpleDateFormat formatter = new SimpleDateFormat("mm-dd-yyyy");
            Date date = null;

            try {
                date = formatter.parse(request.getParameter("price"));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            String userEmail = (String) session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString());
            User user = userService.handleGetUserBy(userEmail);
            String LodgingsId = request.getParameter("lodgingsId");
            long userId = user.getId();
            Lodgings lodgings = lodgingsService.handleGetLodgingsBy(LodgingsId,userId).get(0);
            PropertyManager propertyManager = (PropertyManager) lodgings.getPropertyManager();


            ToDo toDo = new ToDo(lodgings, propertyManager, date, description, price);

            toDoService.handleAddNew(toDo);

            response.sendRedirect("/index");


        }

    }

}