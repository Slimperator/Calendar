package com.calendar.server.controllers;

import com.calendar.client.json.AccountConfirmation;
import com.calendar.client.json.EventConfirmation;
import com.calendar.client.json.LoginConfirmation;
import com.calendar.server.databaseconnector.entity.Accounts;
import com.calendar.server.databaseconnector.entity.Calendar;
import com.calendar.server.databaseconnector.service.AccountsService;
import com.calendar.server.databaseconnector.service.CalendarService;
import com.calendar.server.databaseconnector.service.impl.AccountsServiceImpl;
import com.calendar.server.databaseconnector.service.impl.CalendarServiceImpl;
import com.calendar.server.security.spring.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.ws.rs.PathParam;
import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Владимир on 05.02.2017.
 */
@Controller
public class CalendarController {
    @Autowired
    private AccountsService accountsService;
    @Autowired
    private CalendarService calendarService;
    //check!
    @RequestMapping()
    public String showMeTheLoginPage(Model model) {
        return "redirect:/login.jsp";
    }
    @RequestMapping(value = "/newAccount", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    AccountConfirmation newAccountRequest(@RequestBody AccountConfirmation request) throws ServletException, IOException {
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
    //Check!
    @RequestMapping(value = "/auth/account", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public @ResponseBody
    AccountConfirmation deleteAccountRequest(@RequestBody String login) throws ServletException, IOException {
        AccountConfirmation confirmation = new AccountConfirmation();
        confirmation.login = (String) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
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
    //Check!
    @RequestMapping(value = "/auth/account", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    AccountConfirmation editAccountRequest(@RequestBody AccountConfirmation request) throws ServletException, IOException {
        AccountConfirmation confirmation = request;
        try {
            Accounts account = accountsService.getAccount((String) SecurityContextHolder.getContext()
                    .getAuthentication().getPrincipal());
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
    //FAIL!
    @RequestMapping(value = "/auth/account", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    AccountConfirmation getAccountRequest() throws ServletException, IOException {
        AccountConfirmation confirmation = new AccountConfirmation();
        confirmation.login = (String) SecurityContextHolder.getContext()
                .getAuthentication().getName();
        try {
            Accounts account = accountsService.getAccount(confirmation.login);
            confirmation.success = "ok";
        }
        catch (Exception e){
            confirmation.success = "false";
        }
        finally {
            return confirmation;
        }
    }
    //Check!
    @RequestMapping(value = "/auth/newEvent", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    EventConfirmation createEventRequest(@RequestBody EventConfirmation request) throws ServletException, IOException {
        EventConfirmation confirmation = request;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Accounts account = accountsService.getAccount(SecurityContextHolder.getContext().getAuthentication().getName());
            calendarService.addEvent(new Calendar(
                    confirmation.name,
                    confirmation.description,
                    new java.sql.Date(confirmation.beginDate.getTime()),
                    new java.sql.Date(confirmation.endDate.getTime()),
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
    //Check!
    @RequestMapping(value = "/auth/event", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    EventConfirmation editEventRequest(@RequestBody EventConfirmation request) throws ServletException, IOException {
        EventConfirmation confirmation = request;
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Accounts account = accountsService.getAccount(SecurityContextHolder.getContext().getAuthentication().getName());
            Calendar event = calendarService.getEvent(confirmation.name);
            event.setName(confirmation.newName);
            event.setDescription(confirmation.description);
            event.setBegin_data(new java.sql.Date(confirmation.beginDate.getTime()));
            event.setEnd_data(new java.sql.Date(confirmation.endDate.getTime()));
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
    //Add search for account
    @RequestMapping(value = "/auth/eventsByRange", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    List<EventConfirmation> getEventByDateRequest(EventConfirmation request) throws ServletException, IOException {
        EventConfirmation confirmation = request;
        List<EventConfirmation> response = new ArrayList<EventConfirmation>();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmm");
            List<Calendar> events = calendarService.getEventsByRange(new java.sql.Date(confirmation.beginDate.getTime()),
                    new java.sql.Date(confirmation.endDate.getTime()),
                    SecurityContextHolder.getContext().getAuthentication().getName());
            for(Calendar event: events)
            {
                EventConfirmation element = new EventConfirmation();
                element.description = event.getDescription();
                element.beginDate = event.getBegin_data();
                element.endDate = event.getEnd_data();
                element.name = event.getName();
                response.add(element);
            }
        }
        catch (Exception e){
            confirmation.success = "false";
        }
        finally {
            return response;
        }
    }
    //Check!
    @RequestMapping(value = "/auth/event", method = RequestMethod.DELETE, headers = "Accept=application/json")
    public @ResponseBody
    EventConfirmation deleteEventRequest(@RequestBody String eventName) throws ServletException, IOException {
        EventConfirmation confirmation = new EventConfirmation();
        confirmation.name = eventName;
        try {
            calendarService.deleteEvent(calendarService.getEvent(confirmation.name),
                    SecurityContextHolder.getContext().getAuthentication().getName());
            confirmation.success = "ok";
        }
        catch (Exception e){
            confirmation.success = "false";
        }
        finally {
            return confirmation;
        }
    }
    //Somthing wrong
    @RequestMapping(value = "/auth/event/{eventName}", method = RequestMethod.GET, headers = "Accept=application/json")
    public @ResponseBody
    EventConfirmation getEvent(@RequestParam("eventName") String eventName) throws ServletException, IOException {
        EventConfirmation confirmation = new EventConfirmation();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
            Calendar event = calendarService.getEvent(eventName);
            confirmation.description = event.getDescription();
            confirmation.beginDate = event.getBegin_data();
            confirmation.endDate = event.getEnd_data();
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

    @RequestMapping(value = "/logout", method = RequestMethod.POST, headers = "Accept=application/json")
    public @ResponseBody
    String authorisationOn(@RequestBody LoginConfirmation lg) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        if (authentication == null) {
            System.out.println("Not logged in");
            return null;
        } else {
            return (String) authentication.getPrincipal();
        }
    }
}
