package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.*;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.ExploreSectionDAOFactory;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.model.*;
import com.group5.memegenerator.model.factory.ExploreSectionFactory;
import com.group5.memegenerator.model.factory.MemeFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ExploreSectionController {

    @GetMapping("/explore-section")
    public ModelAndView loadExploreSection(@CookieValue(value = "id", defaultValue = "6") String userid) {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();

        IExploreSectionDAO exploreSectionDAO = ExploreSectionDAOFactory.instance()
                .makeExploreSectionDAO(databaseOperation);
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);

        ILike like = MemeFactory.instance().makeLike(likeDAO);
        IExploreSection exploreSection = ExploreSectionFactory.instance().makeExploreSection(exploreSectionDAO, like);

        IFollowRankDAO followRankDAO = new FollowRankDAO(databaseOperation);
        IMemeRank memeRank = new MemeRank(userid, followRankDAO);

        List<IMeme> mostLikedMemes = exploreSection.loadMotLikedMemesFromDatabase();

        List<IMeme> mostLikedRecentMemes = exploreSection.loadMostLikedRecentMemesFromDatabase();

        mostLikedRecentMemes = memeRank.getMemeRank(mostLikedRecentMemes);

        List<IMeme> topRankedMemes = exploreSection.loadTopRankedMemesFromDatabase();
        topRankedMemes = memeRank.getMemeRank(topRankedMemes);
        if (mostLikedMemes == null && mostLikedRecentMemes == null && topRankedMemes == null) {

            ModelAndView modelAndView = new ModelAndView();

            modelAndView.setViewName("server-down");
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("mostLikedMemes", mostLikedMemes);
        modelAndView.addObject("mostLikedRecentMemes", mostLikedRecentMemes);
        modelAndView.addObject("topRankedMemes", topRankedMemes);
        modelAndView.addObject("userId", userid);

        modelAndView.setViewName("explore-section");
        return modelAndView;
    }
}
