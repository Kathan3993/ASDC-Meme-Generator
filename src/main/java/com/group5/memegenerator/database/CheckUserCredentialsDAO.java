package com.group5.memegenerator.database;

import java.util.ArrayList;
import java.util.HashMap;

public class CheckUserCredentialsDAO implements ICheckUserCredentialsDAO {
    IDatabaseOperation databaseOperation;

    public CheckUserCredentialsDAO(IDatabaseOperation databaseOperation) {
        this.databaseOperation = databaseOperation;
    }

    public ArrayList<HashMap<String, String>> checkEmail(String userName) throws Exception {
        ArrayList<HashMap<String, String>> results = databaseOperation.select("email", "users", "WHERE email='" + userName + "'");
        System.out.println(userName);
        for (HashMap<String, String> result : results) {
            Object[] keys = result.keySet().toArray();
            for (Object key : keys) {
                System.out.println("FieldName:" + key);
                System.out.println("Value:" + result.get(key));
            }
        }
        return results;
    }

    public ArrayList<HashMap<String, String>> checkPassword(String password) throws Exception {
        ArrayList<HashMap<String, String>> results = databaseOperation.select("password", "users", "WHERE password=" + password + "");
        for (HashMap<String, String> result : results) {
            Object[] keys = result.keySet().toArray();
            for (Object key : keys) {
                System.out.println("FieldName:" + key);
                System.out.println("Value:" + result.get(key));
            }
        }
        return results;
    }
}
