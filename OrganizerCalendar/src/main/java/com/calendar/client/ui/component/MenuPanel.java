package com.calendar.client.ui.component;

import com.calendar.client.InfoService;
import com.calendar.client.json.AccountConfirmation;
import com.calendar.client.json.EventConfirmation;
import com.calendar.client.ui.buffer.BufferSingletone;
import com.calendar.client.ui.buffer.CurrentState;
import com.calendar.client.ui.controller.StateController;
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
        menuPanel.setStyleName("menuStyle");
        Button eventsButton = new Button("Events");
        Button newEventButton = new Button("New Event");
        Button accountButton = new Button("Account");
        Button exitButton = new Button("Logout");

        eventsButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        accountButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        exitButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        newEventButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);

        eventsButton.setStyleName("menuButtonStyle");
        accountButton.setStyleName("menuButtonStyle");
        exitButton.setStyleName("menuButtonStyle");
        newEventButton.setStyleName("menuButtonStyle");

        newEventButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                StateController.setCurrentState(CurrentState.NewEvents);
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
                StateController.setCurrentState(CurrentState.AccountInfo);
            }
        });
        eventsButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                BufferSingletone.getBuffer().notifyObserver();
                StateController.setCurrentState(CurrentState.Events);
            }
        });
        exitButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                InfoService.Util.getService().logout(new MethodCallback<Void>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        Window.alert("Logout fail!");
                    }

                    @Override
                    public void onSuccess(Method method, Void s) {
                        Window.alert("Logout success!");
                        StateController.setCurrentState(CurrentState.Login);
                    }
                });
            }
        });
        menuPanel.add(eventsButton);
        menuPanel.add(newEventButton);
        menuPanel.add(accountButton);
        menuPanel.add(exitButton);
    }

    public FlowPanel getMenuPanel() {
        return menuPanel;
    }

    public void setMenuPanel(FlowPanel menuPanel) {
        this.menuPanel = menuPanel;
    }
}
