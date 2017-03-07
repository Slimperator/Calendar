package com.Calendar.Client;

public class OrderConfirmation_Generated_JsonEncoderDecoder_ extends org.fusesource.restygwt.client.AbstractJsonEncoderDecoder<com.Calendar.Client.OrderConfirmation> {
  
  public static final OrderConfirmation_Generated_JsonEncoderDecoder_ INSTANCE = new OrderConfirmation_Generated_JsonEncoderDecoder_();
  
  public com.google.gwt.json.client.JSONValue encode(com.Calendar.Client.OrderConfirmation value) {
    if( value==null ) {
      return null;
    }
    com.google.gwt.json.client.JSONObject rc = new com.google.gwt.json.client.JSONObject();
    com.Calendar.Client.OrderConfirmation parseValue = (com.Calendar.Client.OrderConfirmation)value;
    {
      com.google.gwt.json.client.JSONValue v=org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.encode(parseValue.message);
      if( v!=null ) {
        rc.put("message", v);
      }
    }
    {
      com.google.gwt.json.client.JSONValue v=org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.LONG.encode(parseValue.ready_time);
      if( v!=null ) {
        rc.put("ready_time", v);
      }
    }
    return rc;
  }
  
  public OrderConfirmation decode(com.google.gwt.json.client.JSONValue value) {
    com.google.gwt.json.client.JSONObject object = toObject(value);
    com.Calendar.Client.OrderConfirmation rc = new com.Calendar.Client.OrderConfirmation();
    if(object.get("message") != null) {
      if(object.get("message") instanceof com.google.gwt.json.client.JSONNull) {
        rc.message=null;
      } else {
        rc.message=org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.STRING.decode(object.get("message"));
      }
    }
    if(object.get("ready_time") != null) {
      if(object.get("ready_time") instanceof com.google.gwt.json.client.JSONNull) {
        rc.ready_time=null;
      } else {
        rc.ready_time=org.fusesource.restygwt.client.AbstractJsonEncoderDecoder.LONG.decode(object.get("ready_time"));
      }
    }
    return rc;
  }
  
}
