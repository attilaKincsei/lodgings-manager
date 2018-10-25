package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.service.*;
import com.codecool.lodgingsmanager.util.DeletableType;
import com.codecool.lodgingsmanager.util.UserDataField;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet(urlPatterns = {"/delete"})
public class DeleteController extends HttpServlet {

    private BaseService userHandler = new UserService();
    private BaseService lodgingsHandler = new LodgingsService();
    private BaseService commentHandler = new CommentService();
    private BaseService toDoHandler = new ToDoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // todo: login

        HttpSession session = request.getSession();

        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()) == null) {
            response.sendRedirect("/login");
        } else {
            Enumeration<String> parameterNames = request.getParameterNames();


            List<String> deletableTypeList = createDeletableTypeList();

            while (parameterNames.hasMoreElements()) {
                String parameterName = parameterNames.nextElement();
                if (deletableTypeList.contains(parameterName)) {
                    String stringId = request.getParameter(parameterName);
                    long id = Long.parseLong(stringId);
                    DeletableType deletableType = DeletableType.valueOf(parameterName.toUpperCase());
                    switch (deletableType) {
                        case COMMENT:
                            commentHandler.delete(id);
                            break;
                        case USER:
                            userHandler.delete(id);
                            response.sendRedirect("/login");
                            break;
                        case LODGINGS:
                            lodgingsHandler.delete(id);
                            response.sendRedirect("/lodgings");
                            break;
                        case TODO:
                            toDoHandler.delete(id);
                            break;
                    }
                }
            }

        }
    }

    private List<String> createDeletableTypeList() {
        List<String> deletableTypeList = new ArrayList<>();
        for (DeletableType model : DeletableType.values()) {
            deletableTypeList.add(model.getModelTypeString());
        }
        return deletableTypeList;
    }


}
