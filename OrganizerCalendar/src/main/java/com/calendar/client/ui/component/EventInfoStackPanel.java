package com.calendar.client.ui.component;

import com.calendar.client.json.EventConfirmation;
import com.calendar.client.ui.buffer.BufferSingletone;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import java.util.List;

/**
 * Created by Владимир on 20.03.2017.
 */
public class EventInfoStackPanel implements Observer{
    private StackLayoutPanel eventsStackLayoutPanel = null;

    public EventInfoStackPanel()
    {
        eventsStackLayoutPanel = new StackLayoutPanel(Style.Unit.EM);
    }

    public void updateEventsStackLayoutPanel(List<EventConfirmation> events)
    {
        for(EventConfirmation event: events)
        {
            VerticalPanel body = new VerticalPanel();
            body.add(new HTML(event.description));
            body.add(new HTML("Begin date:" + event.beginDate));
            body.add(new HTML("End date:" + event.endDate));
            eventsStackLayoutPanel.add(new HTML(event.name), body, 4);
        }
    }

    public StackLayoutPanel getEventsStackLayoutPanel() {
        return eventsStackLayoutPanel;
    }

    public void setEventsStackLayoutPanel(StackLayoutPanel eventsStackLayoutPanel) {
        this.eventsStackLayoutPanel = eventsStackLayoutPanel;
    }

    @Override
    public void update() {
        BufferSingletone buffer = BufferSingletone.getBuffer();
        if(buffer.getEvents() == null || buffer.getEvents().size() == 0)
            return;
        updateEventsStackLayoutPanel(buffer.getEvents());
    }
}
