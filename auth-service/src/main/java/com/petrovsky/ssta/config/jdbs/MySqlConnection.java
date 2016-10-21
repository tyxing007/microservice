package com.petrovsky.ssta.config.jdbs;

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
    @Value("${jdbc.driver}")
    private String driver;

    private Connection connect = null;
    private Statement statement = null;

    private void connection() throws Exception {
        Class.forName(driver);
        connect = DriverManager.getConnection(String.format(urlConnect, username, password));
        statement = connect.createStatement();
    }

    private void closeConnection() throws SQLException {
        connect.close();
    }

    public User getUserByEmail(String email) throws Exception {
        connection();
        ResultSet resultSet = statement.executeQuery(String.format(DbQueries.SELECT_USER_BY_EMAIL, email));
        return writeResultSet(resultSet);
    }

    private User writeResultSet(ResultSet resultSet) throws SQLException {
        Long id = null;
        String email = null;
        String passwordUser = null;
        while (resultSet.next()) {
            id = resultSet.getLong("id");
            email = resultSet.getString("email");
            passwordUser = resultSet.getString("password");
        }
        User user = new User(id, email, passwordUser);
        closeConnection();
        return user;
    }
}
