package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IMemeCompetitionDAO;
import com.group5.memegenerator.database.MemeCompetitionDAO;
import com.group5.memegenerator.database.mock.MockMemeCompetitionDAO;
import com.group5.memegenerator.model.ILike;

public class MemeCompetitionDAOFactory implements IMemeCompetitionDAOFactory {
    private static IMemeCompetitionDAOFactory memeCompetitionDAOFactory = null;

    private MemeCompetitionDAOFactory() {

    }

    public static IMemeCompetitionDAOFactory instance() {

        if (memeCompetitionDAOFactory == null) {
            memeCompetitionDAOFactory = new MemeCompetitionDAOFactory();
        }
        return memeCompetitionDAOFactory;
    }

    @Override
    public IMemeCompetitionDAO makeMemeCompetitionDAO(IDatabaseOperation databaseOperation, ILike like) {

        return new MemeCompetitionDAO(databaseOperation, like);
    }

    @Override
    public IMemeCompetitionDAO makeMockMemeCompetitionDAO() {
        return new MockMemeCompetitionDAO();
    }
}
