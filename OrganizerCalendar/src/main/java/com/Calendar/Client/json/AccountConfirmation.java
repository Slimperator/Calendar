package com.calendar.client.json;

import com.google.gwt.core.client.GWT;
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
    /**
     * Example of how to create an instance of a JsonEncoderDecoder for a data
     * transfer object.
     */
    public interface AccountConfirmationJED extends JsonEncoderDecoder<AccountConfirmation> {
    }
    @Override
    public String toString() {
        if (GWT.isClient()) {
            AccountConfirmationJED jed = GWT.create(AccountConfirmationJED.class);
            return jed.encode(this).toString();
        }
        return super.toString();
    }
}
