package com.group5.memegenerator.model;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordEncryption implements IPasswordEncryption {

    @Override
    public String encryptPassword(String password) {

        PasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        return bCryptEncoder.encode(password);
    }

    @Override
    public boolean isPasswordAuthenticated(String userProvided, String passwordInDatabase) {

        if (passwordInDatabase == null) {
            return false;
        }
        return BCrypt.checkpw(userProvided, passwordInDatabase);
    }
}
