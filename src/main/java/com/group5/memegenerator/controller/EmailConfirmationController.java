package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.ICheckUserCredentialsDAO;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.factory.CheckUserCredentialsDAOFactory;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.model.ICheckUserCredentials;
import com.group5.memegenerator.model.IEmail;
import com.group5.memegenerator.model.factory.CheckUserCredentialFactory;
import com.group5.memegenerator.model.factory.EmailFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Controller
public class EmailConfirmationController {

    @GetMapping("/email-confirmation")
    public String readEmail() {
        return "email-confirmation";
    }

    @PostMapping("/email-confirmation")
    public String setPasswordForm(String email, HttpServletRequest request) {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();
        ICheckUserCredentialsDAO checkUserCredentialsDAO = CheckUserCredentialsDAOFactory.instance().makeCheckUserCredentialsDAO(databaseOperation);

        ICheckUserCredentials checkUserCredentials = CheckUserCredentialFactory.instance().makeCheckCredentials(checkUserCredentialsDAO);
        boolean emailCheck = checkUserCredentials.checkEmail(email);

        if (emailCheck) {

            IEmail iEmail = EmailFactory.instance().makeEmail();

            String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
                    .replacePath(null)
                    .build()
                    .toUriString();

            String url = baseUrl + "/change-password/" + email;
            String content = "<a href='" + url + "'>" + url + "</a>";
            iEmail.sendEmail(email, "forgot", content);

            String login = "login";
            return login;
        }
        else {

            String emailConfirm = "email-confirmation";
            return emailConfirm;
        }
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "email-confirmation";
    }
}