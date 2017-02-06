package com.Calendar.DataBaseConnector;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * Created by Владимир on 19.01.2017.
 */
public class AccountsConnector implements IDBConnector{
    private Connection connection;

    public AccountsConnector(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public ArrayList<String> getNotes() {
        return null;
    }

    @Override
    public void editNote(String note) {

    }

    @Override
    public void addNote(ArrayList<String> notes) {

    }

    @Override
    public void deleteNote(String note) {

    }
}
