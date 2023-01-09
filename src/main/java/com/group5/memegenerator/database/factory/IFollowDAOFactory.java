package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IFollowDAO;

public interface IFollowDAOFactory {
    public IFollowDAO makeFollowDAO(IDatabaseOperation databaseOperation);

    public IFollowDAO makeMockFollowDAO();
}
