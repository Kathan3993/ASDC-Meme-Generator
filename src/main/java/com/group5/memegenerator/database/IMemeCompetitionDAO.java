package com.group5.memegenerator.database;

import com.group5.memegenerator.model.IMeme;
import com.group5.memegenerator.model.IMemeCompetition;
import com.group5.memegenerator.model.MemeCompetition;

import java.util.ArrayList;
import java.util.List;

public interface IMemeCompetitionDAO {

    public DatabaseResponse createCompetition(String winnerId, String winnerMemeId, String competitionName,
            String competitionCategory, String startDate);

    public DatabaseResponse addMemesToCompetition(String memeId, String competitionId);

    public List<IMeme> getMemesByCompetitionId(String competitionId) throws Exception;

    public DatabaseResponse voteOnMeme(String memeId, String competitionId);

    public IMeme getWinnerByCompetitionId(String competitionId) throws Exception;

    public String getCompetitionStartDate(String competitionId);

    public String getLatestCompetition();

    public String getCompetitionNameByCompetitionId(String competitionId);

    public List<IMemeCompetition> getAllCompetitions();
}
