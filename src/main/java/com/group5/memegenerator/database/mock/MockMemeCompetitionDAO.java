package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IMemeCompetitionDAO;
import com.group5.memegenerator.model.IMeme;
import com.group5.memegenerator.model.IMemeCompetition;
import com.group5.memegenerator.model.MemeCompetition;

import java.util.ArrayList;
import java.util.List;

public class MockMemeCompetitionDAO implements IMemeCompetitionDAO {

    public DatabaseResponse createCompetition(String winnerId, String winnerMemeId, String competitionName,
                                              String competitionCategory, String startDate) {
        if (winnerId == "" || winnerMemeId == "" || competitionName == "" || competitionCategory == "") {
            return DatabaseResponse.ERROR;
        } else {
            return DatabaseResponse.SUCCESS;
        }
    }

    public DatabaseResponse addMemesToCompetition(String memeId, String competitionId) {
        if (memeId == "" || competitionId == "") {
            return DatabaseResponse.ERROR;
        } else {
            return DatabaseResponse.SUCCESS;
        }
    }

    public List<IMeme> getMemesByCompetitionId(String competitionId) {
        if (competitionId == "1") {
            return generateDummyMemes();
        }
        return new ArrayList<IMeme>();
    }

    public List<IMeme> generateDummyMemes() {
        List<IMeme> memes = new ArrayList<>();
        MockMemeDAO memeDAOMock = new MockMemeDAO();
        for (int i = 1; i <= 5; i++) {
            try {
                IMeme meme = memeDAOMock.loadMemeById("" + i);
                memes.add(meme);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return memes;
    }

    public DatabaseResponse voteOnMeme(String memeId, String competitionId) {
        if (memeId == "" || memeId == "0") {
            return DatabaseResponse.ERROR;
        }
        if (competitionId == "" || competitionId == "0") {
            return DatabaseResponse.ERROR;
        }
        return DatabaseResponse.SUCCESS;
    }

    public IMeme getWinnerByCompetitionId(String competitionId) {
        MockMemeDAO memeDAOMock = new MockMemeDAO();
        IMeme meme = null;
        try {
            meme = memeDAOMock.loadMemeById(competitionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return meme;
    }

    public String getCompetitionStartDate(String competitionId) {
        return "2022-07-10 10:46:59";
    }

    public String getLatestCompetition() {
        return "1";
    }

    public String getCompetitionNameByCompetitionId(String competitionId) {
        if (competitionId == "1") {
            return "Test Competition";
        } else {
            return "Test Competition " + competitionId;
        }
    }

    public List<IMemeCompetition> getAllCompetitions() {
        List<IMemeCompetition> competitions = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            competitions.add(generateDummyMemeCompetition("" + i));
        }
        return competitions;
    }

    public IMemeCompetition generateDummyMemeCompetition(String competitionId) {
        return new MemeCompetition(competitionId, "winnerId", "winnerMemeId",
                "competition Number: " + competitionId,
                "competitionCategory", getCompetitionStartDate(competitionId));
    }

}
