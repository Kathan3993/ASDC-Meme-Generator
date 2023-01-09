package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.Database;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IMemeTemplateDAO;
import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.MemeTemplateDAOFactory;
import com.group5.memegenerator.database.factory.UserDetailsDAOFactory;
import com.group5.memegenerator.model.IMemeTemplate;
import com.group5.memegenerator.model.IMemeTemplatesOperations;
import com.group5.memegenerator.model.MemeTemplateResponses;
import com.group5.memegenerator.model.factory.TemplateLibraryFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.util.List;

@Controller
public class UserTemplateLibraryOperationsController {

    @GetMapping("/meme-library")
    public ModelAndView loadImagesToUser() {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();
        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMemeTemplateDAO(databaseOperation);
        IMemeTemplatesOperations memeTemplateOperations = TemplateLibraryFactory.instance().makeMemeTemplatesOperation(memeTemplateDAO);

        List<IMemeTemplate> memeTemplates = memeTemplateOperations.loadApprovedMemeTemplatesFromDatabase();

        if (memeTemplates == null) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("maintenance-meme-library");

            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("images", memeTemplates);
        modelAndView.setViewName("meme-library");

        return modelAndView;
    }

    @PostMapping("/upload-template")
    public String saveImageFromUser(@RequestParam("image") MultipartFile image) {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();

        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMemeTemplateDAO(databaseOperation);
        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeUserDetailsDAO(databaseOperation);
        IMemeTemplate memeTemplate = TemplateLibraryFactory.instance().makeMemeTemplate(memeTemplateDAO, userDetailsDAO);

        MemeTemplateResponses result = memeTemplate.saveMemeTemplateToDatabase(image);
        if (result.equals(MemeTemplateResponses.MemeTemplateUploadSuccessful)) {
            return "upload-successful";
        } else if (result.equals(MemeTemplateResponses.MemeTemplateExistUnapproved)) {
            return "unapproved-meme-template";
        } else if (result.equals(MemeTemplateResponses.MemeTemplateExistApproved)) {
            return "approved-meme-template";
        }
        return "upload-error";
    }
}
