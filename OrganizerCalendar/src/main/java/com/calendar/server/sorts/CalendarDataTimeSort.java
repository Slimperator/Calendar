package com.calendar.server.sorts;

import com.calendar.server.databaseconnector.entity.Calendar;
import java.util.Comparator;

/**
 * Created by Владимир on 18.04.2017.
 */
public class CalendarDataTimeSort implements Comparator<Calendar> {
    public int compare(Calendar obj1, Calendar obj2) {

        Long begin1 = obj1.getBegin_data().getTime();
        Long begin2 = obj2.getBegin_data().getTime();

        return begin1.compareTo(begin2);
    }
}
