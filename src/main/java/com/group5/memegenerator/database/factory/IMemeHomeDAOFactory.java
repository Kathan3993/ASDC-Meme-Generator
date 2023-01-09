package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.IMemeHomeDAO;
import com.group5.memegenerator.database.IUserProfileDAO;

public interface IMemeHomeDAOFactory {
    IMemeHomeDAO makeMemeHomeDAO(IDatabaseOperation databaseOperation, ILikeDAO likeDAO);

    IMemeHomeDAO makeMockMemeHomeDAO();

    IUserProfileDAO makeUserProfileDAO(IDatabaseOperation databaseOperation, ILikeDAO likeDAO);

}
