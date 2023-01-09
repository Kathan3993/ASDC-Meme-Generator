package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IMemeTemplateDAO;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.MemeTemplateDAOFactory;
import com.group5.memegenerator.model.IMemeData;
import com.group5.memegenerator.model.IMemeTemplate;
import com.group5.memegenerator.model.IMemeTemplatesOperations;
import com.group5.memegenerator.model.factory.MemeFactory;
import com.group5.memegenerator.model.factory.TemplateLibraryFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemeController {

    @GetMapping("/create-meme/{memeId}")
    public ModelAndView createMeme(@PathVariable("memeId") String memeId) {
        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();
        IMemeTemplateDAO memeTemplateDAO = MemeTemplateDAOFactory.instance().makeMemeTemplateDAO(databaseOperation);
        IMemeTemplatesOperations operations = TemplateLibraryFactory.instance()
                .makeMemeTemplatesOperation(memeTemplateDAO);
        IMemeTemplate meme = operations.loadApprovedMemeTemplatesFromDatabaseByMemeId(memeId);

        IMemeData memeData = MemeFactory.instance().makeMemeData(meme.getBase64(), "white", "white", "", "", 20f, 100,
                150, 100, 350);
        ModelAndView model = new ModelAndView("/create-meme");
        model.addObject("memeData", memeData);
        model.addObject("categoryId", meme.getCategoryId());
        model.addObject("memeId", meme.getId());
        return model;
    }
}
