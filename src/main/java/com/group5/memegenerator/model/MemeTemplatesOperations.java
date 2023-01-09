package com.group5.memegenerator.model;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IMemeTemplateDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MemeTemplatesOperations implements IMemeTemplatesOperations {

    private IMemeTemplateDAO memeTemplateDAO;

    public MemeTemplatesOperations(IMemeTemplateDAO memeTemplateDAO) {
        this.memeTemplateDAO = memeTemplateDAO;
    }

    public List<IMemeTemplate> loadAllMemeTemplatesFromDatabase() {
        try {
            return memeTemplateDAO.getAllMemeTemplatesFromDatabase();
        } catch (SQLException e) {
            return null;
        }
    }

    public List<IMemeTemplate> loadApprovedMemeTemplatesFromDatabase() {
        try {
            return memeTemplateDAO.getApprovedMemeTemplatesFromDatabase();
        } catch (SQLException e) {
            return null;
        }
    }

    public List<IMemeTemplate> loadUnapprovedMemeTemplatesFromDatabase() {
        try {
            return memeTemplateDAO.getUnApprovedTemplatesFromDatabase();
        } catch (SQLException e) {
            return null;
        }
    }

    public MemeTemplateResponses updateMemeTemplatesInDatabase(List<IMemeTemplate> updateMemeTemplates) {
        List<IMemeTemplate> error = null;

        for (IMemeTemplate i : updateMemeTemplates) {
            if (i.getIsApproved().equals("accept")) {
                int id = i.getId();
                int cid = i.getCategoryId();

                DatabaseResponse databaseResponse = this.memeTemplateDAO.updateMemeTemplate(id, cid);
                if (databaseResponse.equals(DatabaseResponse.ERROR)) {
                    if (error == null) {
                        error = new ArrayList<>();
                    }
                    error.add(i);
                }
            } else if (i.getIsApproved().equals("reject")) {
                int id = i.getId();

                DatabaseResponse databaseResponse = this.memeTemplateDAO.deleteMemeTemplate(id);
                if (databaseResponse.equals(DatabaseResponse.ERROR)) {
                    if (error == null) {
                        error = new ArrayList<>();
                    }
                    error.add(i);
                }
            }
        }
        if (error == null) {
            return MemeTemplateResponses.MemeTemplateUpdatesSuccessful;
        }
        return MemeTemplateResponses.MemeTemplateUpdatesFail;
    }

    public IMemeTemplate loadApprovedMemeTemplatesFromDatabaseByMemeId(String memeId) {
        try {
            List<IMemeTemplate> templates = memeTemplateDAO.getApprovedMemeTemplatesFromDatabase();
            Iterator<IMemeTemplate> iter = templates.iterator();
            while (iter.hasNext()) {
                IMemeTemplate memeTemplate = iter.next();
                if (memeTemplate.getId() == Integer.parseInt(memeId)) {
                    return memeTemplate;
                }
            }
            return null;
        } catch (SQLException e) {
            return null;
        }
    }
}
