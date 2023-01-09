package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.IUserRelationDAO;
import com.group5.memegenerator.model.IUserRelation;

public interface IUserRelationFactory {
    public IUserRelation makeUserRelation(IUserRelationDAO userRelationDAO);
}
