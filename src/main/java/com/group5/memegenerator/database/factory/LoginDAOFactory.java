package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.ILoginDAO;
import com.group5.memegenerator.database.LoginDAO;
import com.group5.memegenerator.database.mock.MockLoginDAO;

public class LoginDAOFactory implements ILoginDAOFactory {
    private static ILoginDAOFactory loginDAOFactory = null;

    private LoginDAOFactory() {
    }

    public static ILoginDAOFactory instance() {
        if (loginDAOFactory == null) {
            loginDAOFactory = new LoginDAOFactory();
        }
        return loginDAOFactory;
    }

    @Override
    public ILoginDAO makeLoginDAO(IDatabaseOperation databaseOperation) {
        return new LoginDAO(databaseOperation);
    }

    @Override
    public ILoginDAO makeMockLoginDAO() {
        return new MockLoginDAO();
    }
}
