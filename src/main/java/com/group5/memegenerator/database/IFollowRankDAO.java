package com.group5.memegenerator.database;

import com.group5.memegenerator.model.UserRelation;

public interface IFollowRankDAO {
    public UserRelation getFollowRank(String userId1, String userId2);
}
