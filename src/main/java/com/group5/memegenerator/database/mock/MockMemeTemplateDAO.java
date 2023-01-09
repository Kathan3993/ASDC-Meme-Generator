package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IMemeTemplateDAO;
import com.group5.memegenerator.model.IMemeTemplate;
import com.group5.memegenerator.model.factory.TemplateLibraryFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MockMemeTemplateDAO implements IMemeTemplateDAO {

    @Override
    public List<IMemeTemplate> getAllMemeTemplatesFromDatabase() {
        List<IMemeTemplate> memeTemplates = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate();

            memeTemplate.setId(i + 1);
            memeTemplate.setCategoryId(i + 2);
            memeTemplate.setBase64("1234" + i);

            if (i / 2 == 0) {
                memeTemplate.setIsApproved("NA");
            } else {
                memeTemplate.setIsApproved("accept");
            }

            memeTemplates.add(memeTemplate);
        }
        return memeTemplates;
    }

    @Override
    public List<IMemeTemplate> getApprovedMemeTemplatesFromDatabase() {
        List<IMemeTemplate> memeTemplates = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate();
                memeTemplate.setId(i + 1);
                memeTemplate.setCategoryId(i + 2);
                memeTemplate.setBase64("1234" + i);
                memeTemplate.setIsApproved("NA");
            } else {
                IMemeTemplate memeTemplate2 = TemplateLibraryFactory.instance().makeMemeTemplate();
                memeTemplate2.setId(i + 1);
                memeTemplate2.setCategoryId(i + 2);
                memeTemplate2.setBase64("1234" + i);
                memeTemplate2.setIsApproved("accept");
                memeTemplates.add(memeTemplate2);
            }
        }
        return memeTemplates;
    }

    @Override
    public List<IMemeTemplate> getUnApprovedTemplatesFromDatabase() {
        List<IMemeTemplate> memeTemplates = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate();

            memeTemplate.setId(i + 1);
            memeTemplate.setCategoryId(i + 2);
            memeTemplate.setBase64("1234" + i);
            if (i % 2 == 0) {
                memeTemplate.setIsApproved("NA");
                memeTemplates.add(memeTemplate);
            } else {
                memeTemplate.setIsApproved("accept");
            }
        }
        return memeTemplates;
    }

    @Override
    public DatabaseResponse saveMemeTemplate(String base64) {
        if (base64 == null) {
            return DatabaseResponse.ERROR;
        }

        IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate();
        memeTemplate.setId(ThreadLocalRandom.current().nextInt(0, 1000 + 1));
        memeTemplate.setBase64(base64);
        memeTemplate.setCategoryId(ThreadLocalRandom.current().nextInt(0, 10 + 1));
        memeTemplate.setIsApproved("NA");

        return DatabaseResponse.SUCCESS;
    }

    @Override
    public DatabaseResponse updateMemeTemplate(int templateId, int categoryId) {
        if (templateId == 0 || categoryId == 0) {
            return DatabaseResponse.ERROR;
        }

        IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate();
        memeTemplate.setId(1);
        memeTemplate.setBase64("test");
        memeTemplate.setCategoryId(categoryId);
        memeTemplate.setIsApproved("NA");

        if (memeTemplate.getIsApproved().equals("NA")) {
            memeTemplate.setIsApproved("accept");
        }

        return DatabaseResponse.SUCCESS;
    }

    @Override
    public DatabaseResponse deleteMemeTemplate(int id) {
        if (id == 0) {
            return DatabaseResponse.ERROR;
        }

        IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate();
        memeTemplate.setId(2);
        memeTemplate.setBase64("test");
        memeTemplate.setCategoryId(9);
        memeTemplate.setIsApproved("NA");

        if (memeTemplate.getId() == id) {
            memeTemplate.setIsApproved("reject");
        }

        return DatabaseResponse.SUCCESS;
    }
}
