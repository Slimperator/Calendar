package com.calendar.client;

import com.calendar.client.json.AccountConfirmation;
import com.calendar.client.json.EventConfirmation;
import com.calendar.client.json.LoginConfirmation;
import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by Владимир on 20.02.2017.
 */
@Path("/service")
public interface InfoService extends RestService {
    public static class Util {
        private static InfoService instance;
        public static InfoService getService() {
            if (instance == null) {
                instance = GWT.create(InfoService.class);
            }
            Resource resource = new Resource(GWT.getModuleBaseURL() + "service");
            ((RestServiceProxy) instance).setResource(resource);
            return instance;
        }
    }
    @POST
    @Path("/auth/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void editAccount(AccountConfirmation request, MethodCallback<AccountConfirmation> callback);

    //check!
    @GET
    @Path("/auth/account/{request}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getAccount(@PathParam("request") String login, MethodCallback<AccountConfirmation> callback);

    @POST
    @Path("/auth/newAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createAccount(AccountConfirmation request, MethodCallback<AccountConfirmation> callback);

    @DELETE
    @Path("/auth/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAccount(AccountConfirmation request, MethodCallback<AccountConfirmation> callback);

    @POST
    @Path("/auth/event")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void editEvent(EventConfirmation request, MethodCallback<EventConfirmation> callback);

    @GET
    @Path("/auth/event/{eventName}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getEvent(@PathParam("eventName") String eventName, MethodCallback<EventConfirmation> callback);

    @GET
    @Path("/auth/eventsByRange")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getEventsByRange(EventConfirmation request, MethodCallback<List<EventConfirmation>> callback);

    @POST
    @Path("/auth/newEvent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createEvent(EventConfirmation request, MethodCallback<EventConfirmation> callback);

    @DELETE
    @Path("/auth/event")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteEvent(String eventName, MethodCallback<EventConfirmation> callback);

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void login(LoginConfirmation loginConfirmation, MethodCallback<String> callback);
}
