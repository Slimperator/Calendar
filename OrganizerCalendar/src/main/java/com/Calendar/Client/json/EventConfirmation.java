package com.calendar.client.json;

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
    /**
     * Example of how to create an instance of a JsonEncoderDecoder for a data
     * transfer object.
     */
    /*@Override
    public String toString() {
        return success + "," + name + "," + newName + "," + description + "," + beginDate + endDate + account + events;
    }

    /**
     * Example of how to create an instance of a JsonEncoderDecoder for a data
     * transfer object.


    public static AccountConfirmation fromString(String string)
    {
        AccountConfirmation ac = null;
        try {
            ac = new AccountConfirmation();
            String[] split = string.split(",");
            ac.success = split[0];
            ac.name = split[1];
            ac.login = split[2];
            ac.newPassword = split[3];
            ac.someOtherShitAboutYou = split[4];
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("wrong str format");
        }
        finally {
            return ac;
        }
    }*/

    public interface EventConfirmationJED extends JsonEncoderDecoder<EventConfirmation> {
    }
}
