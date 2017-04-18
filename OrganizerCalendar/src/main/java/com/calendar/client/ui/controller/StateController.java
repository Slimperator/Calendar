package com.calendar.client.ui.controller;

import com.calendar.client.ui.buffer.CurrentState;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владимир on 07.04.2017.
 */
public class StateController {
    private static CurrentState currentState = CurrentState.Login;
    private static List<Widget> centerPanels = new ArrayList<>();
    private static List<Widget> menuPanels = new ArrayList<>();
    private static FlowPanel centerPanel;
    private static FlowPanel menuPanel;

    public static CurrentState getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(CurrentState currentState) {
        StateController.currentState = currentState;
        changeCurrentPanel();
    }

    public static FlowPanel getCenterPanel() {
        return centerPanel;
    }

    public static void setCenterPanel(FlowPanel centerPanel) {
        StateController.centerPanel = centerPanel;
    }

    public static FlowPanel getMenuPanel() {
        return menuPanel;
    }

    public static void setMenuPanel(FlowPanel menuPanel) {
        StateController.menuPanel = menuPanel;
    }

    private static void changeCurrentPanel()
    {
        if(centerPanel == null | menuPanel == null)
            return;
        centerPanel.clear();
        if(!currentState.equals(CurrentState.Login))
        {
            menuPanel.clear();
            menuPanel.add(menuPanels.get(1));
        }
        switch (currentState){
            case AccountInfo:
                centerPanel.add(centerPanels.get(2));
                break;
            case Events:
                centerPanel.add(centerPanels.get(0));
                break;
            case NewEvents:
                centerPanel.add(centerPanels.get(1));
                break;
            case Settings:
                centerPanel.add(centerPanels.get(4));
                break;
            case NewAccount:
                centerPanel.add(centerPanels.get(3));
                break;
            case Login:
                menuPanel.clear();
                menuPanel.add(menuPanels.get(0));
                break;
        }
    }

    public static void addCenterPanels(Widget accountInfo, Widget events, Widget newEvent, Widget newAccount)
    {
        centerPanels.clear();
        centerPanels.add(events);
        centerPanels.add(newEvent);
        centerPanels.add(accountInfo);
        centerPanels.add(newAccount);
    }

    public static void addMenuPanels(Widget nonAuthorise, Widget authorise){
        menuPanels.clear();
        menuPanels.add(nonAuthorise);
        menuPanels.add(authorise);
    }
}
