package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.*;
import com.group5.memegenerator.database.mock.MockMemeHomeDAO;

public class MemeHomeDAOFactory implements IMemeHomeDAOFactory {
    private static IMemeHomeDAOFactory memeHomeDAOFactory;

    private MemeHomeDAOFactory() {

    }

    public static IMemeHomeDAOFactory instance() {
        if (memeHomeDAOFactory == null) {
            memeHomeDAOFactory = new MemeHomeDAOFactory();
        }
        return memeHomeDAOFactory;
    }

    @Override
    public IMemeHomeDAO makeMemeHomeDAO(IDatabaseOperation databaseOperation, ILikeDAO likeDAO) {
        return new MemeHomeDAO(databaseOperation, likeDAO);
    }

    @Override
    public IMemeHomeDAO makeMockMemeHomeDAO() {
        return new MockMemeHomeDAO();
    }

    @Override
    public IUserProfileDAO makeUserProfileDAO(IDatabaseOperation databaseOperation, ILikeDAO likeDAO) {
        return new UserProfileDAO(databaseOperation, likeDAO);
    }


}
