package com.codecool.lodgingsmanager.config;


import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.LodgingsDaoDb;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.*;
import com.codecool.lodgingsmanager.service.*;
import com.codecool.lodgingsmanager.service.ajax.EmailCheckerService;
import com.codecool.lodgingsmanager.service.delete.DeleteService;
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
    private static final UserDao<User> userDataManager = new UserDaoDb<>(User.class);
    private static final LodgingsDao<Lodgings> lodgingsDataManager = new LodgingsDaoDb();

    public static final EmailCheckerService EMAIL_CHECKER_HANDLER = new EmailCheckerService(userDataManager);
    public static final BaseService<User> USER_HANDLER = new UserService(userDataManager, lodgingsDataManager);
    public static final BaseService<Lodgings> LODGINGS_HANDLER = new LodgingsService(lodgingsDataManager);
    public static final BaseService<Comment> COMMENT_HANDLER = new CommentService();
    public static final BaseService<ToDo> TO_DO_HANDLER = new ToDoService();
    public static final DeleteService DELETE_HANDLER = new DeleteService();

    @Override
    public void contextInitialized(ServletContextEvent sce) {


        User testUser = UserFactory.createUserInstanceBy(
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

        userDataManager.add(testUser);

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
                (Landlord) testUser
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
                (Landlord) testUser
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



        testingMethod(); // todo: delete later


    }

    private void testingMethod() {

        System.out.println("\n\n\n------------------------------------------------");
        userDataManager.getAllEmailAddresses().forEach(System.out::println);
//        userDataManager.getAll().forEach(System.out::println);

//        System.out.println(lodgingsDataManager.getAllLodgingsBy(2L));
//        System.out.println(lodgingsDataManager.find(2L));
        System.out.println(userDataManager.find(2L));

    }


}