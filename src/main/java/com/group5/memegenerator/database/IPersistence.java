package com.group5.memegenerator.database;

import java.sql.Connection;

public interface IPersistence {
    public Connection connectToDB();

    public void setInitialValues(String url, String user, String password);
}
