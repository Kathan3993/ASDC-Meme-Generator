package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IMemeCompetitionDAO;
import com.group5.memegenerator.model.IMemeCompetition;

public interface IMemeCompetitionFactory {
    public IMemeCompetition makeMemeCompetition(IMemeCompetitionDAO memeCompetitionDAO);
}
