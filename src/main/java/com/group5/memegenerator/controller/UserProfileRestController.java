package com.group5.memegenerator.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IUserRelationDAO;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.UserRelationDAOFactory;
import com.group5.memegenerator.model.IUserRelation;
import com.group5.memegenerator.model.UserRelationField;
import com.group5.memegenerator.model.factory.UserRelationFactory;

@RestController
public class UserProfileRestController {

    @PostMapping(value = "/api/increment-count")
    public DatabaseResponse incrementCount(String userId1, String userId2, String field)
            throws Exception {
        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();

        IUserRelationDAO userRelationDAO = UserRelationDAOFactory.instance()
                .makeUserRelationDAO(databaseOperation);
        IUserRelation userRelation = UserRelationFactory.instance().makeUserRelation(userRelationDAO);
        UserRelationField userRelationField = UserRelationField.valueOf(field);
        return userRelation.incrementCount(userId1, userId2, userRelationField);
    }
}
