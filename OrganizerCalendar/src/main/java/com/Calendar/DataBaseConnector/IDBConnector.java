package com.Calendar.DataBaseConnector;

import java.util.ArrayList;

/**
 * Created by Владимир on 19.01.2017.
 */
public interface IDBConnector {
    ArrayList<String> getNotes(ArrayList<String> params);
    void editNote(String note);
    void addNote(ArrayList<String> notes);
    void deleteNote(String note);
}
