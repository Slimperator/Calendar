package com.calendar.server.security.spring.impl;

/**
 * Created by Владимир on 04.04.2017.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        if (userDetailsService.loadUserByUsername(username) == null)
            throw new UsernameNotFoundException("User not found");
        UserDetails store = userDetailsService.loadUserByUsername(username);
        if (!store.getPassword().equals(password))
            throw new BadCredentialsException("invalid password");
        Authentication customAuthentication = new CustomUserAuthentication(
                "ROLE_USER", authentication);
        customAuthentication.setAuthenticated(true);
        return customAuthentication;
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
