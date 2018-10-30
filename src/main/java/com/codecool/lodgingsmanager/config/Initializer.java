package com.codecool.lodgingsmanager.config;


import com.codecool.lodgingsmanager.dao.LandlordDao;
import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.LandlordDaoDb;
import com.codecool.lodgingsmanager.dao.implementation.database.LodgingsDaoDb;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.*;
import com.codecool.lodgingsmanager.service.*;
import com.codecool.lodgingsmanager.service.ajax.EmailCheckerService;
import com.codecool.lodgingsmanager.service.delete.DeletionService;
import com.codecool.lodgingsmanager.util.LodgingsType;
import com.codecool.lodgingsmanager.util.PasswordHashing;
import com.codecool.lodgingsmanager.util.UserFactory;
import com.codecool.lodgingsmanager.util.UserType;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    public static final String GUEST_EMAIL = "guest@fakedomain.com";

    public static final BaseService<User> USER_SERVICE = new UserService();
    public static final BaseService<Lodgings> LODGINGS_SERVICE = new LodgingsService(USER_SERVICE);
    public static final EmailCheckerService EMAIL_CHECKER_SERVICE = new EmailCheckerService(USER_SERVICE);
    public static final BaseService<Comment> COMMENT_SERVICE = new CommentService();
    public static final BaseService<ToDo> TO_DO_SERVICE = new ToDoService();
    public static final DeletionService DELETION_SERVICE = new DeletionService(USER_SERVICE, LODGINGS_SERVICE, COMMENT_SERVICE, TO_DO_SERVICE);

    static {
        USER_SERVICE.injectDependency(LODGINGS_SERVICE);
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {

        UserDao userDataManager = UserDaoDb.getINSTANCE();
        LodgingsDao lodgingsDataManager = LodgingsDaoDb.getINSTANCE();


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

        userDataManager.add(testLandlord);

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

        userDataManager.add(testPropertyManager);


        Lodgings newLodging = new Lodgings(
                "My little apartment",
                LodgingsType.APARTMENT,
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

        lodgingsDataManager.add(newLodging);


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

        lodgingsDataManager.add(newLodging2);


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

        userDataManager.add(guestUser);



//        testingCriteriaQueries(); // todo: delete later
        testingDI();


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
        for (User user : USER_SERVICE.handleGetAllBy(1L)) {
            System.out.println(user);
        }


    }


}