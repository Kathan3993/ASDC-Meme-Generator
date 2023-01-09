package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.FollowDAO;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IFollowDAO;
import com.group5.memegenerator.database.mock.MockFollowDAO;

public class FollowDAOFactory implements IFollowDAOFactory {
    private static IFollowDAOFactory followDAOFactory = null;

    private FollowDAOFactory() {

    }

    public static IFollowDAOFactory instance() {

        if (followDAOFactory == null) {
            followDAOFactory = new FollowDAOFactory();
        }
        return followDAOFactory;
    }

    @Override
    public IFollowDAO makeFollowDAO(IDatabaseOperation databaseOperation) {

        return new FollowDAO(databaseOperation);
    }

    @Override
    public IFollowDAO makeMockFollowDAO() {
        return new MockFollowDAO();
    }

}
