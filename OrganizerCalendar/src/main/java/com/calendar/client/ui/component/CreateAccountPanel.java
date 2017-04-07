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

        // Create a panel to hold all of the form widgets.
        FlowPanel panel = new FlowPanel();
        accountPanel.setWidget(panel);

        // Create a TextBox, giving it a name so that it will be submitted.
        TextBox loginTextBox = new TextBox();
        PasswordTextBox passwordTextBox = new PasswordTextBox();
        Label loginLabel = new Label();
        Label passwordLabel = new Label();

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

        // Add a 'submit' button.
        panel.add(new Button("Submit", new ClickHandler() {
            public void onClick(ClickEvent event) {
                accountPanel.submit();
            }
        }));
        // Add an event handler to the form.
        accountPanel.addSubmitHandler(new FormPanel.SubmitHandler() {
            public void onSubmit(FormPanel.SubmitEvent event) {
                // This event is fired just before the form is submitted. We can take
                // this opportunity to perform validation.
                if (loginTextBox.getText().length() == 0 ||
                        passwordTextBox.getText().length() == 0) {
                    Window.alert("The filds must not be empty");
                    event.cancel();
                }
            }
        });
        accountPanel.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
            public void onSubmitComplete(FormPanel.SubmitCompleteEvent event) {
                // When the form submission is successfully completed, this event is
                // fired. Assuming the service returned a response of type text/html,
                // we can get the result text here (see the FormPanel documentation for
                // further explanation).
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

    }

    public FormPanel getAccountPanel() {
        return accountPanel;
    }

    public void setAccountPanel(FormPanel accountPanel) {
        this.accountPanel = accountPanel;
    }
}
