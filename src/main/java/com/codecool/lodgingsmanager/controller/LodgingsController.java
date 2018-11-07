package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.service.LodgingsService;
import com.codecool.lodgingsmanager.util.FieldType;
import com.codecool.lodgingsmanager.util.LodgingDataField;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.codecool.lodgingsmanager.config.Initializer.GUEST_EMAIL;

// todo: edit lodgings is not implemented
public class LodgingsController extends HttpServlet {

    private final String servletName;
    private final BaseService<Lodgings> lodgingsService;

    public LodgingsController(String servletName, BaseService<Lodgings> lodgingsService) {
        this.servletName = servletName;
        this.lodgingsService = lodgingsService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        // Handling log-in
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString());
            String lodgingsIdString = request.getParameter("lodgingsId");

            User user = lodgingsService.handleGetUserBy(userEmail);
            List<Lodgings> lodgingsList = ((LodgingsService) lodgingsService).handleGetLodgingsBy(lodgingsIdString, user.getId());
            List<String> lodgingsTypeList = lodgingsService.getEnumAsStringList();

            WebContext context = new WebContext(request, response, request.getServletContext());
            context.setVariable("userData", user);
            context.setVariable("lodgings", lodgingsList);
            context.setVariable("lodgingsTypes", lodgingsTypeList);


            String requestPath = request.getServletPath();
            String lodgingsId = request.getParameter("lodgingsId");
            String templateToRender = lodgingsService.handleCRUDBy(requestPath, lodgingsId); // todo: thing about a better name

            if (templateToRender == null) {
                response.sendRedirect("/lodgings");
            } else {
                TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
                engine.process(templateToRender, context, response.getWriter());
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {

            String lodgingName = request.getParameter(LodgingDataField.NAME.getInputString());
            String lodgingType = request.getParameter(LodgingDataField.TYPE.getInputString());
            String country = request.getParameter(FieldType.COUNTRY.getInputString());
            String city = request.getParameter(FieldType.CITY.getInputString());
            String zipCode = request.getParameter(FieldType.ZIP_CODE.getInputString());
            String address = request.getParameter(FieldType.ADDRESS.getInputString());
            String dailyPrice = request.getParameter(LodgingDataField.DAILY_PRICE.getInputString());
            String electricityBill = request.getParameter(LodgingDataField.ELECTRICITY_BILL.getInputString());
            String gasBill = request.getParameter(LodgingDataField.GAS_BILL.getInputString());
            String telecommunicationBill = request.getParameter(LodgingDataField.TELECOMMUNICATION_BILL.getInputString());
            String cleaningCost = request.getParameter(LodgingDataField.CLEANING_COST.getInputString());

            String userEmail = (String) session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString());


            String requestPath = request.getServletPath();
            String lodgingsIdString = request.getParameter("lodgingsId");
            System.out.println("\n--------------------------------------\n" + lodgingsIdString);


            lodgingsService.handleAddOrEditWithPostRequest(lodgingName, lodgingType, country, city, zipCode, address, dailyPrice, electricityBill, gasBill, telecommunicationBill, cleaningCost, userEmail, requestPath, lodgingsIdString);


            response.sendRedirect("/index");
        }
    }


}