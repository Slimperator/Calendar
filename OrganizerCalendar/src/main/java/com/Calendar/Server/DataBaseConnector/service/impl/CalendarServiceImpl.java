package com.Calendar.Server.DataBaseConnector.service.impl;

import com.Calendar.Server.DataBaseConnector.entity.Calendar;
import com.Calendar.Server.DataBaseConnector.repository.CalendarRepository;
import com.Calendar.Server.DataBaseConnector.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Date;
import java.util.List;

/**
 * Created by Владимир on 12.02.2017.
 */
@Service
public class CalendarServiceImpl implements CalendarService {
    @Autowired
    private CalendarRepository calendarRepository;

    @Override
    public void deleteEvent(Calendar calendar) {
        calendarRepository.delete(calendar);
    }

    @Override
    public Calendar getEvent(String eventName) {
        return calendarRepository.findeByEventName(eventName);
    }

    @Override
    public List<Calendar> getEventsByRange(Date begin, Date end) {
        return calendarRepository.findeByDateRange(begin, end);
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
