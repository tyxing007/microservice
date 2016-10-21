package com.petrovsky.ssta.model;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

public class UserDetailsImpl extends User implements UserDetails {

    public UserDetailsImpl(User user) {
        super(user);
    }

    @Override public String getUsername() {
        return super.getEmail();
    }
    @Override public String getPassword() {
        return super.getPassword();
    }
    @Override public boolean isAccountNonExpired() {return true;}
    @Override public boolean isAccountNonLocked() {return true;}
    @Override public boolean isCredentialsNonExpired() {return true;}
    @Override public boolean isEnabled() {
        return super.isEnabled();
    }
    @Override public Set<Authority> getAuthorities() {
        return super.getAuthorities();
    }
}
