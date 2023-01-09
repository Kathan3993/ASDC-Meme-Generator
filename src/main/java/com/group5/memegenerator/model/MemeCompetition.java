package com.group5.memegenerator.model;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IMemeCompetitionDAO;
import lombok.Getter;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MemeCompetition implements IMemeCompetition {
    @Getter
    private String competitionId;
    @Getter
    private String winnerId;
    @Getter
    private String winnerMemeId;

    @Getter
    private String competitionName;
    @Getter
    private String competitionCategory;
    @Getter
    private String competitionStartDate;
    @Getter
    private Connection connection;
    @Getter
    private IMemeCompetitionDAO memeCompetitionDAO;

    public MemeCompetition(IMemeCompetitionDAO memeCompetitionDao) {
        this.memeCompetitionDAO = memeCompetitionDao;
    }

    public MemeCompetition(String competitionId, String winnerId, String winnerMemeId, String competitionName,
            String competitionCategory, String competitionStartDate) {
        this.competitionId = competitionId;
        this.winnerId = winnerId;
        this.winnerMemeId = winnerMemeId;
        this.competitionName = competitionName;
        this.competitionCategory = competitionCategory;
        this.competitionStartDate = competitionStartDate;
    }

    public DatabaseResponse startCompetition(String winnerId, String winnerMemeId, String competitionName,
            String competitionCategory) {
        DatabaseResponse result = DatabaseResponse.ERROR;

        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);

        String startDate = simpleDateFormat.format(new Date());
        result = memeCompetitionDAO.createCompetition(winnerId, winnerMemeId,
                competitionName, competitionCategory,
                startDate);
        return result;

    }

    public DatabaseResponse addMemesToCompetition(String memeId, String competitionId) {
        DatabaseResponse result = DatabaseResponse.ERROR;
        try {
            result = memeCompetitionDAO.addMemesToCompetition(memeId, competitionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<IMeme> getMemesByCompetitionId(String competitionId) {
        List<IMeme> memes = new ArrayList<>();
        try {
            memes = memeCompetitionDAO.getMemesByCompetitionId(competitionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memes;
    }

    public List<IMeme> compete(String competitionId) {
        List<IMeme> memes = getMemesByCompetitionId(competitionId);
        int maxRange = memes.size() - 1;
        int minRange = 0;
        int randomRange = maxRange - minRange + 1;
        int meme1 = 0, meme2 = 0;
        do {
            meme1 = minRange + (int) (Math.random() * randomRange);
            meme2 = minRange + (int) (Math.random() * randomRange);
        } while (meme1 == meme2);
        ArrayList<IMeme> competingMemes = new ArrayList<>();
        competingMemes.add(memes.get(meme1));
        competingMemes.add(memes.get(meme2));
        return competingMemes;
    }

    public DatabaseResponse voteOnMeme(String memeId, String competitionId) {
        return memeCompetitionDAO.voteOnMeme(memeId, competitionId);
    }

    public IMeme getWinnerByCompetitionId(String competitionId) throws Exception {
        if (isCompetitionOver(competitionId)) {
            IMeme meme = memeCompetitionDAO.getWinnerByCompetitionId(competitionId);
            return meme;
        } else {
            return null;
        }
    }

    public boolean isCompetitionOver(String competitionId) {
        String startDate = memeCompetitionDAO.getCompetitionStartDate(competitionId);
        Calendar currentDate = Calendar.getInstance();
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, Locale.ENGLISH);
        try {
            String newDate = simpleDateFormat.format(new Date());
            currentDate.setTime(simpleDateFormat.parse(newDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar prevDate = Calendar.getInstance();
        try {
            prevDate.setTime(simpleDateFormat.parse(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long firstDateTime = prevDate.getTimeInMillis();
        long secondDateTime = currentDate.getTimeInMillis();
        long difference = TimeUnit.MILLISECONDS.toDays(Math.abs(secondDateTime - firstDateTime));

        if (difference >= 1) {
            return true;
        } else {
            return false;
        }
    }

    public String getLatestCompetition() {
        return memeCompetitionDAO.getLatestCompetition();
    }

    public String getCompetitionNameByCompetitionId(String competitionId) {
        return memeCompetitionDAO.getCompetitionNameByCompetitionId(competitionId);
    }

    public List<IMemeCompetition> getAllCompetitions() {
        List<IMemeCompetition> memeCompetitions = new ArrayList<>();
        memeCompetitions = memeCompetitionDAO.getAllCompetitions();
        return memeCompetitions;
    }
}