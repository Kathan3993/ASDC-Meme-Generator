package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IUserRelationDAO;
import com.group5.memegenerator.model.IUserRelation;
import com.group5.memegenerator.model.UserRelation;

public class UserRelationFactory implements IUserRelationFactory {
    private static IUserRelationFactory userRelationFactory = null;

    private UserRelationFactory() {
    }

    public static IUserRelationFactory instance() {
        if (userRelationFactory == null) {
            userRelationFactory = new UserRelationFactory();
        }
        return userRelationFactory;
    }

    @Override
    public IUserRelation makeUserRelation(IUserRelationDAO userRelationDAO) {
        return new UserRelation(userRelationDAO);
    }

}
