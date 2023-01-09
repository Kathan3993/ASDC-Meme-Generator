package com.group5.memegenerator.model;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IFollowDAO;

public class Follow implements IFollow {
    private IFollowDAO followDAO;

    public Follow(IFollowDAO followDAO) {
        this.followDAO = followDAO;
    }

    public DatabaseResponse follow(String userId1, String userId2) {
        return followDAO.follow(userId1, userId2);
    }

    public DatabaseResponse unfollow(String userId1, String userId2) {
        return followDAO.unfollow(userId1, userId2);
    }

    public boolean isFollowing(String userId1, String userId2) {
        return followDAO.isFollowing(userId1, userId2);
    }

    public String followerCount(String userId1) {
        String count = followDAO.followerCount(userId1);
        if (count == null || count == "") {
            return "0";
        }
        return count;
    }

    public String followingCount(String userId1) {
        String count = followDAO.followingCount(userId1);
        if (count == null || count == "") {
            return "0";
        }
        return count;
    }
}
