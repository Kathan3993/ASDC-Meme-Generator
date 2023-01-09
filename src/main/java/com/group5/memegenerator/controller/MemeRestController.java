package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.*;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.model.IMemeData;
import com.group5.memegenerator.model.MemeData;
import com.group5.memegenerator.model.factory.MemeFactory;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemeRestController {
    @PostMapping("/api/modify-image")

    public String modifyMeme(@RequestBody MemeData memeData) {
        return memeData.addText();
    }

    @PostMapping("/api/save-meme")
    public DatabaseResponse saveMeme(@CookieValue(value = "username", defaultValue = "anay@dal.ca") String username,
                                     String memeId, String categoryId, String memeImage) {
        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();
        IMemeDAO memeDao = new MemeDAO(databaseOperation);
        IMemeData memeData = MemeFactory.instance().makeMemeData(memeDao);
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
        IUserDetailsDAO userDetailsDAO = new UserDetailsDAO(databaseOperation);
        String memeUserId = userDetailsDAO.getUserIdByUserName(username);
        return memeData.saveMeme(memeUserId, categoryId, memeId, memeImage);
    }
}
