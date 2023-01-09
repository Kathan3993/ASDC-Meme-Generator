package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.IChangePasswordDAO;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.factory.ChangePasswordDAOFactory;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.model.IChangePassword;
import com.group5.memegenerator.model.factory.ChangePasswordFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserSettingsController {

    @GetMapping("/user-settings")
    public void userSettings(HttpServletResponse response) {
        try {
            response.sendRedirect("/settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/settings")
    public String settings() {
        return "user-settings";
    }

    @GetMapping("/settings-update-password")
    public void settingsUpdatePassword(HttpServletResponse response) throws IOException {
            response.sendRedirect("/update-password");
    }

    @GetMapping("/update-password")
    public String update() {
        return "settings-update-password";
    }

    @PostMapping("/update-password")
    public void setNewPassword(HttpServletResponse response, String username, String password) throws IOException {
            IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();
            IChangePasswordDAO changePasswordDAO = ChangePasswordDAOFactory.instance().makeChangePasswordDAO(databaseOperation);
            IChangePassword iChangePassword = ChangePasswordFactory.instance().makeChangePassword(changePasswordDAO);
            iChangePassword.setPassword(username, password);
            response.sendRedirect("/user-settings");

    }

}
