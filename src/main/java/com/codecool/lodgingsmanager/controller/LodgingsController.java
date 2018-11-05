package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.service.LodgingsService;
import com.codecool.lodgingsmanager.util.LodgingDataField;
import com.codecool.lodgingsmanager.util.LodgingsType;
import com.codecool.lodgingsmanager.util.UserDataField;
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

        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {
            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            String lodgingsIdString = request.getParameter("lodgingsId");

            User user = lodgingsService.handleGetUserBy(userEmail);
            List<Lodgings> lodgingsList = ((LodgingsService) lodgingsService).handleGetLodgingsBy(lodgingsIdString, user.getId());

            WebContext context = new WebContext(request, response, request.getServletContext());
            context.setVariable("userData", user);
            context.setVariable("lodgings", lodgingsList);

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
        if (session == null || session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString()).equals(GUEST_EMAIL)) {
            response.sendRedirect("/login");
        } else {

            String lodgingName = request.getParameter(LodgingDataField.NAME.getInputString());
            String lodgingType = request.getParameter(LodgingDataField.TYPE.getInputString());
            String country = request.getParameter(LodgingDataField.COUNTRY.getInputString());
            String city = request.getParameter(LodgingDataField.CITY.getInputString());
            String zipCode = request.getParameter(LodgingDataField.ZIP_CODE.getInputString());
            String address = request.getParameter(LodgingDataField.ADDRESS.getInputString());
            String dailyPrice = request.getParameter(LodgingDataField.DAILY_PRICE.getInputString());
            String electricityBill = request.getParameter(LodgingDataField.ELECTRICITY_BILL.getInputString());
            String gasBill = request.getParameter(LodgingDataField.GAS_BILL.getInputString());
            String telecommunicationBill = request.getParameter(LodgingDataField.TELECOMMUNICATION_BILL.getInputString());
            String cleaningCost = request.getParameter(LodgingDataField.CLEANING_COST.getInputString());

            String userEmail = (String) session.getAttribute(UserDataField.EMAIL_ADDRESS.getInputString());
            User user = lodgingsService.handleGetUserBy(userEmail);


            Lodgings newLodgings = new Lodgings(
                    lodgingName,
                    LodgingsType.valueOf(lodgingType.toUpperCase()),
                    country,
                    city,
                    zipCode,
                    address,
                    Long.parseLong(dailyPrice),
                    Long.parseLong(electricityBill),
                    Long.parseLong(gasBill),
                    Long.parseLong(telecommunicationBill),
                    Long.parseLong(cleaningCost),
                    user
            );

            lodgingsService.handleAddNew(newLodgings);


            response.sendRedirect("/index");
        }
    }

}