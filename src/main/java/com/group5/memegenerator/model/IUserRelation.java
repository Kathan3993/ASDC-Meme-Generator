package com.group5.memegenerator.model;

import com.group5.memegenerator.database.DatabaseResponse;

public interface IUserRelation {
    public DatabaseResponse incrementCount(String userId1, String userId2, UserRelationField field);
}
