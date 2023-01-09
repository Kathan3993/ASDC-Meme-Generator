package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.IChangePasswordDAO;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.factory.ChangePasswordDAOFactory;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.model.IChangePassword;
import com.group5.memegenerator.model.factory.ChangePasswordFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class ForgotPasswordController {

    @GetMapping("/change-password/{email}")
    public String changePassword(@PathVariable String email, Model model) {

        model.addAttribute("Email", email);

        return "change-password";
    }

    @PostMapping("/setPassword")
    public void setNewPassword(HttpServletResponse response, String email, String password) throws IOException {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();

        IChangePasswordDAO changePasswordDAO = ChangePasswordDAOFactory.instance().makeChangePasswordDAO(databaseOperation);
        IChangePassword changePassword = ChangePasswordFactory.instance().makeChangePassword(changePasswordDAO);

        changePassword.setPassword(email, password);
            response.sendRedirect("/login");

    }

}
