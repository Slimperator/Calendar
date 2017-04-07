package com.calendar.client.ui.component;

import com.calendar.client.json.EventConfirmation;
import com.calendar.client.ui.buffer.BufferSingletone;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Владимир on 20.03.2017.
 */
public class CalendarPanel implements Observer{
    private FlowPanel calendarFlowPanel;
    private DatePicker calendar;
    private List<Date> currentDates;
    public CalendarPanel()
    {
        calendarFlowPanel = new FlowPanel();
        calendar = new DatePicker();
        calendar.addValueChangeHandler(new ValueChangeHandler<Date>() {
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date date = event.getValue();
                BufferSingletone.getBuffer().setChoosenDate(date);
            }
        });
        calendar.setValue(new Date(), true);
        calendar.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        calendarFlowPanel.add(calendar);
    }
    public void updateCalendarFlowPanel(List<Date> dates){
        if(currentDates!=null)
            calendar.removeStyleFromDates("dateWithEventStyle", currentDates);
        calendar.addStyleToDates("dateWithEventStyle", dates);
        currentDates = dates;
    }

    public FlowPanel getCalendarFlowPanel() {
        return calendarFlowPanel;
    }

    public void setCalendarFlowPanel(FlowPanel calendarFlowPanel) {
        this.calendarFlowPanel = calendarFlowPanel;
    }

    @Override
    public void update() {
        BufferSingletone buffer = BufferSingletone.getBuffer();
        if (buffer.getEvents()==null||buffer.getEvents().size() == 0 )
            return;
        List<Date> dates = new ArrayList<>();
        for(EventConfirmation event: buffer.getEvents())
        {
            dates.add(event.beginDate);
        }
        this.updateCalendarFlowPanel(dates);
    }
}
