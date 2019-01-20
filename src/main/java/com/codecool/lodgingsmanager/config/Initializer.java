package com.codecool.lodgingsmanager.config;


import com.codecool.lodgingsmanager.controller.*;
import com.codecool.lodgingsmanager.controller.ajax.EmailCheckerController;
import com.codecool.lodgingsmanager.dao.LandlordDao;
import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.dao.ToDoDao;
import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.LandlordDaoDb;
import com.codecool.lodgingsmanager.dao.implementation.database.LodgingsDaoDb;
import com.codecool.lodgingsmanager.dao.implementation.database.ToDoDaoDb;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.*;
import com.codecool.lodgingsmanager.model.builder.AddressBuilder;
import com.codecool.lodgingsmanager.service.*;
import com.codecool.lodgingsmanager.service.ajax.EmailCheckerService;
import com.codecool.lodgingsmanager.util.LodgingsType;
import com.codecool.lodgingsmanager.util.PasswordHashing;
import com.codecool.lodgingsmanager.util.UserType;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    public static final String GUEST_EMAIL = "guest@fakedomain.com";

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // Initialize dao and service objects
        UserDao userDaoDb = new UserDaoDb();
        LodgingsDao lodgingsDaoDb = new LodgingsDaoDb();
        ToDoDao toDoDaoDb = new ToDoDaoDb();

        BaseService<User> userService = new UserService(userDaoDb);
        LodgingsService lodgingsService = new LodgingsService(lodgingsDaoDb, userService);
        EmailCheckerService emailCheckerService = new EmailCheckerService(userService);
        ToDoService toDoService = new ToDoService(toDoDaoDb, userService);


        // Initialize servlets with dependency injection
        ServletContext context = sce.getServletContext();

        context
                .addServlet("MainPageController", new MainPageController("MainPageController", userService, lodgingsService))
                .addMapping("/", "/index");

        context
                .addServlet("UserController", new UserController("UserController", userService))
                .addMapping("/user", "/user/edit", "/user/delete");

        context
                .addServlet("LodgingsController", new LodgingsController("LodgingsController", lodgingsService))
                .addMapping("/lodgings", "/lodgings/add", "/lodgings/edit", "/lodgings/delete");


        context
                .addServlet("EmailChecker", new EmailCheckerController("EmailCheckerController", emailCheckerService))
                .addMapping("/check-email");

        context
                .addServlet("LoginController", new LoginController("LoginController", userService))
                .addMapping("/login", "/login/incorrect", "/logout");

        context
                .addServlet("RegistrationController", new RegistrationController("RegistrationController", userService))
                .addMapping("/registration");

        context
                .addServlet("ToDoController", new ToDoController("ToDoController", userService, lodgingsService, toDoService))
                .addMapping("/todo/add", "/todo");



        // initialize model objects for testing todo: dele later

        AddressBuilder fullAddressLL = new AddressBuilder(
                "Country",
                "City",
                "H-34234",
                "1. Street"
                );

        User testLandlord = new User(
                "Attila",
                "Kincsei",
                "akincsei@gmail.com",
                "+23123123123",
                PasswordHashing.hashPassword("Qq111111"),
                UserType.LANDLORD,
                fullAddressLL
        );

        userDaoDb.add(testLandlord);

        AddressBuilder fullAddressPM = new AddressBuilder(
                "ManCountry",
                "ManCity",
                "M-1001",
                "1. Manager Street"
        );


        User testPropertyManager = new User(
                "Hugo",
                "Menedzser",
                "menedzser@gmail.com",
                "+10000000000",
                PasswordHashing.hashPassword("Qq111111"),
                UserType.PROPERTY_MANAGER,
                fullAddressPM
        );

        userDaoDb.add(testPropertyManager);

        AddressBuilder fullAddress0 = new AddressBuilder("Molvania", "Molvania City", "MO-2342", "111. Very Nice Street");

        Lodgings newLodging = new Lodgings(
                "My little apartment",
                LodgingsType.ROOM,
                100L,
                10L,
                20L,
                15L,
                4L,
                testLandlord,
                testPropertyManager,
                fullAddress0
        );

        lodgingsDaoDb.add(newLodging);

        AddressBuilder fullAddress = new AddressBuilder("Vanuatu", "Big City", "VAU-2342", "111. dfdfce Street");

        Lodgings newLodging2 = new Lodgings(
                "My very big apartment",
                LodgingsType.APARTMENT,
                10033L,
                103L,
                203L,
                153L,
                433L,
                testLandlord,
                fullAddress
        );

        lodgingsDaoDb.add(newLodging2);

        AddressBuilder fullAddressGuest = new AddressBuilder(
                "Country",
                "City",
                "W-1111",
                "1. Street"
        );

        User guestUser = new User(
                "Guest",
                "User",
                GUEST_EMAIL,
                "+2211111111",
                PasswordHashing.hashPassword("11111111"),
                UserType.GUEST,
                fullAddressGuest
        );

        userDaoDb.add(guestUser);


//        ToDo testToDo = new ToDo("pay bills", newLodging, new Date(118, 10, 10), "Go to post office and pay cheques", 25000L);
//        toDoDaoDb.add(testToDo);
//        // todo: for testing, delete later
//        testingCriteriaQueries();

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void testingDI() {
        LandlordDao landlordDataManager = new LandlordDaoDb();
        System.out.println("\n\n\n------------------------------------------------");
        for (User user : landlordDataManager.getAll()) {
            System.out.println(user);
        }
    }

    private void testingCriteriaQueries() {

        UserDao userDaoDb = new UserDaoDb();
        LodgingsDao lodgingsDaoDb = new LodgingsDaoDb();

        System.out.println("\n\n\n------------------------------------------------");
//        userDataManager.getAllEmailAddresses().forEach(System.out::println);
        userDaoDb.getAll().forEach(System.out::println);

//        System.out.println(lodgingsDataManager.getAllLodgingsBy(2L));
//        System.out.println(lodgingsDataManager.find(2L));
//        System.out.println(userDataManager.find(2L));
//        UserDao USER_DAO_DB = UserDaoDb.getINSTANCE();
//        BaseService<User> USER_SERVICE = new UserService(USER_DAO_DB);
//        for (User user : USER_SERVICE.handleGetAllBy(1L)) {
//            System.out.println(user);
//        }


    }
}