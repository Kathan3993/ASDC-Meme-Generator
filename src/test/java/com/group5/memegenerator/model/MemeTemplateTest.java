package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IMemeTemplateDAO;
import com.group5.memegenerator.database.factory.MemeTemplateDAOFactory;
import com.group5.memegenerator.model.factory.TemplateLibraryFactory;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MemeTemplateTest {

    @Test
    public void isMemeTemplateAlreadyExistPositiveTest() {
        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMockMemeTemplateDAO();
        List<IMemeTemplate> memeTemplates = null;

        try {
            memeTemplates = memeTemplateDAO.getAllMemeTemplatesFromDatabase();
        } catch (SQLException e) {
            System.out.println("Error in isMemeTemplateAlreadyExistPositiveTest()");
        }

        IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate();
        memeTemplate.setId(1);
        memeTemplate.setBase64("12340");
        memeTemplate.setCategoryId(1);
        memeTemplate.setIsApproved("NA");

        boolean result = false;
        for (IMemeTemplate dummy : memeTemplates) {
            if (dummy.getBase64().equals(memeTemplate.getBase64())) {
                result = true;
            }
        }

        Assertions.assertEquals(true, result);
    }

    @Test
    public void isMemeTemplateAlreadyExistNegativeTest() {

        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMockMemeTemplateDAO();
        List<IMemeTemplate> memeTemplates = null;

        try {
            memeTemplates = memeTemplateDAO.getAllMemeTemplatesFromDatabase();
        } catch (SQLException e) {
            System.out.println("Error in isMemeTemplateAlreadyExistPositiveTest()");
        }

        IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate();
        memeTemplate.setId(1);
        memeTemplate.setBase64("12340000");
        memeTemplate.setCategoryId(1);
        memeTemplate.setIsApproved("NA");

        boolean result = false;
        for (IMemeTemplate dummy : memeTemplates) {
            if (dummy.getBase64().equals(memeTemplate.getBase64())) {
                result = true;
            }
        }

        Assertions.assertEquals(false, result);
    }

    @Test
    public void saveMemeTemplateToDatabaseTest() {

//        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMockMemeTemplateDAO();
//        IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate(memeTemplateDAO);
//
//        try {
//            Resource resource = new ClassPathResource("static/images/test1.jpg");
//            FileInputStream file = new FileInputStream(resource.getFile());
//            MultipartFile multipartFile = new MockMultipartFile(
//                    "resource",
//                    resource.getFile().getName(),
//                    "image/jpg",
//                    IOUtils.toByteArray(file)
//            );
//
//            MemeTemplateResponses memeTemplateResponses = memeTemplate.saveMemeTemplateToDatabase(multipartFile);
//
//            assertEquals(MemeTemplateResponses.MemeTemplateUploadSuccessful, memeTemplateResponses);
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println("File not found");
//        }
    }
}
