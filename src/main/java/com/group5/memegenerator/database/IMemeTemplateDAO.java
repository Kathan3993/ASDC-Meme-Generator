package com.group5.memegenerator.database;

import com.group5.memegenerator.model.IMemeTemplate;

import java.sql.SQLException;
import java.util.List;

public interface IMemeTemplateDAO {
    List<IMemeTemplate> getAllMemeTemplatesFromDatabase() throws SQLException;

    List<IMemeTemplate> getApprovedMemeTemplatesFromDatabase() throws SQLException;

    List<IMemeTemplate> getUnApprovedTemplatesFromDatabase() throws SQLException;

    DatabaseResponse saveMemeTemplate(String base64);

    DatabaseResponse updateMemeTemplate(int templateId, int categoryId);

    DatabaseResponse deleteMemeTemplate(int id);
}
