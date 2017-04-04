package com.calendar.client;

import com.calendar.client.json.LoginConfirmation;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.datepicker.client.DatePicker;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

import java.util.Date;

/**
 * Created by Владимир on 20.02.2017.
 */
public class CalendarGWT implements EntryPoint {
    public void onModuleLoad() {
        Button button = new Button("Click Me");
        button.setSize("300", "100");
        /*button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                InfoService.Util.getService().createEvent(new MethodCallback<EventConfirmation>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        GWT.log("Error");
                    }

                    @Override
                    public void onSuccess(Method method, EventConfirmation eventConfirmation) {

                    }
                });
            }
        });*/

        StackLayoutPanel f = new StackLayoutPanel(Style.Unit.EM);
        f.add(new HTML("Event 1"), new HTML("Hello"), 4);
        f.add(new HTML("Event 2"), new HTML("Event"), 4);
        f.add(new HTML("Event 3"), new HTML("World"), 4);

        //Buttons on flow Panel
        Button button1 = new Button("Account");
        button.setSize("300", "100");
        button1.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        Button button2 = new Button("New Event");
        button.setSize("300", "100");
        button2.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        Button button3 = new Button("Settings");
        button.setSize("300", "100");
        button3.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        Button button4 = new Button("Exit");
        button.setSize("300", "100");
        button4.getElement().getStyle().setDisplay(Style.Display.BLOCK);

        DatePicker datePicker = new DatePicker();
        final Label text = new Label();
        datePicker.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        text.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        // Set the value in the text box when the user selects a date
        datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
            public void onValueChange(ValueChangeEvent<Date> event) {
                Date date = event.getValue();
                String dateString = DateTimeFormat.getMediumDateFormat().format(date);
                text.setText(dateString);
            }
        });

        // Set the default value
        datePicker.setValue(new Date(), true);
        FlowPanel verticalFlowPanel2 = new FlowPanel();
        verticalFlowPanel2.add(text);
        verticalFlowPanel2.add(datePicker);

        // Example of a flow panel with all elements disposed in vertical
        FlowPanel verticalFlowPanel = new FlowPanel();
        TextBox textBox = new TextBox();
        Label label = new Label("Foo");
        textBox.getElement().getStyle().setDisplay(Style.Display.BLOCK);
        verticalFlowPanel.add(button1);
        verticalFlowPanel.add(button2);
        verticalFlowPanel.add(button3);
        verticalFlowPanel.add(button4);

        //Create SplitLayoutPanel with MenuPanel and CalendarPanel
        DockLayoutPanel p = new DockLayoutPanel(Style.Unit.EM);
        p.addEast(verticalFlowPanel2, 8);
        p.addWest(verticalFlowPanel, 8);
        p.add(f);
        RootLayoutPanel rp = RootLayoutPanel.get();
        rp.add(p);
        LoginConfirmation lg = new LoginConfirmation();
        lg.login = "asda";
        lg.password = "asd";
        InfoService.Util.getService().login(lg, new MethodCallback<String>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {

            }

            @Override
            public void onSuccess(Method method, String s) {
                Window.alert("s");
            }
        });
    }
}
