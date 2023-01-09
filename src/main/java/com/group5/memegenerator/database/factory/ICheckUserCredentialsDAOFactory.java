package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.ICheckUserCredentialsDAO;
import com.group5.memegenerator.database.IDatabaseOperation;

public interface ICheckUserCredentialsDAOFactory {

    ICheckUserCredentialsDAO makeCheckUserCredentialsDAO(IDatabaseOperation databaseOperation);

    ICheckUserCredentialsDAO makeMockCheckUserCredentialsDAO();
}
