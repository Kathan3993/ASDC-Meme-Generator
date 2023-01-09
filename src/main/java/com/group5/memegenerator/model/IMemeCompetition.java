package com.group5.memegenerator.model;

import com.group5.memegenerator.database.DatabaseResponse;

import java.util.List;

public interface IMemeCompetition {
    public DatabaseResponse startCompetition(String winnerId, String winnerMemeId, String competitionName,
            String competitionCategory) ;

    public DatabaseResponse addMemesToCompetition(String memeId, String competitionId);

    public List<IMeme> getMemesByCompetitionId(String competitionId);

    public List<IMeme> compete(String competitionId);

    public DatabaseResponse voteOnMeme(String memeId, String competitionId);

    public IMeme getWinnerByCompetitionId(String competitionId) throws Exception;

    public boolean isCompetitionOver(String competitionId);

    public String getLatestCompetition();

    public String getCompetitionNameByCompetitionId(String competitionId);

    public List<IMemeCompetition> getAllCompetitions();

    String getWinnerId();
    String getWinnerMemeId();
}
