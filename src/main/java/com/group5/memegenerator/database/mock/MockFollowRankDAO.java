package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.IFollowRankDAO;
import com.group5.memegenerator.model.UserRelation;

public class MockFollowRankDAO implements IFollowRankDAO {

    public UserRelation getFollowRank(String userId1, String userId2) {
        UserRelation userRelation = new UserRelation();
        if (userId1 == "userId1" && userId2 == "userId2") {
            userRelation.setProfileVisits(5);
            userRelation.setComments(5);
            userRelation.setLikes(5);
        } else {
            userRelation.setProfileVisits(0);
            userRelation.setComments(0);
            userRelation.setLikes(0);
        }
        return userRelation;
    }

}
