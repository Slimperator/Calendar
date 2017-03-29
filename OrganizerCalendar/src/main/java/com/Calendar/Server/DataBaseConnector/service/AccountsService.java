package com.calendar.server.databaseconnector.service;

import com.calendar.server.databaseconnector.entity.Accounts;

/**
 * Created by Владимир on 12.02.2017.
 */
public interface AccountsService {
    Accounts addAccount(Accounts account);
    void deleteAccount(String accountHash);
    Accounts editAccount(Accounts account);
    String getHeshPass(String accountHash);
    Accounts getAccount(String accountHash);
}
