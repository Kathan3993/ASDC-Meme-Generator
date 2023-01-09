package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IMemeTemplateDAO;
import com.group5.memegenerator.database.factory.MemeTemplateDAOFactory;
import com.group5.memegenerator.model.factory.ITemplateLibraryFactory;
import com.group5.memegenerator.model.factory.TemplateLibraryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MemeTemplateOperationsTest {

    @Test
    public void loadAllMemeTemplatesFromDatabaseTest() {

        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMockMemeTemplateDAO();
        IMemeTemplatesOperations memeTemplatesOperations = TemplateLibraryFactory.instance().makeMemeTemplatesOperation(memeTemplateDAO);

        List<IMemeTemplate> memeTemplateList = memeTemplatesOperations.loadAllMemeTemplatesFromDatabase();

        assertEquals(10, memeTemplateList.size());
    }

    @Test
    public void loadApprovedMemeTemplatesFromDatabaseTest() {

        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMockMemeTemplateDAO();
        IMemeTemplatesOperations memeTemplatesOperations = TemplateLibraryFactory.instance().makeMemeTemplatesOperation(memeTemplateDAO);

        List<IMemeTemplate> memeTemplateList = memeTemplatesOperations.loadApprovedMemeTemplatesFromDatabase();

        assertEquals(5, memeTemplateList.size());
    }

    @Test
    public void loadUnapprovedMemeTemplatesFromDatabaseTest() {

        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMockMemeTemplateDAO();
        IMemeTemplatesOperations memeTemplatesOperations = TemplateLibraryFactory.instance().makeMemeTemplatesOperation(memeTemplateDAO);

        List<IMemeTemplate> memeTemplateList = memeTemplatesOperations.loadUnapprovedMemeTemplatesFromDatabase();

        assertEquals(4, memeTemplateList.size());
    }

    @Test
    public void updateMemeTemplatesInDatabasePositiveTest() {

        ITemplateLibraryFactory templateLibraryFactory = TemplateLibraryFactory.instance();
        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMockMemeTemplateDAO();

        IMemeTemplate memeTemplate1 = templateLibraryFactory.makeMemeTemplate();
        memeTemplate1.setId(1);
        memeTemplate1.setBase64("data:image/*;base64, testUpdateOne");
        memeTemplate1.setCategoryId(2);
        memeTemplate1.setIsApproved("accept");

        IMemeTemplate memeTemplate2 = templateLibraryFactory.makeMemeTemplate();
        memeTemplate2.setId(2);
        memeTemplate2.setBase64("data:image/*;base64, testUpdateOne");
        memeTemplate2.setCategoryId(3);
        memeTemplate2.setIsApproved("reject");

        List<IMemeTemplate> memeTemplates = new ArrayList<>();
        memeTemplates.add(memeTemplate1);
        memeTemplates.add(memeTemplate2);


        IMemeTemplatesOperations memeTemplatesOperations = templateLibraryFactory.makeMemeTemplatesOperation(memeTemplateDAO);
        MemeTemplateResponses memeTemplateResponse = memeTemplatesOperations.updateMemeTemplatesInDatabase(memeTemplates);

        assertEquals(MemeTemplateResponses.MemeTemplateUpdatesSuccessful, memeTemplateResponse);
    }

    @Test
    public void updateMemeTemplatesInDatabaseNegativeTest() {

        ITemplateLibraryFactory templateLibraryFactory = TemplateLibraryFactory.instance();
        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMockMemeTemplateDAO();

        IMemeTemplate memeTemplate = templateLibraryFactory.makeMemeTemplate();
        memeTemplate.setId(0);
        memeTemplate.setBase64("data:image/*;base64, testUpdateThree");
        memeTemplate.setCategoryId(0);
        memeTemplate.setIsApproved("reject");

        IMemeTemplate memeTemplate1 = templateLibraryFactory.makeMemeTemplate();
        memeTemplate1.setId(0);
        memeTemplate1.setBase64("data:image/*;base64, testUpdateFour");
        memeTemplate1.setCategoryId(2);
        memeTemplate1.setIsApproved("accept");

        List<IMemeTemplate> memeTemplates = new ArrayList<>();
        memeTemplates.add(memeTemplate);
        memeTemplates.add(memeTemplate1);

        IMemeTemplatesOperations forCall = templateLibraryFactory.makeMemeTemplatesOperation(memeTemplateDAO);
        MemeTemplateResponses memeTemplateResponse = forCall.updateMemeTemplatesInDatabase(memeTemplates);

        assertEquals(MemeTemplateResponses.MemeTemplateUpdatesFail, memeTemplateResponse);
    }

}
