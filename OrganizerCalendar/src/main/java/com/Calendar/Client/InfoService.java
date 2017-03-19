package com.Calendar.Client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
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
    @Path("/session")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void getInfo(MethodCallback<OrderConfirmation> callback);

    @GET
    @Path("/session")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void truegetInfo(MethodCallback<OrderConfirmation> callback);

    @PUT
    @Path("/session")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void putInfo(MethodCallback<OrderConfirmation> callback);

    @DELETE
    @Path("/session")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteInfo(MethodCallback<OrderConfirmation> callback);
}
