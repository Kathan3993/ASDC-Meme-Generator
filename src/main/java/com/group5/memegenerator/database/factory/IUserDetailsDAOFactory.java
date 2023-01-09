package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.model.Like;

public interface IUserDetailsDAOFactory {

    IUserDetailsDAO makeUserDetailsDAO(IDatabaseOperation databaseOperation);

    IUserDetailsDAO makeMockUserDetailsDAOFactory();
}
