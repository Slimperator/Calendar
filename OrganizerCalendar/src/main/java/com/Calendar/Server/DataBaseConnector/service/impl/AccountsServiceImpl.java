package com.calendar.server.databaseconnector.service.impl;

import com.calendar.server.databaseconnector.entity.Accounts;
import com.calendar.server.databaseconnector.repository.AccountsRepository;
import com.calendar.server.databaseconnector.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by Владимир on 12.02.2017.
 */
@Service
public class AccountsServiceImpl implements AccountsService {
    @Autowired
    private AccountsRepository accountsRepository;

    @Override
    public Accounts addAccount(Accounts account) {
        Accounts saveAccount = accountsRepository.saveAndFlush(account);
        return saveAccount;
    }

    @Override
    public void deleteAccount(String accountName) {
        accountsRepository.deleteByAccountHash(accountName);
        accountsRepository.flush();
    }

    @Override
    public Accounts editAccount(Accounts account) {
        return accountsRepository.saveAndFlush(account);
    }

    @Override
    public String getHeshPass(String accountName) {
        Accounts account = accountsRepository.findeByAccountName(accountName);
        return account.getPasswordHash();
    }

    @Override
    public Accounts getAccount(String accountName) {
        return accountsRepository.findeByAccountName(accountName);
    }
}
