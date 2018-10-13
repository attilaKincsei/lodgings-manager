package com.codecool.lodgingsmanager.config;


import com.codecool.lodgingsmanager.dao.LodgingsDao;
import com.codecool.lodgingsmanager.dao.UserDao;
import com.codecool.lodgingsmanager.dao.implementation.database.LodgingsDaoDb;
import com.codecool.lodgingsmanager.dao.implementation.database.UserDaoDb;
import com.codecool.lodgingsmanager.model.Landlord;
import com.codecool.lodgingsmanager.model.Lodgings;
import com.codecool.lodgingsmanager.model.User;
import com.codecool.lodgingsmanager.util.PasswordHashing;
import com.codecool.lodgingsmanager.util.Type;
import com.codecool.lodgingsmanager.util.UserFactory;
import com.codecool.lodgingsmanager.util.UserType;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {

    private UserDao userDataManager = new UserDaoDb();
    private LodgingsDao lodgingsDataManager = new LodgingsDaoDb();


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext().getContext("/logout");

        System.out.println("\n\n\n\n\n----------------------------------------------------------------------------------------------------");
        System.out.println(context);


        // TODO add test data
        // have just one EMF, EM

        User testUser = UserFactory.createUserInstanceBy(
                UserType.LANDLORD,
                "Attila",
                "Kincsei",
                "akincsei@gmail.com",
                "23123123123",
                "Country",
                "City",
                "H-34234",
                "1. Street",
                PasswordHashing.hashPassword("11111111")
        );

        userDataManager.add(testUser);

        Lodgings newLodging = new Lodgings(
                "My little apartment",
                Type.APARTMENT,
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
                Type.APARTMENT,
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


    }


}