package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IExploreSectionDAO;

public interface IExploreSectionDAOFactory {
    IExploreSectionDAO makeExploreSectionDAO(IDatabaseOperation databaseOperation);

    IExploreSectionDAO makeMockExploreSectionDAO();
}
