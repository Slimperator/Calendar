package com.calendar.client.ui.component;

import com.calendar.client.InfoService;
import com.calendar.client.json.AccountConfirmation;
import com.calendar.client.ui.buffer.BufferSingletone;
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
public class AccountInfo implements Observer{
    private FormPanel accountInfoForm;
    private Label aboutLabel;
    public AccountInfo()
    {
        accountInfoForm = new FormPanel();
        accountInfoForm.setStyleName("centerPanelStyle");
        // Create a panel to hold all of the form widgets.
        FlowPanel panel = new FlowPanel();
        accountInfoForm.setWidget(panel);

        Button getInfoButton = new Button("Get Info");
        getInfoButton.setStyleName("menuButtonStyle");
        getInfoButton.getElement().getStyle().setDisplay(Style.Display.BLOCK);

        getInfoButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                InfoService.Util.getService().getAccount(new MethodCallback<AccountConfirmation>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        Window.alert("Can't take account info from server");
                    }

                    @Override
                    public void onSuccess(Method method, AccountConfirmation accountConfirmation) {
                        aboutLabel.setText(accountConfirmation.login);
                    }
                });
            }
        });
        // Create a TextBox, giving it a name so that it will be submitted.
        Label nameLabel = new Label();
        aboutLabel = new Label();

        nameLabel.setText("Account name:");
        aboutLabel.setText("");

        nameLabel.setStyleName("labelStyle");
        aboutLabel.setStyleName("labelStyle");

        nameLabel.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        aboutLabel.getElement().getStyle().setDisplay(Style.Display.BLOCK);

        panel.add(nameLabel);
        panel.add(aboutLabel);
        panel.add(getInfoButton);
    }

    public FormPanel getAccountInfoForm() {
        return accountInfoForm;
    }

    public void setAccountInfoForm(FormPanel accountInfoForm) {
        this.accountInfoForm = accountInfoForm;
    }

    @Override
    public void update() {
        this.aboutLabel.setText(BufferSingletone.getBuffer().getAccountInfo());
    }
}
