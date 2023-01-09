package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.ILoginDAO;

public interface ILoginDAOFactory {

    ILoginDAO makeLoginDAO(IDatabaseOperation databaseOperation);

    ILoginDAO makeMockLoginDAO();
}
