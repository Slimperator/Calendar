package com.calendar.client.ui.controllers;

import com.calendar.client.ui.buffer.CurrentState;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владимир on 07.04.2017.
 */
public class CenterFlowPannelStateController {
    private static CurrentState currentState = CurrentState.Events;
    private static List<Widget> panels = new ArrayList<>();
    private static FlowPanel centerPanel;

    public static CurrentState getCurrentState() {
        return currentState;
    }

    public static void setCurrentState(CurrentState currentState) {
        CenterFlowPannelStateController.currentState = currentState;
        changeCurrentPanel();
    }

    public static FlowPanel getCenterPanel() {
        return centerPanel;
    }

    public static void setCenterPanel(FlowPanel centerPanel) {
        CenterFlowPannelStateController.centerPanel = centerPanel;
    }

    private static void changeCurrentPanel()
    {
        if(centerPanel == null)
            return;
        centerPanel.clear();
        switch (currentState){
            case AccountInfo:
                centerPanel.add(panels.get(2));
                break;
            case Events:
                centerPanel.add(panels.get(0));
                break;
            case newEvents:
                centerPanel.add(panels.get(1));
                break;
            case Settings:
                centerPanel.add(panels.get(4));
                break;
            case newAccount:
                centerPanel.add(panels.get(3));
                break;
        }
    }

    public static void addSimplePanels(Widget accountInfo, Widget events, Widget newEvent, Widget newAccount)
    {
        panels.clear();
        panels.add(events);
        panels.add(newEvent);
        panels.add(accountInfo);
        panels.add(newAccount);
    }
}
