package com.calendar.server.databaseconnector.service;

import com.calendar.server.databaseconnector.entity.Accounts;
import com.calendar.server.databaseconnector.entity.Calendar;

import java.util.Date;
import java.util.List;

/**
 * Created by Владимир on 12.02.2017.
 */
public interface CalendarService {
    void deleteEvent(Calendar calendar, Accounts account);
    Calendar getEvent(String eventName);
    List<Calendar> getEventsByRange(Date begin, Date end, Accounts account);
    List<Calendar> getAllEvents();
    Calendar addEvent(Calendar event);
    Calendar editEvent(Calendar event);
}
