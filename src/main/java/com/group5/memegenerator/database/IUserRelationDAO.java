package com.group5.memegenerator.database;

import com.group5.memegenerator.model.UserRelationField;

public interface IUserRelationDAO {
    public DatabaseResponse incrementCount(String userId1, String userId2, UserRelationField field) ;
}
