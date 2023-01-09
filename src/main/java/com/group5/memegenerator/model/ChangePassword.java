package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IChangePasswordDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class ChangePassword implements IChangePassword {

    IChangePasswordDAO changePasswordDAO;

    public ChangePassword(IChangePasswordDAO changePasswordDAO) {
        this.changePasswordDAO = changePasswordDAO;
    }

    @Override
    public void setPassword(String username, String password){

        PasswordEncoder bCryptEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptEncoder.encode(password);
        try {
            changePasswordDAO.setPassword(username, encodedPassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
