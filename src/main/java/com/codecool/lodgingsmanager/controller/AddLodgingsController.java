package com.codecool.lodgingsmanager.controller;

import com.codecool.lodgingsmanager.config.TemplateEngineUtil;
import com.codecool.lodgingsmanager.dao.implementation.database.LodgingsDaoDb;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.model.enums.Type;
import com.codecool.lodgingsmanager.util.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/add-lodgings"})
public class AddLodgingsController extends HttpServlet {

    private LodgingsDaoDb lodgingDataManager = new LodgingsDaoDb();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        engine.process("add_lodgings.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String lodgingName = request.getParameter(LodgingDataField.LODGING_NAME.getInputString());
        String lodgingType = request.getParameter(LodgingDataField.LODGING_TYPE.getInputString());
        String dailyPrice = request.getParameter(LodgingDataField.DAILY_PRICE.getInputString());
        String electricityBill = request.getParameter(LodgingDataField.ELECTRICITY_BILL.getInputString());
        String gasBill = request.getParameter(LodgingDataField.GAS_BILL.getInputString());
        String telecommunicationBill = request.getParameter(LodgingDataField.TELECOMMUNICATION_BILL.getInputString());
        String cleaningCost = request.getParameter(LodgingDataField.CLEANING_COST.getInputString());
        String country = request.getParameter(LodgingDataField.COUNTRY.getInputString());
        String city = request.getParameter(LodgingDataField.CITY.getInputString());
        String zipCode = request.getParameter(LodgingDataField.ZIP_CODE.getInputString());
        String address = request.getParameter(LodgingDataField.ADDRESS.getInputString());


        Lodgings newLodging = new Lodgings(
                lodgingName,
                Type.valueOf(lodgingType.toUpperCase()),
                country,
                city,
                zipCode,
                address,
                Long.parseLong(dailyPrice),
                Long.parseLong(electricityBill),
                Long.parseLong(gasBill),
                Long.parseLong(telecommunicationBill),
                Long.parseLong(cleaningCost)
        );

        try {
            lodgingDataManager.add(newLodging);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            System.out.println("New lodging could not be created");
        }

        response.sendRedirect("/index");
    }
}
