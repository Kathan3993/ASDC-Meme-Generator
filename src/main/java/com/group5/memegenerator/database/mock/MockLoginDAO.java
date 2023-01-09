package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.ILoginDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;

public class MockLoginDAO implements ILoginDAO {
    @Override
    public ArrayList<HashMap<String, String>> login(String userName, String password) {

        if(userName == null || password == null) {
            return null;
        }

        PasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptEncoder.encode(password);

        ArrayList<HashMap<String, String>> result = new ArrayList<>();
        HashMap<String, String> user = new HashMap<>();

        user.put("email", userName);
        user.put("password", encodedPassword);

        result.add(user);

        return result;
    }
}
