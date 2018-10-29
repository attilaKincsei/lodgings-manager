package com.codecool.lodgingsmanager.controller.ajax;

import com.codecool.lodgingsmanager.config.Initializer;
import com.codecool.lodgingsmanager.service.ajax.EmailCheckerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/check-email")
public class EmailCheckerController extends HttpServlet {

    private final EmailCheckerService emailCheckerHandler = Initializer.EMAIL_CHECKER_HANDLER;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String emailentered = "";
        if(br != null){
            emailentered = br.readLine();
        }

        String registeredEmail = emailCheckerHandler.checkIfEmailRegistered(emailentered);

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(registeredEmail);


    }

}