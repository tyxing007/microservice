package com.petrovsky.ssta.security.service;

import com.petrovsky.ssta.client.UserClientResource;
import com.petrovsky.ssta.model.User;
import com.petrovsky.ssta.security.model.UserDetailsImpl;
import com.petrovsky.ssta.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserClientResource userClientResource;

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) {
//        User user = userClientResource.findByName(email);
        User user = null;
        try {
            user = userService.getUserByEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) throw new UsernameNotFoundException("No user found for name '" + email +"'.");
        return new UserDetailsImpl(user);
    }

//    @Transactional
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        com.petrovsky.ssta.model.User user = null;
//        user = userClientResource.findByName(login);
//        if (user == null){
//            return null;
//        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getAuthorities().toString()));
//        return new User(String.valueOf(user.getId()), user.getPassword(),authorities);
//    }
}
