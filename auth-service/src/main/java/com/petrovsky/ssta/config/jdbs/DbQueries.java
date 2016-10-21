package com.petrovsky.ssta.config.jdbs;

public interface DbQueries {
    String SELECT_USER_BY_EMAIL = "SELECT * FROM user WHERE email = '%1$s'";

}
