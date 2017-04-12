package com.calendar.client.ui.component;

import antlr.StringUtils;
import com.calendar.client.InfoService;
import com.calendar.client.json.EventConfirmation;
import com.calendar.client.ui.buffer.BufferSingletone;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.List;

/**
 * Created by Владимир on 20.03.2017.
 */
public class EventInfoStackPanel implements Observer{
    private StackLayoutPanel eventsStackLayoutPanel = null;
    private String currentEventName = "";


    public EventInfoStackPanel()
    {
        eventsStackLayoutPanel = new StackLayoutPanel(Style.Unit.EM);
        eventsStackLayoutPanel.addSelectionHandler(new SelectionHandler<Integer>() {
            @Override
            public void onSelection(SelectionEvent<Integer> event) {
                currentEventName = BufferSingletone.getBuffer().getEvents().get(
                        eventsStackLayoutPanel.getVisibleIndex()
                ).name;
            }
        });
    }

    public void updateEventsStackLayoutPanel(List<EventConfirmation> events)
    {

        eventsStackLayoutPanel.clear();
        //Window.alert("Try update event");
        for(EventConfirmation event: events)
        {
            VerticalPanel body = new VerticalPanel();
            body.add(new HTML(event.description));
            body.add(new HTML("Begin date:   " + event.beginDate));
            body.add(new HTML("End date:     " + event.endDate));
            body.add(new HTML("Creator:      " + event.account));
            body.add(new HTML("Members:"));
            for(String member: event.invites)
                body.add(new HTML(member));
            FlowPanel panel = new FlowPanel();

            Button dropEventButton = new Button("Drop Event");
            Button inviteButton = new Button("Invite");
            Button removeInviteButton = new Button("Re-invite");
            TextBox inviteTextBox = new TextBox();
            dropEventButton.setStyleName("stackPanelButton");
            inviteButton.setStyleName("stackPanelButton");
            removeInviteButton.setStyleName("stackPanelButton");
            inviteTextBox.getElement().getStyle().setDisplay(Style.Display.BLOCK);
            dropEventButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    InfoService.Util.getService().deleteEvent(currentEventName, new MethodCallback<EventConfirmation>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {
                            Window.alert("Can't delete event");
                        }

                        @Override
                        public void onSuccess(Method method, EventConfirmation eventConfirmation) {
                            BufferSingletone.getBuffer().notifyObserver();
                        }
                    });
                }
            });
            removeInviteButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    EventConfirmation request = new EventConfirmation();
                    request.name = currentEventName;
                    request.invites.add(inviteTextBox.getText());
                    InfoService.Util.getService().deleteInvite(request, new MethodCallback<Void>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {
                            Window.alert("Can't delete event");
                        }

                        @Override
                        public void onSuccess(Method method, Void aVoid) {
                            BufferSingletone.getBuffer().notifyObserver();
                        }
                    });
                }
            });
            inviteButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    EventConfirmation request = new EventConfirmation();
                    request.name = currentEventName;
                    request.invites.add(inviteTextBox.getText());
                    InfoService.Util.getService().createInvite(request, new MethodCallback<Void>() {
                        @Override
                        public void onFailure(Method method, Throwable throwable) {
                            Window.alert("Can't add event");
                        }

                        @Override
                        public void onSuccess(Method method, Void aVoid) {
                            BufferSingletone.getBuffer().notifyObserver();
                        }
                    });
                }
            });

            panel.add(inviteTextBox);
            panel.add(inviteButton);
            panel.add(removeInviteButton);
            panel.add(dropEventButton);
            body.add(panel);
            body.getElement().getStyle().setWidth(100, Style.Unit.PCT);
            body.getElement().getStyle().setBackgroundColor("#FFC0CB");
            HTML header = new HTML(event.name);
            eventsStackLayoutPanel.add(body, header, 4);
        }
        eventsStackLayoutPanel.setHeight((40*eventsStackLayoutPanel.getWidgetCount() + 200)+"px");
        //Window.alert(Integer.toString(eventsStackLayoutPanel.getWidgetCount()));
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
