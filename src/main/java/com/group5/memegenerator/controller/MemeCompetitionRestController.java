package com.group5.memegenerator.controller;

import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.IMemeCompetitionDAO;
import com.group5.memegenerator.database.MemeCompetitionDAO;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.MemeCompetitionDAOFactory;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.model.ILike;
import com.group5.memegenerator.model.IMemeCompetition;
import com.group5.memegenerator.model.MemeCompetition;
import com.group5.memegenerator.model.factory.MemeCompetitionFactory;
import com.group5.memegenerator.model.factory.MemeFactory;

@RestController
public class MemeCompetitionRestController {

        @GetMapping("/admin/api/start-competition")
        public DatabaseResponse startCompetition(@CookieValue(value = "username", defaultValue = "") String username,
                        String memeCategory, String competitionName) {
                IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                                .makeDataBaseOperation();

                ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
                ILike like = MemeFactory.instance().makeLike(likeDAO);

                IMemeCompetitionDAO memeCompetitionDao = MemeCompetitionDAOFactory.instance()
                                .makeMemeCompetitionDAO(databaseOperation, like);
                IMemeCompetition memeCompetition = MemeCompetitionFactory.instance()
                                .makeMemeCompetition(memeCompetitionDao);
                return memeCompetition.startCompetition("", "", competitionName, memeCategory);
        }

        @PostMapping(value = "/api/create-competition")
        public DatabaseResponse createCompetition(String competitionName, String competitionCategory) {
                IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                                .makeDataBaseOperation();

                ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
                ILike like = MemeFactory.instance().makeLike(likeDAO);

                IMemeCompetitionDAO memeCompetitionDao = MemeCompetitionDAOFactory.instance()
                                .makeMemeCompetitionDAO(databaseOperation, like);
                IMemeCompetition memeCompetition = MemeCompetitionFactory.instance()
                                .makeMemeCompetition(memeCompetitionDao);
                return memeCompetition.startCompetition("", "", competitionName, competitionCategory);
        }

        @PostMapping(value = "/api/add-meme-in-competition")
        public DatabaseResponse addMemeToCompetition(String memeId) {
                IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                                .makeDataBaseOperation();

                ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
                ILike like = MemeFactory.instance().makeLike(likeDAO);

                IMemeCompetitionDAO memeCompetitionDao = MemeCompetitionDAOFactory.instance()
                                .makeMemeCompetitionDAO(databaseOperation, like);
                IMemeCompetition memeCompetition = MemeCompetitionFactory.instance()
                                .makeMemeCompetition(memeCompetitionDao);
                String competitionId = memeCompetition.getLatestCompetition();
                return memeCompetition.addMemesToCompetition(memeId, competitionId);
        }

        @PostMapping(value = "/api/vote-meme")
        public DatabaseResponse voteMeme(String memeId) {
                IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                                .makeDataBaseOperation();

                ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
                ILike like = MemeFactory.instance().makeLike(likeDAO);

                IMemeCompetitionDAO memeCompetitionDao = MemeCompetitionDAOFactory.instance()
                                .makeMemeCompetitionDAO(databaseOperation, like);
                IMemeCompetition memeCompetition = MemeCompetitionFactory.instance()
                                .makeMemeCompetition(memeCompetitionDao);
                String competitionId = memeCompetition.getLatestCompetition();
                return memeCompetition.voteOnMeme(memeId, competitionId);
        }
}
