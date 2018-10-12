package com.codecool.lodgingsmanager.config;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Initializer implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // TODO add test data
        // have just one EMF, EM

    }


}