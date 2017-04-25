package com.calendar.client.json;

import org.fusesource.restygwt.client.JsonEncoderDecoder;

/**
 * Created by Владимир on 21.03.2017.
 */
public class AccountConfirmation {
    public String success;
    public String name;
    public String login;
    public String newPassword;
    public String someOtherShitAboutYou;

   /* @Override
    public String toString() {
        return success + "," + name + "," + login + "," + newPassword + "," + someOtherShitAboutYou;
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
    public interface AccountConfirmationJED extends JsonEncoderDecoder<AccountConfirmation> {
    }
}
