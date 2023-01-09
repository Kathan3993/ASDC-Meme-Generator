package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.*;
import com.group5.memegenerator.database.factory.*;
import com.group5.memegenerator.model.IMeme;
import com.group5.memegenerator.model.IMemeHome;
import com.group5.memegenerator.model.IUserDetails;
import com.group5.memegenerator.model.factory.MemeHomeFactory;
import com.group5.memegenerator.model.factory.UserDetailsFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MemeHomeController {

    @GetMapping("/home")
    public ModelAndView loadHomePageToUser(@CookieValue(value = "id", defaultValue = "") String id) {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
        IMemeHomeDAO memeHomeDAO = MemeHomeDAOFactory.instance().makeMemeHomeDAO(databaseOperation, likeDAO);
        IMemeHome memeHome = MemeHomeFactory.instance().makeMemeHome(memeHomeDAO);

        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeUserDetailsDAO(databaseOperation);
        IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);
        IFollowDAO followDao = FollowDAOFactory.instance().makeFollowDAO(databaseOperation);
        List<IMeme> memes = memeHome.loadMemesInHomePage(id, followDao);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("images", memes);
        modelAndView.addObject("user", userDetails);
        modelAndView.setViewName("home");

        return modelAndView;
    }
}
