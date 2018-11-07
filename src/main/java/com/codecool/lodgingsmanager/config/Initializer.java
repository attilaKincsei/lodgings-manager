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
import com.codecool.lodgingsmanager.service.*;
import com.codecool.lodgingsmanager.service.ajax.EmailCheckerService;
import com.codecool.lodgingsmanager.util.LodgingsType;
import com.codecool.lodgingsmanager.util.PasswordHashing;
import com.codecool.lodgingsmanager.util.UserFactory;
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
        UserDao userDaoDb = UserDaoDb.getINSTANCE();
        LodgingsDao lodgingsDaoDb = LodgingsDaoDb.getINSTANCE();
        ToDoDao toDoDaoDb = ToDoDaoDb.getINSTANCE();

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
                .addMapping("/todo/add");



        // initialize model objects for testing todo: dele later
        User testLandlord = UserFactory.createUserInstanceBy(
                UserType.LANDLORD,
                "Attila",
                "Kincsei",
                "akincsei@gmail.com",
                "+23123123123",
                "Country",
                "City",
                "H-34234",
                "1. Street",
                PasswordHashing.hashPassword("Qq111111")
        );

        userDaoDb.add(testLandlord);

        User testPropertyManager = UserFactory.createUserInstanceBy(
                UserType.PROPERTY_MANAGER,
                "Hugo",
                "Menedzser",
                "menedzser@gmail.com",
                "+10000000000",
                "ManCountry",
                "ManCity",
                "M-1001",
                "1. Manager Street",
                PasswordHashing.hashPassword("Qq111111")
        );

        userDaoDb.add(testPropertyManager);


        Lodgings newLodging = new Lodgings(
                "My little apartment",
                LodgingsType.ROOM,
                "Molvania",
                "Molvania City",
                "MO-2342",
                "111. Very Nice Street",
                100L,
                10L,
                20L,
                15L,
                4L,
                testLandlord,
                testPropertyManager
        );

        lodgingsDaoDb.add(newLodging);


        Lodgings newLodging2 = new Lodgings(
                "wwwwwwwww apartment",
                LodgingsType.APARTMENT,
                "asdfania",
                "City",
                "MO-2342",
                "111. dfdfce Street",
                10033L,
                103L,
                203L,
                153L,
                433L,
                testLandlord,
                testPropertyManager
        );

        lodgingsDaoDb.add(newLodging2);


        User guestUser = UserFactory.createUserInstanceBy(
                UserType.GUEST,
                "Guest",
                "User",
                GUEST_EMAIL,
                "+2211111111",
                "Country",
                "City",
                "W-1111",
                "1. Street",
                PasswordHashing.hashPassword("11111111")
        );

        userDaoDb.add(guestUser);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    private void testingDI() {
        LandlordDao landlordDataManager = LandlordDaoDb.getINSTANCE();
        System.out.println("\n\n\n------------------------------------------------");
        for (User user : landlordDataManager.getAll()) {
            System.out.println(user);
        }
    }

    private void testingCriteriaQueries() {

        System.out.println("\n\n\n------------------------------------------------");
//        userDataManager.getAllEmailAddresses().forEach(System.out::println);
//        userDataManager.getAll().forEach(System.out::println);

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