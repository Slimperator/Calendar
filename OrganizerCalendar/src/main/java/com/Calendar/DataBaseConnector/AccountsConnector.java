package com.Calendar.DataBaseConnector;

import com.Calendar.TMP.Session;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * Created by Владимир on 19.01.2017.
 */
public class AccountsConnector implements IDBConnector{
    private Connection connection;
    private Session currentSession;

    public AccountsConnector(Connection connection, Session currentSession)

    {

        this.currentSession = currentSession;
        this.connection = connection;

    }

    @Override
    public ArrayList<String> getNotes(ArrayList<String> params)
    {
        return null;
    }

    @Override
    public void editNote(String note) {
        //редактировать пароль
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("UPDATE accounts SET passwordHash=" + note +
                    " WHERE accountHash=" + currentSession.getCurrentUser() + ";");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addNote(ArrayList<String> notes) {
        //добавить аккаунт
        if(notes.size() != 2)
            return;
        try(Statement statement = connection.createStatement()) {
            statement.execute("INSERT INTO accounts(accountHash, passwordHash) VALUES(" + notes.get(0) +
            "," + notes.get(1) + ");");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteNote(String note) {
        //убрать аккаунт
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM accounts WHERE accountHash=" + note + ";");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
