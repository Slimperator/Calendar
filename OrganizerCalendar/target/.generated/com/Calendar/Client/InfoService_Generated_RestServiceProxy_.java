package com.Calendar.Client;

public class InfoService_Generated_RestServiceProxy_ implements com.Calendar.Client.InfoService, org.fusesource.restygwt.client.RestServiceProxy {
  private org.fusesource.restygwt.client.Resource resource = new org.fusesource.restygwt.client.Resource(org.fusesource.restygwt.client.Defaults.getServiceRoot()).resolve("/service");
  
  public void setResource(org.fusesource.restygwt.client.Resource resource) {
    this.resource = resource;
  }
  public org.fusesource.restygwt.client.Resource getResource() {
    return this.resource;
  }
  private org.fusesource.restygwt.client.Dispatcher dispatcher = org.fusesource.restygwt.client.Defaults.getDispatcher();
  
  public void setDispatcher(org.fusesource.restygwt.client.Dispatcher dispatcher) {
    this.dispatcher = dispatcher;
  }
  
  public org.fusesource.restygwt.client.Dispatcher getDispatcher() {
    return this.dispatcher;
  }
  public void getInfo(org.fusesource.restygwt.client.MethodCallback<com.Calendar.Client.OrderConfirmation> callback) {
    final org.fusesource.restygwt.client.Method __method =
    this.resource
    .resolve("/loadInfo")
    .get();
    __method.setDispatcher(this.dispatcher);
    __method.header(org.fusesource.restygwt.client.Resource.HEADER_ACCEPT, "application/json");
    __method.header(org.fusesource.restygwt.client.Resource.HEADER_CONTENT_TYPE, "application/json");
    try {
      __method.send(new org.fusesource.restygwt.client.AbstractRequestCallback<com.Calendar.Client.OrderConfirmation>(__method, callback) {
        protected com.Calendar.Client.OrderConfirmation parseResult() throws Exception {
          try {
            return com.Calendar.Client.OrderConfirmation_Generated_JsonEncoderDecoder_.INSTANCE.decode(com.google.gwt.json.client.JSONParser.parse(__method.getResponse().getText()));
          } catch (Throwable __e) {
            throw new org.fusesource.restygwt.client.ResponseFormatException("Response was NOT a valid JSON document", __e);
          }
        }
      });
    } catch (com.google.gwt.http.client.RequestException __e) {
      callback.onFailure(__method,__e);
    }
  }
}
