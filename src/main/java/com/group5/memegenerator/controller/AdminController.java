package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.database.factory.UserDetailsDAOFactory;
import com.group5.memegenerator.model.IUserDetails;
import com.group5.memegenerator.model.UserDetails.ROLE;
import com.group5.memegenerator.model.factory.UserDetailsFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminController {
    @GetMapping(value = "/admin-home")
    public ModelAndView adminHome(@CookieValue(name = "id") String id) {
        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeUserDetailsDAO(databaseOperation);
        IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);
        IUserDetails user = userDetails.loadUser(id);
        if (user.getRole().equals(ROLE.admin)) {
            return new ModelAndView("admin-home");
        } else {
            return new ModelAndView("redirect:/");
        }
    }
}
