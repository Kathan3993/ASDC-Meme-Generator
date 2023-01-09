package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.ICommentsDAO;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.IMemeCompetitionDAO;
import com.group5.memegenerator.database.IMemeDAO;
import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.MemeCompetitionDAOFactory;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.database.factory.UserDetailsDAOFactory;
import com.group5.memegenerator.model.IComments;
import com.group5.memegenerator.model.ILike;
import com.group5.memegenerator.model.IMeme;
import com.group5.memegenerator.model.IMemeCompetition;
import com.group5.memegenerator.model.IUserDetails;
import com.group5.memegenerator.model.UserDetails.ROLE;
import com.group5.memegenerator.model.factory.MemeCompetitionFactory;
import com.group5.memegenerator.model.factory.MemeFactory;
import com.group5.memegenerator.model.factory.UserDetailsFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemeCompetitionController {
        @GetMapping("/meme-competition")
        public ModelAndView playMemeCompetition(@CookieValue(value = "username", defaultValue = "") String username) {
                IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                                .makeDataBaseOperation();

                ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
                ILike like = MemeFactory.instance().makeLike(likeDAO);

                IMemeCompetitionDAO memeCompetitionDao = MemeCompetitionDAOFactory.instance()
                                .makeMemeCompetitionDAO(databaseOperation, like);
                IMemeCompetition memeCompetition = MemeCompetitionFactory.instance()
                                .makeMemeCompetition(memeCompetitionDao);
                String competitionId = memeCompetition.getLatestCompetition();
                boolean isCompetitionOver = memeCompetition.isCompetitionOver(competitionId);
                if (isCompetitionOver) {
                        return new ModelAndView("no-competition");
                }
                String competitionName = memeCompetition.getCompetitionNameByCompetitionId(competitionId);
                List<IMeme> twoMemes = memeCompetition.compete(competitionId);
                ModelAndView model = new ModelAndView("meme-competition");
                model.addObject("memes", twoMemes);
                model.addObject("username", username);
                model.addObject("competitionName", competitionName);
                return model;
        }

        @GetMapping("/create-competition")
        public ModelAndView createCompetition(@CookieValue(value = "id", defaultValue = "") String id) {
                IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                                .makeDataBaseOperation();
                ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
                IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeUserDetailsDAO(databaseOperation);
                IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);
                IUserDetails user = userDetails.loadUser(id);
                if (user.getRole().equals(ROLE.admin)) {
                        return new ModelAndView("create-competition");
                } else {
                        return new ModelAndView("redirect:/");
                }
        }

        @GetMapping("/competitions")
        public ModelAndView viewCompetitions(@CookieValue(value = "username", defaultValue = "") String username) {
                IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                                .makeDataBaseOperation();

                ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
                ILike like = MemeFactory.instance().makeLike(likeDAO);

                IMemeCompetitionDAO memeCompetitionDao = MemeCompetitionDAOFactory.instance()
                                .makeMemeCompetitionDAO(databaseOperation, like);
                IMemeCompetition memeCompetition = MemeCompetitionFactory.instance()
                                .makeMemeCompetition(memeCompetitionDao);
                List<IMemeCompetition> memeCompetitions = memeCompetition.getAllCompetitions();

                IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeUserDetailsDAO(databaseOperation);
                IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);
                IMemeDAO memeDAO = MemeDAOFactory.instance().makeMemeDAO(databaseOperation);
                IMeme memes = MemeFactory.instance().makeMeme(memeDAO, like);
                ICommentsDAO commentsDAO = MemeDAOFactory.instance().makeCommentsDAO(databaseOperation);
                IComments comments = MemeFactory.instance().makeComment(commentsDAO);
                ModelAndView mv = new ModelAndView("pastCompetitions");
                mv.addObject("memeCompetitions", memeCompetitions);

                List<String> winnerMemes = new ArrayList<>();
                List<String> names = new ArrayList<>();
                for (IMemeCompetition competition : memeCompetitions) {
                        IUserDetails detail = userDetails.loadUser(competition.getWinnerId());
                        names.add(detail.getFirstname());
                        IMeme meme = memes.loadMeme(competition.getWinnerMemeId(), comments);
                        winnerMemes.add(meme.getMemePicture());
                }
                mv.addObject("names", names);
                mv.addObject("winnerMemes", winnerMemes);
                return mv;
        }
}
