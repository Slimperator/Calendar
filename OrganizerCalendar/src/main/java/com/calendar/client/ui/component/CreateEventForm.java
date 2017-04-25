package com.calendar.client.ui.component;

import com.calendar.client.InfoService;
import com.calendar.client.json.EventConfirmation;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DateBox;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Владимир on 20.03.2017.
 */
public class CreateEventForm {
    private FormPanel eventForm;
    public CreateEventForm()
    {
        eventForm = new FormPanel();
        eventForm.setStyleName("centerPanelStyle");
        // Create a panel to hold all of the form widgets.
        FlowPanel panel = new FlowPanel();
        FlowPanel labelDate = new FlowPanel();
        FlowPanel boxDate = new FlowPanel();
        eventForm.setWidget(panel);

        // Create a TextBox, giving it a name so that it will be submitted.
        TextBox nameTextBox = new TextBox();
        DateBox dateBeginDateBox = new DateBox();
        TextArea descriptionTextBox = new TextArea();
        TextBox invitesTextBox = new TextBox();
        DateBox dateEndDateBox = new DateBox();
        Label nameLabel = new Label();
        Label dateBeginLabel = new Label();
        Label descriptionLabel = new Label();
        Label dateEndLabel = new Label();
        Label invitesLabel = new Label();

        nameLabel.setStyleName("labelStyle");
        dateBeginLabel.setStyleName("dataBoxLabelStyle");
        dateEndLabel.setStyleName("dataBoxLabelStyle");
        descriptionLabel.setStyleName("labelStyle");
        invitesLabel.setStyleName("labelStyle");

        nameTextBox.setStyleName("textBoxStyle");
        invitesTextBox.setStyleName("textBoxStyle");
        dateBeginDateBox.setStyleName("dataBoxStyle");
        dateEndDateBox.setStyleName("dataBoxStyle");
        descriptionTextBox.setStyleName("descriptionTextBoxStyle");

        nameLabel.setText("Event Name:");
        dateBeginLabel.setText("Begin in:");
        dateEndLabel.setText("End in:");
        descriptionLabel.setText("Description:");
        invitesLabel.setText("Invite somebody:");

        nameLabel.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        descriptionTextBox.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        nameTextBox.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        invitesTextBox.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        invitesLabel.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        labelDate.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        boxDate.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        descriptionLabel.getElement().getStyle().setDisplay(Style.Display.BLOCK);

        DateTimeFormat dateFormat = DateTimeFormat.getFormat("yyyy-MM-dd HH:mm");
        dateBeginDateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
        dateEndDateBox.setFormat(new DateBox.DefaultFormat(dateFormat));

        labelDate.add(dateBeginLabel);
        labelDate.add(dateEndLabel);
        boxDate.add(dateBeginDateBox);
        boxDate.add(dateEndDateBox);

        panel.add(nameLabel);
        panel.add(nameTextBox);
        panel.add(labelDate);
        panel.add(boxDate);
        panel.add(invitesLabel);
        panel.add(invitesTextBox);
        panel.add(descriptionLabel);
        panel.add(descriptionTextBox);

        Button button = new Button("Submit", new ClickHandler() {
            public void onClick(ClickEvent event) {

                if (nameTextBox.getText().length() == 0 ||
                        descriptionTextBox.getText().length() == 0 ||
                        dateBeginDateBox.getValue() == null ||
                        dateEndDateBox.getValue() == null) {
                    Window.alert("The filds must not be empty");
                    return;
                }

                if(dateBeginDateBox.getValue().compareTo(dateEndDateBox.getValue()) >= 0)
                {
                    Window.alert("Begin date must not be more than end date");
                    return;
                }

                //GWT.log("Event submit begin");
                EventConfirmation ec = new EventConfirmation();

                ec.name = nameTextBox.getText();
                ec.description = descriptionTextBox.getText();
                //GWT.log("Create DATE objects");
                ec.beginDate = dateBeginDateBox.getValue();
                ec.endDate = dateEndDateBox.getValue();
                ec.invites = new ArrayList<>(Arrays.asList(invitesTextBox.getText().split(" ")));
                //GWT.log("Try connect with server");
                InfoService.Util.getService().createEvent(ec, new MethodCallback<EventConfirmation>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        Window.alert("Error: Can't send event on server");
                    }

                    @Override
                    public void onSuccess(Method method, EventConfirmation eventConfirmation) {
                        Window.alert("Success!");
                    }
                });
            }
        });
        button.setStyleName("menuButtonStyle");
        // Add a 'submit' button.
        panel.add(button);
    }

    public FormPanel getEventForm() {
        return eventForm;
    }

    public void setEventForm(FormPanel eventForm) {
        this.eventForm = eventForm;
    }
}
