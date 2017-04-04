package com.calendar.client.json;

import org.fusesource.restygwt.client.JsonEncoderDecoder;

/**
 * Created by Владимир on 04.04.2017.
 */
public class LoginConfirmation {
    public String login;
    public String password;

    public interface LoginConfirmationJED extends JsonEncoderDecoder<AccountConfirmation> {
    }
}

