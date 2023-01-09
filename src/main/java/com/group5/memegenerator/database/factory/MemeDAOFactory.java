package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.*;
import com.group5.memegenerator.database.mock.MockCommentsDAO;
import com.group5.memegenerator.database.mock.MockLikeDAO;
import com.group5.memegenerator.database.mock.MockMemeDAO;

public class MemeDAOFactory implements IMemeDAOFactory {

    private static IMemeDAOFactory memeDAOFactory;

    private MemeDAOFactory() {

    }

    public static IMemeDAOFactory instance() {
        if (memeDAOFactory == null) {
            memeDAOFactory = new MemeDAOFactory();
        }
        return memeDAOFactory;
    }

    @Override
    public IMemeDAO makeMemeDAO(IDatabaseOperation databaseOperation) {
        return new MemeDAO(databaseOperation);
    }

    @Override
    public ILikeDAO makeLikeDAO(IDatabaseOperation databaseOperation) {
        return new LikeDAO(databaseOperation);
    }

    @Override
    public ICommentsDAO makeCommentsDAO(IDatabaseOperation databaseOperation) {
        return new CommentsDAO(databaseOperation);
    }

    @Override
    public IMemeDAO makeMockMemeDAO() {
        return new MockMemeDAO();
    }

    @Override
    public ILikeDAO makeMockLikeDAO() {
        return new MockLikeDAO();
    }

    @Override
    public ICommentsDAO makeMockCommentsDAO() {
        return new MockCommentsDAO();
    }
}
