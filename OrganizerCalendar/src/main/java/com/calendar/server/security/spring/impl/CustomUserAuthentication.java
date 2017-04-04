package com.calendar.server.security.spring.impl;

/**
 * Created by Владимир on 04.04.2017.
 */
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

public class CustomUserAuthentication implements Authentication {
    private static final long serialVersionUID = 2835775828631400069L;
    private boolean authenticated;
    private GrantedAuthority grantedAuthority;
    private Authentication authentication;

    public CustomUserAuthentication(String role, Authentication authentication){
        this.grantedAuthority = new SimpleGrantedAuthority(role);
        this.authentication = authentication;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(grantedAuthority);
        return authorities;
    }

    @Override
    public Object getCredentials() {
        return authentication.getCredentials();
    }

    @Override
    public Object getDetails() {
        return authentication.getDetails();
    }

    @Override
    public Object getPrincipal() {
        return authentication.getPrincipal();
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) throws IllegalArgumentException {
        this.authenticated = authenticated;
    }
}
