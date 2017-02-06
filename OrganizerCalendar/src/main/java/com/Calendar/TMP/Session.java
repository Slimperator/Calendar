package com.Calendar.TMP;

import javax.servlet.http.HttpSession;

/**
 * Created by Владимир on 06.02.2017.
 */
public class Session {
    private String currentUser;
    private HttpSession currentSession;

    public Session (String username)
    {
        currentUser = username;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

}
