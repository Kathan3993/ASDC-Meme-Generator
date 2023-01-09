package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.ICheckUserCredentialsDAO;

import java.util.ArrayList;
import java.util.HashMap;

public class MockCheckUserCredentialsDAO implements ICheckUserCredentialsDAO {

    @Override
    public ArrayList<HashMap<String, String>> checkEmail(String userName) throws Exception {

        if(userName.equals("vidya")){

            ArrayList<HashMap<String, String>> results = new ArrayList<>();
            HashMap<String, String> user = new HashMap<>();

            user.put("Email", userName);

            results.add(user);

            return results;
        }

        return null;
    }

    @Override
    public ArrayList<HashMap<String, String>> checkPassword(String password) throws Exception {

        if(password.equals("vidhut")){

            ArrayList<HashMap<String, String>> results = new ArrayList<>();
            HashMap<String, String> user = new HashMap<>();

            user.put("Password", password);

            results.add(user);

            return results;
        }

        return null;
    }
}
