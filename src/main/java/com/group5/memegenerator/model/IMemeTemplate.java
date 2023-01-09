package com.group5.memegenerator.model;

import org.springframework.web.multipart.MultipartFile;

public interface IMemeTemplate {

    String encodeMemeTemplate(MultipartFile memeTemplate);

    boolean isMemeTemplateAlreadyExist(String memeTemplate);

    MemeTemplateResponses saveMemeTemplateToDatabase(MultipartFile memeTemplate);

    String getBase64();

    void setBase64(String base64);

    String getIsApproved();

    void setIsApproved(String isApproved);

    int getId();

    void setId(int id);

    int getCategoryId();

    void setCategoryId(int categoryId);
}
