package com.calendar.client.ui.buffer;

import com.calendar.client.json.EventConfirmation;
import com.calendar.client.ui.component.Observable;
import com.calendar.client.ui.component.Observer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Владимир on 07.04.2017.
 */
public class BufferSingletone implements Observable {
    private static BufferSingletone bufferSingletone = null;
    private List<EventConfirmation> events = new ArrayList<>();
    private Date choosenDate = new Date();
    private List<Observer> observers = new ArrayList<>();
    private String accountInfo = "";
    private boolean isLogin = false;

    private BufferSingletone()
    {}
    public static BufferSingletone getBuffer()
    {
        if(bufferSingletone == null)
            bufferSingletone = new BufferSingletone();
        return bufferSingletone;
    }
    public List<EventConfirmation> getEvents() {
        return events;
    }

    public Date getChoosenDate() {
        return choosenDate;
    }

    public void setChoosenDate(Date choosenDate) {
        this.choosenDate = choosenDate;
    }

    public void setEvents(List<EventConfirmation> events) {
        this.events = events;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver() {
        for(Observer observer: observers)
            observer.update();
    }

    public String getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(String accountInfo) {
        this.accountInfo = accountInfo;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
