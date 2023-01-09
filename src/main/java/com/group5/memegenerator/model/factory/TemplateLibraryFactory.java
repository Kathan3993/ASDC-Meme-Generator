package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IMemeTemplateDAO;
import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.model.*;

public class TemplateLibraryFactory implements ITemplateLibraryFactory {

    private static ITemplateLibraryFactory templateLibraryFactory = null;

    private TemplateLibraryFactory() {
    }

    public static ITemplateLibraryFactory instance() {
        if (templateLibraryFactory == null) {
            templateLibraryFactory = new TemplateLibraryFactory();
        }
        return templateLibraryFactory;
    }

    @Override
    public IMemeTemplate makeMemeTemplate() {
        return new MemeTemplate();
    }

    @Override
    public IMemeTemplate makeMemeTemplate(IMemeTemplateDAO memeTemplateDAOFactory) {
        return new MemeTemplate(memeTemplateDAOFactory);
    }

    @Override
    public IMemeTemplate makeMemeTemplate(IMemeTemplateDAO memeTemplateDAOFactory, IUserDetailsDAO userDetailsDAO) {
        return new MemeTemplate(memeTemplateDAOFactory, userDetailsDAO);
    }

    @Override
    public IMemeTemplatesOperations makeMemeTemplatesOperation(IMemeTemplateDAO memeTemplateDAO) {
        return new MemeTemplatesOperations(memeTemplateDAO);
    }

    @Override
    public MemeTemplateDto makeMemeTemplateDto() {
        return new MemeTemplateDto();
    }

}
