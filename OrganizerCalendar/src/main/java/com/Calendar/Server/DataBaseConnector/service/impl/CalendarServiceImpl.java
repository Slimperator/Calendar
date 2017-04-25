package com.calendar.server.databaseconnector.service.impl;

import com.calendar.server.databaseconnector.entity.Accounts;
import com.calendar.server.databaseconnector.entity.Calendar;
import com.calendar.server.databaseconnector.repository.CalendarRepository;
import com.calendar.server.databaseconnector.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * Created by Владимир on 12.02.2017.
 */
@Service
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

    @Override
    public void deleteEvent(Calendar calendar, Accounts account) {
        calendarRepository.deleteEvent(calendar.getName(), account);
        calendarRepository.flush();
    }

    @Override
    public Calendar getEvent(String eventName) {
        return calendarRepository.findeByEventName(eventName);
    }

    @Override
    public List<Calendar> getEventsByRange(Date begin, Date end, Accounts account) {
        return calendarRepository.findeByDateRange(begin, end, account);
    }

    @Override
    public List<Calendar> getAllEvents() {
        return calendarRepository.findAll();
    }

    @Override
    public Calendar addEvent(Calendar event) {
        return calendarRepository.saveAndFlush(event);
    }

    @Override
    public Calendar editEvent(Calendar event) {
        return calendarRepository.saveAndFlush(event);
    }
}
