package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IMemeCompetitionDAO;
import com.group5.memegenerator.model.ILike;

public interface IMemeCompetitionDAOFactory {
    public IMemeCompetitionDAO makeMemeCompetitionDAO(IDatabaseOperation databaseOperation, ILike like);

    public IMemeCompetitionDAO makeMockMemeCompetitionDAO();
}
