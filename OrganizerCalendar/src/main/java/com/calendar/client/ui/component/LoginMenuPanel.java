package com.calendar.client.ui.component;

import com.calendar.client.ui.buffer.CurrentState;
import com.calendar.client.ui.controller.StateController;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlowPanel;

/**
 * Created by Владимир on 17.04.2017.
 */
public class LoginMenuPanel {
        private FlowPanel menuPanel;
        private final String widthButton = "300";
        private final String heightButton = "100";

        public LoginMenuPanel()
        {
            menuPanel = new FlowPanel();
            menuPanel.setStyleName("menuStyle");
            Button newAccountButton = new Button("Create Account");
            Button loginButton = new Button("Login");

            newAccountButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);
            loginButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);

            loginButton.setStyleName("menuButtonStyle");
            newAccountButton.setStyleName("menuButtonStyle");

            loginButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    Window.Location.replace("/com.Calendar.Calendar/service/login");
                }
            });
            newAccountButton.addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent event) {
                    StateController.setCurrentState(CurrentState.NewAccount);
                }
            });

            menuPanel.add(loginButton);
            menuPanel.add(newAccountButton);
        }

        public FlowPanel getMenuPanel() {
            return menuPanel;
        }

        public void setMenuPanel(FlowPanel menuPanel) {
            this.menuPanel = menuPanel;
        }
}

