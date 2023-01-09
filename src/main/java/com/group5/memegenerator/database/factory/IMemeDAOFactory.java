package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.ICommentsDAO;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.IMemeDAO;

public interface IMemeDAOFactory {

    IMemeDAO makeMemeDAO(IDatabaseOperation databaseOperation);

    ILikeDAO makeLikeDAO(IDatabaseOperation databaseOperation);

    ICommentsDAO makeCommentsDAO(IDatabaseOperation databaseOperation);

    IMemeDAO makeMockMemeDAO();

    ILikeDAO makeMockLikeDAO();

    ICommentsDAO makeMockCommentsDAO();

}
