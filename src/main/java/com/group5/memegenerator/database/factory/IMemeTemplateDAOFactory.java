package com.group5.memegenerator.database.factory;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IMemeTemplateDAO;

public interface IMemeTemplateDAOFactory {
    IMemeTemplateDAO makeMemeTemplateDAO(IDatabaseOperation databaseOperation);

    IMemeTemplateDAO makeMockMemeTemplateDAO();
}
