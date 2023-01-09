package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.database.factory.UserDetailsDAOFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class RegistrationController {
    @GetMapping("/registration")
    public String home() {
        return "registration.html";
    }

    @PostMapping("/registration")
    public String register(
                            @RequestParam(value = "firstname") String firstname,
                           @RequestParam(value = "lastname") String lastname,
                           @RequestParam(value = "gender") String gender,
                           @RequestParam(value = "dob") String dob,
                           @RequestParam(value = "email") String email,
                           @RequestParam(value = "password") String password
                        ) {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();
        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeUserDetailsDAO(databaseOperation);
        String redirection = userDetailsDAO.storeUserDetails(firstname, lastname, gender, dob, email, password);

        return "redirect:/" + redirection;
    }
}
