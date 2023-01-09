package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.*;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.LoginDAOFactory;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.database.factory.UserDetailsDAOFactory;
import com.group5.memegenerator.model.ILogin;
import com.group5.memegenerator.model.IUserDetails;
import com.group5.memegenerator.model.UserDetails;
import com.group5.memegenerator.model.factory.LoginFactory;
import com.group5.memegenerator.model.factory.UserDetailsFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm(HttpServletResponse response, @CookieValue(value = "username", defaultValue = "") String username, Model model) {

        if (Objects.equals(username, "") || username == null) {
            return "login";
        } else {
            try {
                response.sendRedirect("/LoginSuccessful");
            } catch (IOException e) {

            }
        }
        return "login";
    }

    @PostMapping("/login")
    public ModelAndView login(HttpServletResponse response, String userName, String password) {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();
        ILoginDAO loginDAO = LoginDAOFactory.instance().makeLoginDAO(databaseOperation);
        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeUserDetailsDAO(databaseOperation);

        ILogin login = LoginFactory.instance().makeLogin(loginDAO);
        String results = login.login(userName, password);

        if (Objects.equals(results, "")) {

            return new ModelAndView("redirect:/login");
        }
        else {

            IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);
            String userid = userDetails.getUserIdByUserName(userName);

            userDetails = userDetails.loadUser(userid);
            Cookie cookie = new Cookie("username", results);
            Cookie cookieId = new Cookie("id", userid);
            response.addCookie(cookie);
            response.addCookie(cookieId);
            if (userDetails.getRole().equals(UserDetails.ROLE.user)) {

                return new ModelAndView("redirect:/LoginSuccessful");
            }
            return new ModelAndView("redirect:/admin-home");
        }
    }

    @GetMapping("/logout")
    public String logOut(HttpServletResponse response) {

        Cookie cookie = new Cookie("username", "");
        response.addCookie(cookie);
        cookie = new Cookie("id", "");
        response.addCookie(cookie);
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {

        }
        return "login";
    }

    @GetMapping("/LoginSuccessful")
    public ModelAndView loginSuccess() {
        return new ModelAndView("redirect:/home");
    }

}
