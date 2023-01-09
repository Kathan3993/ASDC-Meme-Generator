package com.group5.memegenerator.database;

import com.group5.memegenerator.model.ILike;
import com.group5.memegenerator.model.IMeme;
import com.group5.memegenerator.model.IMemeCompetition;
import com.group5.memegenerator.model.MemeCompetition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class MemeCompetitionDAO implements IMemeCompetitionDAO {
    private IDatabaseOperation databaseOperation;
    private ILike like;

    public MemeCompetitionDAO(IDatabaseOperation dbo, ILike like) {
        this.databaseOperation = dbo;
        this.like = like;
    }

    public DatabaseResponse createCompetition(String winnerId, String winnerMemeId, String competitionName,
            String competitionCategory, String startDate) {
        String fields = "winner_id,winner_meme_id,competition_name,competition_category,start_date";
        String values = "'" + winnerId + "','" + winnerMemeId + "','" + competitionName + "','" + competitionCategory
                + "','" + startDate + "'";
        DatabaseResponse result;
        try {
            result = databaseOperation.insert("meme_competition", fields, values);
        } catch (Exception exception) {
            return DatabaseResponse.ERROR;
        }
        return result;

    }

    public DatabaseResponse addMemesToCompetition(String memeId, String competitionId) {

        ArrayList<HashMap<String, String>> response = new ArrayList<>();
        try {
            response = databaseOperation.select("*", "competition_submissions",
                    " WHERE competition_id='" + competitionId + "' AND meme_id='" + memeId + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.size() > 0) {
            return DatabaseResponse.MEME_ALREADY_EXISTS;
        }
        String fields = "competition_id,meme_id";
        String values = "'" + competitionId + "','" + memeId + "'";
        DatabaseResponse result;
        try {
            result = databaseOperation.insert("competition_submissions", fields, values);
        } catch (Exception exception) {
            return DatabaseResponse.ERROR;
        }
        return result;
    }

    public List<IMeme> getMemesByCompetitionId(String competitionId) throws Exception {
        List<HashMap<String, String>> results = new ArrayList<>();
        try {
            results = databaseOperation.select("*", "competition_submissions",
                    " WHERE competition_id='" + competitionId + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Iterator<HashMap<String, String>> resIter = results.iterator();
        List<String> memeIds = new ArrayList<>();
        while (resIter.hasNext()) {
            HashMap<String, String> result = resIter.next();
            memeIds.add(result.get("meme_id"));
        }
        IMemeDAO memeDao = new MemeDAO(databaseOperation);
        ArrayList<IMeme> memes = new ArrayList<>();

        Iterator<String> iter = memeIds.iterator();
        while (iter.hasNext()) {
            IMeme meme = memeDao.loadMemeById(iter.next(), null);
            memes.add(meme);
        }
        return memes;
    }

    public DatabaseResponse voteOnMeme(String memeId, String competitionId) {
        String fields = "points=points+1";
        String conditions = "meme_id='" + memeId + "' AND competition_id='" + competitionId + "'";
        DatabaseResponse result;
        try {
            result = databaseOperation.update("competition_submissions", fields, conditions);
        } catch (Exception exception) {
            return DatabaseResponse.ERROR;
        }
        return result;

    }

    public IMeme getWinnerByCompetitionId(String competitionId) throws Exception {
        List<HashMap<String, String>> results = new ArrayList<>();
        try {
            results = databaseOperation.select("*", "competition_submissions",
                    " WHERE competition_id='" + competitionId + "' ORDER BY points DESC LIMIT 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String memeId = "";
        Iterator<HashMap<String, String>> iter = results.iterator();
        while (iter.hasNext()) {
            HashMap<String, String> result = iter.next();
            memeId = result.get("meme_id");
        }
        IMemeDAO memeDao = new MemeDAO(databaseOperation);
        return memeDao.loadMemeById(memeId, null);
    }

    public String getCompetitionStartDate(String competitionId) {
        List<HashMap<String, String>> results = new ArrayList<>();
        String fields = "*";
        try {
            results = databaseOperation.select(fields, "meme_competition",
                    " WHERE id='" + competitionId + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String startDate = "";
        Iterator<HashMap<String, String>> iter = results.iterator();
        while (iter.hasNext()) {
            HashMap<String, String> result = iter.next();
            startDate = result.get("start_date");
        }

        return startDate;
    }

    @Override
    public String getLatestCompetition() {
        List<HashMap<String, String>> results = new ArrayList<>();
        String fields = "*";
        try {
            results = databaseOperation.select(fields, "meme_competition",
                    " ORDER BY id DESC LIMIT 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
        String competitionId = "";
        Iterator<HashMap<String, String>> iter = results.iterator();
        while (iter.hasNext()) {
            HashMap<String, String> result = iter.next();
            competitionId = result.get("id").toString();
        }
        return competitionId;
    }

    public String getCompetitionNameByCompetitionId(String competitionId) {
        List<HashMap<String, String>> results = new ArrayList<>();
        String fields = "*";
        try {
            results = databaseOperation.select(fields, "meme_competition",
                    " WHERE id=" + competitionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Iterator<HashMap<String, String>> iter = results.iterator();
        String competitionName = "";
        while (iter.hasNext()) {
            HashMap<String, String> result = iter.next();
            competitionName = result.get("competition_name");
        }
        return competitionName;
    }

    public List<IMemeCompetition> getAllCompetitions() {
        String fields = "*";
        List<HashMap<String, String>> results = databaseOperation.select(fields, "meme_competition", "");
        Iterator<HashMap<String, String>> iter = results.iterator();
        List<IMemeCompetition> memeCompetitions = new ArrayList<>();
        while (iter.hasNext()) {
            HashMap<String, String> result = iter.next();
            String competitionName = result.get("competition_name");
            String competitionId = result.get("id");
            String winnerId = result.get("winner_id");
            String winnerMemeId = result.get("winner_meme_id");
            String competitionCategory = result.get("competition_category");
            String competitionStartDate = result.get("start_date");
            IMeme meme = null;
            try {
                meme = getWinnerByCompetitionId(competitionId);
            } catch (Exception e) {
                meme = null;
            }
            winnerId = meme.getUserId();
            winnerMemeId = meme.getMemeId();
            IMemeCompetition memeCompetition = new MemeCompetition(competitionId, winnerId, winnerMemeId,
                    competitionName, competitionCategory, competitionStartDate);
            memeCompetitions.add(memeCompetition);
        }
        return memeCompetitions;
    }
}