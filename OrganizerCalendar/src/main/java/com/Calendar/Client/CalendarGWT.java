package com.Calendar.Client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

/**
 * Created by Владимир on 20.02.2017.
 */
public class CalendarGWT implements EntryPoint {
    public void onModuleLoad() {
        Button button = new Button("Click Me");
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                InfoService.Util.getService().getInfo(new MethodCallback<OrderConfirmation>() {
                    @Override
                    public void onFailure(Method method, Throwable throwable) {
                        GWT.log("Error");
                    }

                    @Override
                    public void onSuccess(Method method, OrderConfirmation orderConfirmation) {

                    }
                });
//                InfoService.Util.getService().getInfo(new MethodCallback<OrderConfirmation>() {
//                    @Override
//                    public void onSuccess(Method method, OrderConfirmation response) {
//                        RootPanel.get().add(new Label(response.toString()));
//                    }
//                    @Override
//                    public void onFailure(Method method, Throwable exception) {
//                        GWT.log("Error");
//                    }
//                });
            }
        });
        RootPanel.get().add(button);
    }
}
