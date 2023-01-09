package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.*;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.database.factory.MemeTemplateDAOFactory;
import com.group5.memegenerator.database.factory.UserDetailsDAOFactory;
import com.group5.memegenerator.model.*;
import com.group5.memegenerator.model.UserDetails.ROLE;
import com.group5.memegenerator.model.factory.TemplateLibraryFactory;
import com.group5.memegenerator.model.factory.UserDetailsFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AdminTemplateLibraryOperationsController {

    @GetMapping("/template-decision")
    public ModelAndView displayAdminUnapprovedImages(@CookieValue(name = "id") String id) {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeUserDetailsDAO(databaseOperation);
        IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);
        IUserDetails user = userDetails.loadUser(id);
        if (user.getRole().equals(ROLE.user)) {
            return new ModelAndView("redirect:/");
        }
        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMemeTemplateDAO(databaseOperation);
        IMemeTemplatesOperations memeTemplateOperations = TemplateLibraryFactory.instance()
                .makeMemeTemplatesOperation(memeTemplateDAO);

        List<IMemeTemplate> unapprovedMemeTemplates = memeTemplateOperations.loadUnapprovedMemeTemplatesFromDatabase();

        if (unapprovedMemeTemplates.size() == 0) {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("no-new-meme-template-available");
            return modelAndView;
        }

        MemeTemplateDto unapprovedTemplateDto = TemplateLibraryFactory.instance().makeMemeTemplateDto();
        unapprovedTemplateDto.setIMemeTemplates(unapprovedMemeTemplates);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("unapprovedTemplateDto", unapprovedTemplateDto);
        modelAndView.addObject("id", id);
        modelAndView.setViewName("template-decision");

        return modelAndView;
    }

    @PostMapping(value = "/template-decision-submit")
    public String sendAdminDecisionToDatabase(
            @ModelAttribute("unapprovedTemplateDto") MemeTemplateDto unapprovedTemplateDto) {

        IDatabaseOperation databaseOperation = new DatabaseOperation();
        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMemeTemplateDAO(databaseOperation);
        IMemeTemplatesOperations memeTemplateOperations = TemplateLibraryFactory.instance()
                .makeMemeTemplatesOperation(memeTemplateDAO);

        MemeTemplateResponses response = memeTemplateOperations
                .updateMemeTemplatesInDatabase(unapprovedTemplateDto.getIMemeTemplates());

        if (response.equals(MemeTemplateResponses.MemeTemplateUpdatesFail)) {
            return "upload-error";
        }
        return "template-decision-submit";
    }

}
