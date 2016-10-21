package com.petrovsky.ssta.service;

import com.petrovsky.ssta.model.User;
import com.petrovsky.ssta.model.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) throw new UsernameNotFoundException("No user found for name '" + email +"'.");
        return new UserDetailsImpl(user);
    }

}
