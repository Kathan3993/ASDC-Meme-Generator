package com.group5.memegenerator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database implements IPersistence {

    private static String url;

    private static String user;

    private static String password;

    private static IPersistence database = null;

    public Database() {
    }

    public static IPersistence instance() {
        if (null == database) {
            return new Database();
        }
        return database;
    }

    public void setInitialValues(String urlProp, String userProp, String passwordProp) {
        url = urlProp;
        user = userProp;
        password = passwordProp;
    }

    public Connection connectToDB() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return connection;
    }
}
