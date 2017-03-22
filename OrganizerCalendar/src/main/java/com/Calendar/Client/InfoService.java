package com.Calendar.Client;

import com.Calendar.Client.json.AccountConfirmation;
import com.Calendar.Client.json.EventConfirmation;
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
    public void editAccount(MethodCallback<AccountConfirmation> callback);

    @GET
    @Path("/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getAccount(MethodCallback<AccountConfirmation> callback);

    @POST
    @Path("/newAccount")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createAccount(MethodCallback<AccountConfirmation> callback);

    @DELETE
    @Path("/account")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteAccount(MethodCallback<AccountConfirmation> callback);

    @POST
    @Path("/event")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void editEvent(MethodCallback<EventConfirmation> callback);

    @GET
    @Path("/event")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getEvent(MethodCallback<EventConfirmation> callback);

    @GET
    @Path("/eventsByRange")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getEventsByRange(MethodCallback<EventConfirmation> callback);

    @POST
    @Path("/newEvent")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void createEvent(MethodCallback<EventConfirmation> callback);

    @DELETE
    @Path("/event")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteEvent(MethodCallback<EventConfirmation> callback);
}
