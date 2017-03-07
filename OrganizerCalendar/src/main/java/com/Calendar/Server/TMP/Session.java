package com.Calendar.Server.TMP;



/**
 * Created by Владимир on 06.02.2017.
 */
public class Session {
    private String currentUser;

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
