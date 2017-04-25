package com.calendar.client;

import com.calendar.client.json.AccountConfirmation;
import com.calendar.client.ui.buffer.BufferSingletone;
import com.calendar.client.ui.buffer.CurrentState;
import com.calendar.client.ui.component.*;
import com.calendar.client.ui.controller.StateController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;

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
    private LoginMenuPanel loginMenuPanel;
    private FlowPanel menuFlowPanel;
    private FlowPanel centerFlowPanel;
    private AccountInfo accountInfo;
    private CreateAccountPanel createAccountPanel;
    private BufferSingletone buffer;
    public void onModuleLoad() {
        //Создаем элементы юи
        calendarPanel = new CalendarPanel();
        createEventForm = new CreateEventForm();
        eventInfoStackPanel = new EventInfoStackPanel();
        menuPanel = new MenuPanel();
        loginMenuPanel = new LoginMenuPanel();
        centerFlowPanel = new FlowPanel();
        menuFlowPanel = new FlowPanel();
        centerFlowPanel.setStyleName("centerStyle");
        accountInfo = new AccountInfo();
        createAccountPanel = new CreateAccountPanel();

        calendarPanel.getCalendar().setVisible(false);
        menuFlowPanel.getElement().getStyle().setHeight(100, Style.Unit.PCT);

        //инициализируем буффер
        buffer = BufferSingletone.getBuffer();
        //Активируем контроллер центра экрана
        StateController.setCenterPanel(centerFlowPanel);
        StateController.addCenterPanels(
                accountInfo.getAccountInfoForm(),
                eventInfoStackPanel.getEventsStackLayoutPanel(),
                createEventForm.getEventForm(),
                createAccountPanel.getAccountPanel()
                );
        //Активируем контроллер панели с меню
        StateController.setMenuPanel(menuFlowPanel);
        StateController.addMenuPanels(
                loginMenuPanel.getMenuPanel(),
                menuPanel.getMenuPanel()
        );
        //регестрируем наблюдателя
        buffer.addObserver(calendarPanel);
        buffer.addObserver(eventInfoStackPanel);
        buffer.addObserver(accountInfo);
        isLogin();
        //заполняем экран
        DockLayoutPanel mainDockLayoutPanel = new DockLayoutPanel(Style.Unit.EM);
        mainDockLayoutPanel.addEast(calendarPanel.getCalendarFlowPanel(), 11);
        mainDockLayoutPanel.addWest(menuFlowPanel, 11);
        mainDockLayoutPanel.add(centerFlowPanel);

        RootLayoutPanel rp = RootLayoutPanel.get();
        rp.add(mainDockLayoutPanel);
    }

    private void isLogin()
    {
        InfoService.Util.getService().getAccount(new MethodCallback<AccountConfirmation>() {
            @Override
            public void onFailure(Method method, Throwable throwable) {
            }
            @Override
            public void onSuccess(Method method, AccountConfirmation accountConfirmation) {
                if(!accountConfirmation.login.equals("anonymousUser")) {
                    BufferSingletone.getBuffer().setLogin(true);
                    calendarPanel.getCalendar().setVisible(true);
                    StateController.setCurrentState(CurrentState.Events);
                }
                else {
                    BufferSingletone.getBuffer().setLogin(false);
                    calendarPanel.getCalendar().setVisible(false);
                    StateController.setCurrentState(CurrentState.Login);
                }
            }
        });
    }
}
