package com.Calendar.DataBaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Владимир on 19.01.2017.
 */
public class CalendarConnector implements IDBConnector{
    private Connection connection;

    public CalendarConnector(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public ArrayList<String> getNotes(ArrayList<String> params) {
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
