package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IMemeTemplateDAO;
import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.model.IMemeTemplate;
import com.group5.memegenerator.model.IMemeTemplatesOperations;
import com.group5.memegenerator.model.MemeTemplateDto;

public interface ITemplateLibraryFactory {

    IMemeTemplate makeMemeTemplate();

    IMemeTemplate makeMemeTemplate(IMemeTemplateDAO memeTemplateDAO);

    IMemeTemplate makeMemeTemplate(IMemeTemplateDAO memeTemplateDAOFactory, IUserDetailsDAO userDetailsDAO);

    IMemeTemplatesOperations makeMemeTemplatesOperation(IMemeTemplateDAO memeTemplateDAO);

    MemeTemplateDto makeMemeTemplateDto();
}
