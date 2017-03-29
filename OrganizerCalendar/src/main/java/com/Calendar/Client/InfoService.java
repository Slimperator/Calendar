package com.calendar.client;

import com.calendar.client.json.AccountConfirmation;
import com.calendar.client.json.EventConfirmation;
import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Resource;
import org.fusesource.restygwt.client.RestService;
import org.fusesource.restygwt.client.RestServiceProxy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

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
    @Path("/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void editAccount(AccountConfirmation request, MethodCallback<AccountConfirmation> callback);

    @GET
    @Path("/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getAccount(AccountConfirmation request, MethodCallback<AccountConfirmation> callback);

    @POST
    @Path("/newAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createAccount(AccountConfirmation request, MethodCallback<AccountConfirmation> callback);

    @DELETE
    @Path("/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAccount(AccountConfirmation request, MethodCallback<AccountConfirmation> callback);

    @POST
    @Path("/event")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void editEvent(EventConfirmation request, MethodCallback<EventConfirmation> callback);

    @GET
    @Path("/event")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getEvent(EventConfirmation request, MethodCallback<EventConfirmation> callback);

    @GET
    @Path("/eventsByRange")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getEventsByRange(EventConfirmation request, MethodCallback<EventConfirmation> callback);

    @POST
    @Path("/newEvent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createEvent(EventConfirmation request, MethodCallback<EventConfirmation> callback);

    @DELETE
    @Path("/event")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteEvent(EventConfirmation request, MethodCallback<EventConfirmation> callback);
}
