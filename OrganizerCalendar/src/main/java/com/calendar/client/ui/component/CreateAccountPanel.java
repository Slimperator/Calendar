package com.calendar.client.ui.component;

import com.calendar.client.InfoService;
import com.calendar.client.json.AccountConfirmation;
import com.calendar.client.json.EventConfirmation;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 * Created by Владимир on 07.04.2017.
 */
public class CreateAccountPanel {
    private FormPanel accountPanel;

    public CreateAccountPanel(){
        accountPanel = new FormPanel();
        accountPanel.setStyleName("centerPanelStyle");
        // Create a panel to hold all of the form widgets.
        FlowPanel panel = new FlowPanel();
        accountPanel.setWidget(panel);

        // Create a TextBox, giving it a name so that it will be submitted.
        TextBox loginTextBox = new TextBox();
        PasswordTextBox passwordTextBox = new PasswordTextBox();
        Label loginLabel = new Label();
        Label passwordLabel = new Label();

        loginLabel.setStyleName("labelStyle");
        passwordLabel.setStyleName("labelStyle");

        loginTextBox.setStyleName("textBoxStyle");
        passwordTextBox.setStyleName("textBoxStyle");

        loginLabel.setText("Login:");
        passwordLabel.setText("Password:");

        loginLabel.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        passwordTextBox.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        loginTextBox.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        passwordLabel.getElement().getStyle().setDisplay(Style.Display.BLOCK);

        panel.add(loginLabel);
        panel.add(loginTextBox);
        panel.add(passwordLabel);
        panel.add(passwordTextBox);

        Button button = new Button("Submit", new ClickHandler() {
            public void onClick(ClickEvent event) {
                if (loginTextBox.getText().length() == 0 ||
                        passwordTextBox.getText().length() == 0) {
                    Window.alert("The filds must not be empty");
                    return;
                }
                AccountConfirmation ac = new AccountConfirmation();
                ac.login = loginTextBox.getText();
                ac.newPassword = passwordTextBox.getText();
                InfoService.Util.getService().createAccount(ac, new MethodCallback<AccountConfirmation>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        Window.alert("Error: Can't send event on server");
                    }

                    @Override
                    public void onSuccess(Method method, AccountConfirmation accountConfirmation) {
                        Window.alert("Success!");
                    }
                });
            }
        });
        button.setStyleName("menuButtonStyle");

        // Add a 'submit' button.
        panel.add(button);
    }

    public FormPanel getAccountPanel() {
        return accountPanel;
    }

    public void setAccountPanel(FormPanel accountPanel) {
        this.accountPanel = accountPanel;
    }
}
