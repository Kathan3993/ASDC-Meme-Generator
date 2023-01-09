package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.ChangePasswordDAO;
import com.group5.memegenerator.database.IChangePasswordDAO;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.mock.MockChangePasswordDAO;

public class ChangePasswordDAOFactory implements IChangePasswordDAOFactory {

    private static IChangePasswordDAOFactory changePasswordDAOFactory = null;

    private ChangePasswordDAOFactory() {

    }

    public static IChangePasswordDAOFactory instance() {
        if (changePasswordDAOFactory == null) {
            changePasswordDAOFactory = new ChangePasswordDAOFactory();
        }
        return changePasswordDAOFactory;
    }

    @Override
    public IChangePasswordDAO makeChangePasswordDAO(IDatabaseOperation databaseOperation) {
        return new ChangePasswordDAO(databaseOperation);
    }

    @Override
    public IChangePasswordDAO makeMockChangePasswordDAO() {
        return new MockChangePasswordDAO();
    }

}
