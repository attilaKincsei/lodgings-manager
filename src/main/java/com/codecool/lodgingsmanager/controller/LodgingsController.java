package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.model.builder.AddressBuilder;
import com.codecool.lodgingsmanager.service.BaseService;
import com.codecool.lodgingsmanager.service.LodgingsService;
import com.codecool.lodgingsmanager.util.FieldType;
import com.codecool.lodgingsmanager.util.LodgingDataField;
import com.codecool.lodgingsmanager.util.LodgingsType;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

import static com.codecool.lodgingsmanager.config.Initializer.GUEST_EMAIL;

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
            String templateToRender = lodgingsService.handleCrudGetBy(requestPath, lodgingsId); // todo: think about a better name

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

            String country = request.getParameter(FieldType.COUNTRY.getInputString());
            String city = request.getParameter(FieldType.CITY.getInputString());
            String zipCode = request.getParameter(FieldType.ZIP_CODE.getInputString());
            String address = request.getParameter(FieldType.ADDRESS.getInputString());

            AddressBuilder fullAddress = new AddressBuilder(country, city, zipCode, address);

            String lodgingName = request.getParameter(LodgingDataField.NAME.getInputString());
            String lodgingType = request.getParameter(LodgingDataField.TYPE.getInputString());

            String dailyPrice = request.getParameter(LodgingDataField.DAILY_PRICE.getInputString());
            String electricityBill = request.getParameter(LodgingDataField.ELECTRICITY_BILL.getInputString());
            String gasBill = request.getParameter(LodgingDataField.GAS_BILL.getInputString());
            String telecommunicationBill = request.getParameter(LodgingDataField.TELECOMMUNICATION_BILL.getInputString());
            String cleaningCost = request.getParameter(LodgingDataField.CLEANING_COST.getInputString());
            String propertyManagerEmail = request.getParameter(FieldType.EMAIL_ADDRESS.getInputString());

            String landlordEmail = (String) session.getAttribute(FieldType.EMAIL_ADDRESS.getInputString());


            String requestPath = request.getServletPath();
            String lodgingsIdString = request.getParameter("lodgingsId");


            // todo being refactored

            User user = lodgingsService.handleGetUserBy(landlordEmail);

            if (requestPath.equals("/lodgings/add")) {

                Lodgings newLodgings = new Lodgings(
                        lodgingName,
                        LodgingsType.valueOf(lodgingType.toUpperCase()),
                        Long.parseLong(dailyPrice),
                        Long.parseLong(electricityBill),
                        Long.parseLong(gasBill),
                        Long.parseLong(telecommunicationBill),
                        Long.parseLong(cleaningCost),
                        user,
                        fullAddress
                );

                lodgingsService.handleAddPost(newLodgings);

            } else if (requestPath.equals("/lodgings/edit")) {

                Lodgings lodgings = ((LodgingsService) lodgingsService).handleGetLodgingsBy(lodgingsIdString, user.getId()).get(0);

                lodgings.setName(lodgingName);
                lodgings.setLodgingsType(LodgingsType.valueOf(lodgingType.toUpperCase()));
                lodgings.setCountry(country);
                lodgings.setCity(city);
                lodgings.setZipCode(zipCode);
                lodgings.setAddress(address);
                lodgings.setPricePerDay(Long.parseLong(dailyPrice));
                lodgings.setElectricityBill(Long.parseLong(electricityBill));
                lodgings.setGasBill(Long.parseLong(gasBill));
                lodgings.setTelecommunicationBill(Long.parseLong(telecommunicationBill));
                lodgings.setCleaningCost(Long.parseLong(cleaningCost));

                ((LodgingsService) lodgingsService).handleEditPost(propertyManagerEmail, lodgings);
            }

            response.sendRedirect("/lodgings");
        }
    }

}