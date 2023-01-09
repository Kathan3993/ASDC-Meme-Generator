package com.group5.memegenerator.model;

import com.group5.memegenerator.model.factory.TemplateLibraryFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MemeTemplateDtoTest {

    @Test
    public void getIMemeTemplates() {
        List<IMemeTemplate> memeTemplates = new ArrayList<>();

        for (int i = 0; i < 4; i++) {

            IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate();
            memeTemplate.setId(i + 1);
            memeTemplate.setBase64("test base64" + i);
            memeTemplate.setIsApproved("accept");
            memeTemplate.setCategoryId(i + 2);

            memeTemplates.add(memeTemplate);
        }

        MemeTemplateDto memeTemplateDto = TemplateLibraryFactory.instance().makeMemeTemplateDto();
        memeTemplateDto.setIMemeTemplates(memeTemplates);

        List<IMemeTemplate> returnedMemeTemplates = memeTemplateDto.getIMemeTemplates();

        Assertions.assertEquals(4, returnedMemeTemplates.size());
    }

    @Test
    public void setMemeTemplates() {
        List<IMemeTemplate> memeTemplates = new ArrayList<>();

        for (int i = 0; i < 5; i++) {

            IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate();
            memeTemplate.setId(i + 1);
            memeTemplate.setBase64("test base64" + i);
            memeTemplate.setIsApproved("accept");
            memeTemplate.setCategoryId(i + 2);

            memeTemplates.add(memeTemplate);
        }

        MemeTemplateDto memeTemplateDto = TemplateLibraryFactory.instance().makeMemeTemplateDto();
        memeTemplateDto.setIMemeTemplates(memeTemplates);

        List<IMemeTemplate> returnedMemeTemplates = memeTemplateDto.getIMemeTemplates();

        Assertions.assertEquals(5, returnedMemeTemplates.size());
    }
}
