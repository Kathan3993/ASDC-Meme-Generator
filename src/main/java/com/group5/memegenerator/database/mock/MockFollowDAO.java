package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IFollowDAO;

public class MockFollowDAO implements IFollowDAO {

    @Override
    public DatabaseResponse follow(String userId1, String userId2) {
        if(userId1==null||userId2==null)
        {
            return DatabaseResponse.ERROR;
        }
        return DatabaseResponse.SUCCESS;
    }

    @Override
    public DatabaseResponse unfollow(String userId1, String userId2) {
        if(userId1==null||userId2==null)
        {
            return DatabaseResponse.ERROR;
        }
        return DatabaseResponse.SUCCESS;
    }

    @Override
    public boolean isFollowing(String userId1, String userId2) {
        if(userId1==""||userId2=="")
        {
            return false;
        }
        return true;
    }

    @Override
    public String followerCount(String userId2) {
        if(userId2=="")
        {
            return "0";
        }
        return "1";
    }

    @Override
    public String followingCount(String userId1) {
        if(userId1=="")
        {
            return "0";
        }
        return "1";
    }

}
