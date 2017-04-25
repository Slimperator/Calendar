package com.calendar.client.ui.component;

/**
 * Created by Владимир on 07.04.2017.
 */
public interface Observable {
    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
    void notifyObserver();
}
