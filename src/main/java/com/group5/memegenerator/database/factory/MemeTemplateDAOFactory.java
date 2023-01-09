package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IMemeTemplateDAO;
import com.group5.memegenerator.database.MemeTemplateDAO;
import com.group5.memegenerator.database.mock.MockMemeTemplateDAO;

public class MemeTemplateDAOFactory implements IMemeTemplateDAOFactory {
    private static IMemeTemplateDAOFactory memeTemplateDAOFactory = null;

    private MemeTemplateDAOFactory() {
    }

    public static IMemeTemplateDAOFactory instance() {
        if (memeTemplateDAOFactory == null) {
            memeTemplateDAOFactory = new MemeTemplateDAOFactory();
        }
        return memeTemplateDAOFactory;
    }

    @Override
    public IMemeTemplateDAO makeMemeTemplateDAO(IDatabaseOperation databaseOperation) {
        return new MemeTemplateDAO(databaseOperation);
    }

    @Override
    public IMemeTemplateDAO makeMockMemeTemplateDAO() {
        return new MockMemeTemplateDAO();
    }
}
