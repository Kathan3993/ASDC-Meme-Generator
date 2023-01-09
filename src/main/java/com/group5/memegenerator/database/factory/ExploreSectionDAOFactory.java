package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.ExploreSectionDAO;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IExploreSectionDAO;
import com.group5.memegenerator.database.mock.MockExploreSectionDAO;

public class ExploreSectionDAOFactory implements IExploreSectionDAOFactory {

    private static IExploreSectionDAOFactory exploreSectionDAOFactory = null;

    private ExploreSectionDAOFactory() {

    }

    public static IExploreSectionDAOFactory instance() {

        if (exploreSectionDAOFactory == null) {
            exploreSectionDAOFactory = new ExploreSectionDAOFactory();
        }
        return exploreSectionDAOFactory;
    }

    @Override
    public IExploreSectionDAO makeExploreSectionDAO(IDatabaseOperation databaseOperation) {

        return new ExploreSectionDAO(databaseOperation);
    }

    @Override
    public IExploreSectionDAO makeMockExploreSectionDAO() {
        return new MockExploreSectionDAO();
    }
}
