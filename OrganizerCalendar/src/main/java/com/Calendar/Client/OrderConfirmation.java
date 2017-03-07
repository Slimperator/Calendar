package com.Calendar.Client;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.JsonEncoderDecoder;

/**
 * Created by Владимир on 20.02.2017.
 */
public class OrderConfirmation {
    public String message;
    public Long ready_time;
    /**
     * Example of how to create an instance of a JsonEncoderDecoder for a data
     * transfer object.
     */
    public interface OrderConfirmationJED extends JsonEncoderDecoder<OrderConfirmation> {
    }
    @Override
    public String toString() {
        if (GWT.isClient()) {
            OrderConfirmationJED jed = GWT.create(OrderConfirmationJED.class);
            return jed.encode(this).toString();
        }
        return super.toString();
    }
}