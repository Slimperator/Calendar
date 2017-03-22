package com.Calendar.Client.json;

import com.google.gwt.core.client.GWT;
import org.fusesource.restygwt.client.JsonEncoderDecoder;

import java.util.List;

/**
 * Created by Владимир on 20.02.2017.
 */
public class EventConfirmation {
    public String success;
    public String name;
    public String newName;
    public String description;
    public String beginDate;
    public String endDate;
    public String account;
    public List<String> events;
    /**
     * Example of how to create an instance of a JsonEncoderDecoder for a data
     * transfer object.
     */
    public interface EventConfirmationJED extends JsonEncoderDecoder<EventConfirmation> {
    }
    @Override
    public String toString() {
        if (GWT.isClient()) {
            EventConfirmationJED jed = GWT.create(EventConfirmationJED.class);
            return jed.encode(this).toString();
        }
        return super.toString();
    }
}
