package com.group5.memegenerator.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class LoginDAO implements ILoginDAO {

    IDatabaseOperation databaseOperation;

    public LoginDAO(IDatabaseOperation databaseOperation) {
        this.databaseOperation = databaseOperation;
    }

    public ArrayList<HashMap<String, String>> login( String userName, String password) {

        try {

            ArrayList<HashMap<String, String>> results = databaseOperation.select("email,password", "users", "WHERE email='" + userName + "'");

            return results;
        }
        catch (Exception e) {
            return null;
        }

    }
}
