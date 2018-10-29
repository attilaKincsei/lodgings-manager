package com.codecool.lodgingsmanager.controller.delete;

import com.codecool.lodgingsmanager.config.Initializer;
import com.codecool.lodgingsmanager.service.delete.DeleteService;
import com.codecool.lodgingsmanager.util.UserDataField;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

import static com.codecool.lodgingsmanager.config.Initializer.GUEST_EMAIL;

@WebServlet(urlPatterns = {"/delete"})
public class DeleteController extends HttpServlet {

    private final DeleteService deletHandler = Initializer.DELETE_HANDLER;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {
            Enumeration<String> parameterNames = request.getParameterNames();
            deletHandler.handleDeleting(request, response, parameterNames);

        }
    }



}