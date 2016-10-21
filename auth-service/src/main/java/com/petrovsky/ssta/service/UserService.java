package com.petrovsky.ssta.service;

import com.petrovsky.ssta.config.MySqlConnection;
import com.petrovsky.ssta.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private MySqlConnection mySqlConn;

    public User getUserByEmail(String email) throws Exception {
        return mySqlConn.getUserById(email);
    }

}
