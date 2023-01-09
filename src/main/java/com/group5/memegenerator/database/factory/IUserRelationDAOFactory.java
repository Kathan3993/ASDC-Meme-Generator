package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IUserRelationDAO;

public interface IUserRelationDAOFactory {
    public IUserRelationDAO makeUserRelationDAO(IDatabaseOperation databaseOperation);

    public IUserRelationDAO makeMockUserRelationDAO();
}
