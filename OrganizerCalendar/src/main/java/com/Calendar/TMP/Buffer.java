package com.Calendar.TMP;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Владимир on 06.02.2017.
 */
public class Buffer {
    private static Buffer instance = null;
    private Connection connection;
    private static final String accountsURL = "jdbc:mysql://localhost:6666/accounts";
    private static final String accountsUSERNAME = "root";
    private static final String accountsPASSWORD = "qwerty123456";
    private static final String calendarURL = "";
    private static final String calendarUSERNAME = "";
    private static final String calendarPASSWORD = "";

    private Buffer ()
    {}

    public static Buffer getInstance()
    {
        if (instance == null)
            instance = new Buffer();
        return instance;
    }
    private Connection getConnection()
    {
        Connection newConnection = null;
        try {
            newConnection = DriverManager.getConnection(accountsURL, accountsUSERNAME, accountsPASSWORD);
        }
        catch (SQLException e)
        {
            System.err.println("error sql connection");
        }
        return newConnection;
    }
}
