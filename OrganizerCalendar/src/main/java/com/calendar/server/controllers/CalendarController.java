package com.calendar.server.controllers;

import com.calendar.client.json.AccountConfirmation;
import com.calendar.client.json.EventConfirmation;
import com.calendar.server.databaseconnector.entity.Accounts;
import com.calendar.server.databaseconnector.entity.Calendar;
import com.calendar.server.databaseconnector.service.AccountsService;
import com.calendar.server.databaseconnector.service.CalendarService;
import com.calendar.server.databaseconnector.service.impl.AccountsServiceImpl;
import com.calendar.server.databaseconnector.service.impl.CalendarServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Владимир on 05.02.2017.
 */
@Controller
public class CalendarController {
    private AccountsService accountsService = new AccountsServiceImpl();
    private CalendarService calendarService = new CalendarServiceImpl();

    @RequestMapping(value = "/newAccount", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    AccountConfirmation newAccountRequest(AccountConfirmation request) throws ServletException, IOException {
        AccountConfirmation confirmation = request;
        try {
            accountsService.addAccount(new Accounts(confirmation.login, confirmation.newPassword));
            confirmation.success = "ok";
        }
        catch (Exception e){
            confirmation.success = "false";
        }
        finally {
            return confirmation;
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public @ResponseBody
    AccountConfirmation deleteAccountRequest(AccountConfirmation request) throws ServletException, IOException {
        AccountConfirmation confirmation = request;
        try {
            accountsService.deleteAccount(confirmation.login);
            confirmation.success = "ok";
        }
        catch (Exception e){
            confirmation.success = "false";
        }
        finally {
            return confirmation;
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    AccountConfirmation editAccountRequest(AccountConfirmation request) throws ServletException, IOException {
        AccountConfirmation confirmation = request;
        try {
            Accounts account = accountsService.getAccount(com.calendar.server.security.Tools.MD5Generator(confirmation.login));
            account.setPasswordHash(com.calendar.server.security.Tools.MD5Generator(confirmation.newPassword));
            accountsService.editAccount(account);
            confirmation.success = "ok";
        }
        catch (Exception e){
            confirmation.success = "false";
        }
        finally {
            return confirmation;
        }
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    AccountConfirmation getAccountRequest(AccountConfirmation request) throws ServletException, IOException {
        AccountConfirmation confirmation = request;
        try {
            Accounts account = accountsService.getAccount(com.calendar.server.security.Tools.MD5Generator(confirmation.login));
            confirmation.success = "ok";
        }
        catch (Exception e){
            confirmation.success = "false";
        }
        finally {
            return confirmation;
        }
    }

    @RequestMapping(value = "/newEvent", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    EventConfirmation createEventRequest(EventConfirmation request) throws ServletException, IOException {
        EventConfirmation confirmation = request;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Accounts account = accountsService.getAccount(com.calendar.server.security.Tools.MD5Generator(confirmation.account));
            calendarService.addEvent(new Calendar(
                    confirmation.name,
                    confirmation.description,
                    new java.sql.Date(df.parse(confirmation.beginDate).getTime()),
                    new java.sql.Date(df.parse(confirmation.endDate).getTime()),
                    account
            ));
            confirmation.success = "ok";
        }
        catch (Exception e){
            confirmation.success = "false";
        }
        finally {
            return confirmation;
        }
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    EventConfirmation editEventRequest(EventConfirmation request) throws ServletException, IOException {
        EventConfirmation confirmation = request;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Accounts account = accountsService.getAccount(com.calendar.server.security.Tools.MD5Generator(confirmation.account));
            Calendar event = calendarService.getEvent(confirmation.name);
            event.setName(confirmation.newName);
            event.setDescription(confirmation.description);
            event.setBegin_data(new java.sql.Date(df.parse(confirmation.beginDate).getTime()));
            event.setEnd_data(new java.sql.Date(df.parse(confirmation.endDate).getTime()));
            calendarService.editEvent(event);
            confirmation.success = "ok";
        }
        catch (Exception e){
            confirmation.success = "false";
        }
        finally {
            return confirmation;
        }
    }

    @RequestMapping(value = "/eventsByRange", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    EventConfirmation getEventByDateRequest(EventConfirmation request) throws ServletException, IOException {
        EventConfirmation confirmation = request;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            List<Calendar> events = calendarService.getEventsByRange(new java.sql.Date(df.parse(confirmation.beginDate).getTime()),
                    new java.sql.Date(df.parse(confirmation.endDate).getTime()));
            for(Calendar calendar: events)
            {   //Настроить тустринг и продумать схему хранения на клиенте
                confirmation.events.add(calendar.toString());
            }
            confirmation.success = "ok";
        }
        catch (Exception e){
            confirmation.success = "false";
        }
        finally {
            return confirmation;
        }
    }

    @RequestMapping(value = "/event", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public @ResponseBody
    EventConfirmation deleteEventRequest(EventConfirmation request) throws ServletException, IOException {
        EventConfirmation confirmation = request;
        try {
            calendarService.deleteEvent(calendarService.getEvent(confirmation.name));
            confirmation.success = "ok";
        }
        catch (Exception e){
            confirmation.success = "false";
        }
        finally {
            return confirmation;
        }
    }

    @RequestMapping(value = "/event", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    EventConfirmation getEvent(EventConfirmation request) throws ServletException, IOException {
        EventConfirmation confirmation = request;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Calendar event = calendarService.getEvent(confirmation.name);
            confirmation.description = event.getDescription();
            confirmation.beginDate = df.format(event.getBegin_data());
            confirmation.endDate = df.format(event.getEnd_data());
            confirmation.name = event.getName();
            confirmation.success = "ok";
        }
        catch (Exception e){
            confirmation.success = "false";
        }
        finally {
            return confirmation;
        }
    }
}
