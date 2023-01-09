package com.group5.memegenerator.model;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IMemeTemplateDAO;
import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.database.UserDetailsDAO;
import com.group5.memegenerator.database.factory.UserDetailsDAOFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

public class MemeTemplate implements IMemeTemplate {

    private IMemeTemplateDAO memeTemplateDAO;
    private IUserDetailsDAO userDetailsDAO;
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private String base64;
    @Getter
    @Setter
    private int categoryId;
    @Getter
    @Setter
    private String isApproved;

    public MemeTemplate() {
    }

    public MemeTemplate(IMemeTemplateDAO memeTemplateDAO) {
        this.memeTemplateDAO = memeTemplateDAO;
    }
    public MemeTemplate(IMemeTemplateDAO memeTemplateDAO, IUserDetailsDAO userDetailsDAO) {
        this.memeTemplateDAO = memeTemplateDAO;
        this.userDetailsDAO = userDetailsDAO;
    }

    @Override
    public String encodeMemeTemplate(MultipartFile memeTemplate) {
        try {
            return "data:image/*;base64," + Base64.getEncoder().encodeToString(memeTemplate.getBytes());
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean isMemeTemplateAlreadyExist(String memeTemplate) {
        List<IMemeTemplate> memeTemplates = null;
        try {
            memeTemplates = memeTemplateDAO.getAllMemeTemplatesFromDatabase();

            for (IMemeTemplate template : memeTemplates) {
                if (template.getBase64().equals(memeTemplate)) {
                    this.isApproved = template.getIsApproved();
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in isMemeTemplateAlreadyExist()");
        }
        return false;
    }

    @Override
    public MemeTemplateResponses saveMemeTemplateToDatabase(MultipartFile memeTemplate) {
        this.base64 = encodeMemeTemplate(memeTemplate);

        if (isMemeTemplateAlreadyExist(this.base64)) {
            if (this.isApproved.equals("NA")) {
                return MemeTemplateResponses.MemeTemplateExistUnapproved;
            } else if (this.isApproved.equals("accept")) {
                return MemeTemplateResponses.MemeTemplateExistApproved;
            }
        }

        DatabaseResponse databaseResponse = memeTemplateDAO.saveMemeTemplate(this.base64);
        if (databaseResponse.equals(DatabaseResponse.SUCCESS)) {
            TemplateSubject.instance(this.userDetailsDAO).notifySubjects();
            return MemeTemplateResponses.MemeTemplateUploadSuccessful;
        }
        return MemeTemplateResponses.MemeTemplateUploadFail;
    }
}
