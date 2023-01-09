package com.group5.memegenerator.model;

import java.util.List;

public interface IMemeTemplatesOperations {
    List<IMemeTemplate> loadApprovedMemeTemplatesFromDatabase();

    List<IMemeTemplate> loadAllMemeTemplatesFromDatabase();

    List<IMemeTemplate> loadUnapprovedMemeTemplatesFromDatabase();

    MemeTemplateResponses updateMemeTemplatesInDatabase(List<IMemeTemplate> memeTemplates);

    IMemeTemplate loadApprovedMemeTemplatesFromDatabaseByMemeId(String memeId);
}
