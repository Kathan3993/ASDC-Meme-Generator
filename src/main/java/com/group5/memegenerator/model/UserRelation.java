package com.group5.memegenerator.model;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IUserRelationDAO;

import lombok.Getter;
import lombok.Setter;

public class UserRelation implements IUserRelation {
    @Getter
    @Setter
    private int profileVisits;
    @Getter
    @Setter
    private int likes;
    @Getter
    @Setter
    private int comments;

    private IUserRelationDAO userRelationDAO;

    public UserRelation() {

    }

    public UserRelation(IUserRelationDAO userRelationDAO) {
        this.userRelationDAO = userRelationDAO;
    }

    public DatabaseResponse incrementCount(String userId1, String userId2, UserRelationField field) {
        return userRelationDAO.incrementCount(userId1, userId2, field);
    }
}
