package com.petrovsky.ssta.config;

import com.petrovsky.ssta.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class MySqlConnection {

    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.url}")
    private String urlConnect;

    private Connection connect = null;
    private Statement statement = null;

    private void connection() throws Exception {
        System.err.println("testt" + urlConnect);
        Class.forName("com.mysql.cj.jdbc.Driver");
        connect = DriverManager.getConnection(String.format(urlConnect, username, password));
        statement = connect.createStatement();
    }

    private void closeConnection() throws SQLException {
        connect.close();
    }

    public User getUserById(String email) throws Exception {
        connection();
        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM user WHERE email", email));
        return writeResultSet(resultSet);
    }

    private User writeResultSet(ResultSet resultSet) throws SQLException {
        User user = new User(
                resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("password"));
        closeConnection();
        return user;
    }
}
