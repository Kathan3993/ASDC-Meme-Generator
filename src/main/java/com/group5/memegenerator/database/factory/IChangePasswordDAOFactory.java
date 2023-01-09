package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IChangePasswordDAO;
import com.group5.memegenerator.database.IDatabaseOperation;

public interface IChangePasswordDAOFactory {
    IChangePasswordDAO makeChangePasswordDAO(IDatabaseOperation databaseOperation);

    IChangePasswordDAO makeMockChangePasswordDAO();
}
