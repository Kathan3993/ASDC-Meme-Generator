package com.group5.memegenerator.model;

import com.group5.memegenerator.database.ILoginDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashMap;

public class Login implements ILogin {

    ILoginDAO userDao;

    public Login(ILoginDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public String login(String userName, String password) {

        PasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();

        ArrayList<HashMap<String, String>> results = null;
        try {
            results = userDao.login(userName, password);
        } catch (Exception e) {
            return null;
        }

        if (results == null) {
            return "";
        }

        HashMap<String, String> idPass = results.get(0);

        String Id = idPass.get("email");
        String passwordEncode = idPass.get("password");

        if (bCryptEncoder.matches(password, passwordEncode)) {
            return userName;
        }
        return "";
    }
}
