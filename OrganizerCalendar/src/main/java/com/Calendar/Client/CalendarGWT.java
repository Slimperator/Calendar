package com.calendar.client;

import com.calendar.client.json.EventConfirmation;
import com.calendar.client.ui.buffer.BufferSingletone;
import com.calendar.client.ui.component.*;
import com.calendar.client.ui.controllers.CenterFlowPannelStateController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Владимир on 20.02.2017.
 */
public class CalendarGWT implements EntryPoint {
    static {
        Defaults.setDateFormat(null);
    }
    private CalendarPanel calendarPanel;
    private CreateEventForm createEventForm;
    private EventInfoStackPanel eventInfoStackPanel;
    private MenuPanel menuPanel;
    private FlowPanel centerFlowPanel;
    private AccountInfo accountInfo;
    private CreateAccountPanel createAccountPanel;
    private BufferSingletone buffer;
    public void onModuleLoad() {
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
        //Создаем элементы юи
        calendarPanel = new CalendarPanel();
        createEventForm = new CreateEventForm();
        eventInfoStackPanel = new EventInfoStackPanel();
        menuPanel = new MenuPanel();
        centerFlowPanel = new FlowPanel();
        centerFlowPanel.setStyleName("centerStyle");
        accountInfo = new AccountInfo();
        createAccountPanel = new CreateAccountPanel();
        //инициализируем буффер
        buffer = BufferSingletone.getBuffer();
        //Активируем контроллер центра экрана
        CenterFlowPannelStateController.setCenterPanel(centerFlowPanel);
        CenterFlowPannelStateController.addSimplePanels(
                accountInfo.getAccountInfoForm(),
                eventInfoStackPanel.getEventsStackLayoutPanel(),
                createEventForm.getEventForm(),
                createAccountPanel.getAccountPanel()
                );
        //регестрируем наблюдателя
        buffer.addObserver(calendarPanel);
        buffer.addObserver(eventInfoStackPanel);
        buffer.addObserver(accountInfo);
        //заполняем экран
        DockLayoutPanel mainDockLayoutPanel = new DockLayoutPanel(Style.Unit.EM);
        mainDockLayoutPanel.addEast(calendarPanel.getCalendarFlowPanel(), 11);
        mainDockLayoutPanel.addWest(menuPanel.getMenuPanel(), 11);
        mainDockLayoutPanel.add(centerFlowPanel);

        RootLayoutPanel rp = RootLayoutPanel.get();
        rp.add(mainDockLayoutPanel);
    }
}
