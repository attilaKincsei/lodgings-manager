package com.codecool.lodgingsmanager.controller.ajaxcontroller;

import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.persistence.NoResultException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/check-email")
public class RegistrationEmailChecker extends HttpServlet {

//    private static final long serialVersionUID = 1L;
    private final UserDao<User> userDataManager = new UserDaoDb<>(User.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String emailentered = "";
        if(br != null){
            emailentered = br.readLine();
        }

        String registeredEmail = "";
        try {
            User mightBeRegisteredUser = userDataManager.findIdBy(emailentered);
            registeredEmail = mightBeRegisteredUser.getEmail();
        } catch (NoResultException nre) {
            // todo: change this to logger?
            System.out.println(emailentered + " is not yet registered in database");
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(registeredEmail);


    }
}