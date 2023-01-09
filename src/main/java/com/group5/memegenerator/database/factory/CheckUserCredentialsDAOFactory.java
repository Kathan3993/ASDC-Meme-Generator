package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.CheckUserCredentialsDAO;
import com.group5.memegenerator.database.ICheckUserCredentialsDAO;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.mock.MockCheckUserCredentialsDAO;

public class CheckUserCredentialsDAOFactory implements ICheckUserCredentialsDAOFactory {
    private static ICheckUserCredentialsDAOFactory checkCredentialsDAOFactory = null;

    private CheckUserCredentialsDAOFactory() {

    }

    public static ICheckUserCredentialsDAOFactory instance() {
        if (checkCredentialsDAOFactory == null) {
            checkCredentialsDAOFactory = new CheckUserCredentialsDAOFactory();
        }
        return checkCredentialsDAOFactory;
    }

    @Override
    public ICheckUserCredentialsDAO makeCheckUserCredentialsDAO(IDatabaseOperation databaseOperation) {
        return new CheckUserCredentialsDAO(databaseOperation);
    }

    @Override
    public ICheckUserCredentialsDAO makeMockCheckUserCredentialsDAO(){
        return new MockCheckUserCredentialsDAO();
    }
}
