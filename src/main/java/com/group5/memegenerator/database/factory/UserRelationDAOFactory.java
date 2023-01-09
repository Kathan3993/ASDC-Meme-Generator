package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IUserRelationDAO;
import com.group5.memegenerator.database.UserRelationDAO;
import com.group5.memegenerator.database.mock.MockUserRelationDAO;

public class UserRelationDAOFactory implements IUserRelationDAOFactory {
    private static IUserRelationDAOFactory userRelationDAOFactory = null;

    private UserRelationDAOFactory() {
    }

    public static IUserRelationDAOFactory instance() {
        if (userRelationDAOFactory == null) {
            userRelationDAOFactory = new UserRelationDAOFactory();
        }
        return userRelationDAOFactory;
    }

    @Override
    public IUserRelationDAO makeUserRelationDAO(IDatabaseOperation databaseOperation) {
        return new UserRelationDAO(databaseOperation);
    }

    @Override
    public IUserRelationDAO makeMockUserRelationDAO() {
        return new MockUserRelationDAO();
    }
}
