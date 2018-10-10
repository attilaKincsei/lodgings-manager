package com.codecool.lodgingsmanager.util;

import javax.servlet.http.HttpSession;

public class SessionHandler {
    private static final SessionHandler INSTANCE = new SessionHandler();
    private static HttpSession session;

    private SessionHandler() {}

    public static SessionHandler getInstance(HttpSession session) {
        SessionHandler.session = session;
        return INSTANCE;
    }

    public void addAttributeToSession(String key, String value) {
        session.setAttribute(key, value);
    }

    public void addAttributeToSession(String key, long value) {
        session.setAttribute(key, value);
    }

    public String getSessionValue(String key) {
        return session.getAttribute(key).toString();
    }

//    public static boolean isActiveSession() {
//        return session != null;
//    }
}
