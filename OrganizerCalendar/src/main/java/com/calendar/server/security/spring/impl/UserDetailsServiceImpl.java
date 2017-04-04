package com.calendar.server.security.spring.impl;

import com.calendar.server.databaseconnector.entity.Accounts;
import com.calendar.server.databaseconnector.service.AccountsService;
import com.calendar.server.security.Tools;
import com.calendar.server.security.spring.entity.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Владимир on 29.03.2017.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AccountsService service;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Accounts account = service.getAccount(Tools.MD5Generator(username));
        Set<GrantedAuthority> roles = new HashSet();
        roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(account.getAccountHash(),
                        account.getPasswordHash(),
                        roles);
        return userDetails;
    }
}
