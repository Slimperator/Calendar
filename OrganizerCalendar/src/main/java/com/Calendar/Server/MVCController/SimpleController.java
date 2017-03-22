package com.Calendar.Server.MVCController;

import com.Calendar.Client.json.AccountConfirmation;
import com.Calendar.Client.json.EventConfirmation;
import com.Calendar.Server.DataBaseConnector.entity.Accounts;
import com.Calendar.Server.DataBaseConnector.entity.Calendar;
import com.Calendar.Server.DataBaseConnector.service.AccountsService;
import com.Calendar.Server.DataBaseConnector.service.CalendarService;
import com.Calendar.Server.DataBaseConnector.service.impl.AccountsServiceImpl;
import com.Calendar.Server.DataBaseConnector.service.impl.CalendarServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Владимир on 05.02.2017.
 */
@Controller
public class SimpleController {
    private AccountsService accountsService = new AccountsServiceImpl();
    private CalendarService calendarService = new CalendarServiceImpl();

    @RequestMapping(value = "/newAccount", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    AccountConfirmation newAccountRequest(HttpServletRequest request,
                                        HttpServletResponse response) throws ServletException, IOException {
        AccountConfirmation confirmation = new AccountConfirmation();
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
    AccountConfirmation deleteAccountRequest(HttpServletRequest request,
                                           HttpServletResponse response) throws ServletException, IOException {
        AccountConfirmation confirmation = new AccountConfirmation();
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
    AccountConfirmation editAccountRequest(HttpServletRequest request,
                                         HttpServletResponse response) throws ServletException, IOException {
        AccountConfirmation confirmation = new AccountConfirmation();
        try {
            Accounts account = accountsService.getAccount(com.Calendar.Server.Security.Tools.MD5Generator(confirmation.login));
            account.setPasswordHash(com.Calendar.Server.Security.Tools.MD5Generator(confirmation.newPassword));
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
    AccountConfirmation getAccountRequest(HttpServletRequest request,
                                          HttpServletResponse response) throws ServletException, IOException {
        AccountConfirmation confirmation = new AccountConfirmation();
        try {
            Accounts account = accountsService.getAccount(com.Calendar.Server.Security.Tools.MD5Generator(confirmation.login));
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
    EventConfirmation createEventRequest(HttpServletRequest request,
                                         HttpServletResponse response) throws ServletException, IOException {
        EventConfirmation confirmation = new EventConfirmation();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Accounts account = accountsService.getAccount(com.Calendar.Server.Security.Tools.MD5Generator(confirmation.account));
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
    EventConfirmation editEventRequest(HttpServletRequest request,
                                       HttpServletResponse response) throws ServletException, IOException {
        EventConfirmation confirmation = new EventConfirmation();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Accounts account = accountsService.getAccount(com.Calendar.Server.Security.Tools.MD5Generator(confirmation.account));
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
    EventConfirmation getEventByDateRequest(HttpServletRequest request,
                                            HttpServletResponse response) throws ServletException, IOException {
        EventConfirmation confirmation = new EventConfirmation();
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
    EventConfirmation deleteEventRequest(HttpServletRequest request,
                                         HttpServletResponse response) throws ServletException, IOException {
        EventConfirmation confirmation = new EventConfirmation();
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
    EventConfirmation getEvent(HttpServletRequest request,
                                            HttpServletResponse response) throws ServletException, IOException {
        EventConfirmation confirmation = new EventConfirmation();
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
