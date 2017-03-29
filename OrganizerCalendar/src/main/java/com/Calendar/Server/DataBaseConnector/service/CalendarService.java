package com.calendar.server.databaseconnector.service;

import com.calendar.server.databaseconnector.entity.Calendar;

import java.sql.Date;
import java.util.List;

/**
 * Created by Владимир on 12.02.2017.
 */
public interface CalendarService {
    void deleteEvent(Calendar calendar);
    Calendar getEvent(String eventName);
    List<Calendar> getEventsByRange(Date begin, Date end);
    List<Calendar> getAllEvents();
    Calendar addEvent(Calendar event);
    Calendar editEvent(Calendar event);
}
