package com.group5.memegenerator.database;

import com.group5.memegenerator.model.IMemeTemplate;
import com.group5.memegenerator.model.factory.TemplateLibraryFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemeTemplateDAO implements IMemeTemplateDAO {
    private IDatabaseOperation databaseOperation;

    public MemeTemplateDAO(IDatabaseOperation databaseOperation) {
        this.databaseOperation = databaseOperation;
    }

    private static List<IMemeTemplate> getMemeList(ArrayList<HashMap<String, String>> results) {
        List<IMemeTemplate> memeTemplates = null;

        try {
            memeTemplates = new ArrayList<>();
            for (HashMap<String, String> result : results) {

                IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate();

                memeTemplate.setId(Integer.parseInt(result.get("id")));
                memeTemplate.setBase64(result.get("base64"));
                memeTemplate.setCategoryId(Integer.parseInt(result.get("category_id")));
                memeTemplate.setIsApproved(result.get("isApproved"));

                memeTemplates.add(memeTemplate);
            }

        } catch (Exception e) {
            return null;
        }
        return memeTemplates;
    }

    @Override
    public List<IMemeTemplate> getAllMemeTemplatesFromDatabase() {

        String tableName = "meme_templates";
        String fields = "*";
        String endQuery = "";

        try {
            ArrayList<HashMap<String, String>> results = databaseOperation.select(fields, tableName, endQuery);
            return MemeTemplateDAO.getMemeList(results);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<IMemeTemplate> getApprovedMemeTemplatesFromDatabase() throws SQLException {

        String tableName = "meme_templates";
        String fields = "*";
        String endQuery = "WHERE isApproved = 'accept'";

        try {
            ArrayList<HashMap<String, String>> results = databaseOperation.select(fields, tableName, endQuery);
            return MemeTemplateDAO.getMemeList(results);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<IMemeTemplate> getUnApprovedTemplatesFromDatabase() throws SQLException {

        String tableName = "meme_templates";
        String fields = "*";
        String endQuery = "WHERE isApproved = 'NA'";

        try {
            ArrayList<HashMap<String, String>> results = databaseOperation.select(fields, tableName, endQuery);
            return MemeTemplateDAO.getMemeList(results);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public DatabaseResponse saveMemeTemplate(String base64) {

        String tableName = "meme_templates";
        String fields = "base64, isApproved";
        String values = "'" + base64 + "','NA'";

        return databaseOperation.insert(tableName, fields, values);
    }

    @Override
    public DatabaseResponse updateMemeTemplate(int templateId, int categoryId) {

        String tableName = "meme_templates";
        String setValues = "category_id = " + categoryId + ", isApproved = 'accept'";
        String condition = "id = " + templateId;

        return databaseOperation.update(tableName, setValues, condition);
    }

    @Override
    public DatabaseResponse deleteMemeTemplate(int id) {

        String tableName = "meme_templates";
        String condition = "id = " + id;

        return databaseOperation.delete(tableName, condition);
    }
}
