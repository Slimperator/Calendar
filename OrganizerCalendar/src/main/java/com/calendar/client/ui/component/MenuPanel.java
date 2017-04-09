package com.calendar.client.ui.component;

import com.calendar.client.InfoService;
import com.calendar.client.json.AccountConfirmation;
import com.calendar.client.json.EventConfirmation;
import com.calendar.client.ui.buffer.BufferSingletone;
import com.calendar.client.ui.buffer.CurrentState;
import com.calendar.client.ui.controllers.CenterFlowPannelStateController;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Date;
import java.util.List;

/**
 * Created by Владимир on 20.03.2017.
 */
public class MenuPanel {
    private FlowPanel menuPanel;
    private final String widthButton = "300";
    private final String heightButton = "100";

    public MenuPanel()
    {
        menuPanel = new FlowPanel();
        Button eventsButton = new Button("Events");
        Button newEventButton = new Button("New Event");
        Button accountButton = new Button("Account");
        Button exitButton = new Button("Exit");
        Button newAccountButton = new Button("Create Account");
        Button loginButton = new Button("Login");

        eventsButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        accountButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        exitButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        newEventButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        newAccountButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        loginButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        //eventsButton.setSize(widthButton, heightButton);
        //accountButton.setSize(widthButton, heightButton);
        //exitButton.setSize(widthButton, heightButton);
        //newEventButton.setSize(widthButton, heightButton);
        loginButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Window.Location.replace("/com.Calendar.Calendar/service/login");
            }
        });
        newAccountButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                CenterFlowPannelStateController.setCurrentState(CurrentState.newAccount);
            }
        });
        newEventButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                CenterFlowPannelStateController.setCurrentState(CurrentState.newEvents);
            }
        });
        accountButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                InfoService.Util.getService().getAccount(new MethodCallback<AccountConfirmation>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        Window.alert("Error: Can't load account info from server");
                    }

                    @Override
                    public void onSuccess(Method method, AccountConfirmation accountConfirmation) {
                        BufferSingletone.getBuffer().setAccountInfo(accountConfirmation.login);
                        BufferSingletone.getBuffer().notifyObserver();
                    }
                });
                CenterFlowPannelStateController.setCurrentState(CurrentState.AccountInfo);
            }
        });
        eventsButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                EventConfirmation ec = new EventConfirmation();
                ec.beginDate = BufferSingletone.getBuffer().getChoosenDate();
                ec.endDate = new Date(ec.beginDate.getTime() + (1000 * 60 * 60 * 24));
                //Window.alert("Date in buffer:" + BufferSingletone.getBuffer().getChoosenDate() + "; Date in request:"
                //+ ec.beginDate + "   " + ec.endDate);
                InfoService.Util.getService().getEventsByRange(ec, new MethodCallback<List<EventConfirmation>>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        Window.alert("Error: Can't load events from server");
                        Window.alert(throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Method method, List<EventConfirmation> eventConfirmations) {
                        //Window.alert("All OK");
                        BufferSingletone.getBuffer().setEvents(eventConfirmations);
                        Window.alert(BufferSingletone.getBuffer().getEvents().toString());
                        BufferSingletone.getBuffer().notifyObserver();
                    }
                });
                CenterFlowPannelStateController.setCurrentState(CurrentState.Events);
            }
        });
        exitButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                InfoService.Util.getService().logout(new MethodCallback<String>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        Window.alert("Logout fail!");
                    }

                    @Override
                    public void onSuccess(Method method, String s) {
                        Window.alert("Logout success!");
                    }
                });
            }
        });
        menuPanel.add(loginButton);
        menuPanel.add(eventsButton);
        menuPanel.add(newEventButton);
        menuPanel.add(accountButton);
        menuPanel.add(newAccountButton);
        menuPanel.add(exitButton);
    }

    public FlowPanel getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(FlowPanel menuPanel) {
        this.menuPanel = menuPanel;
    }
}
