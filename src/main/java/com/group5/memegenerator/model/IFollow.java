package com.group5.memegenerator.model;

import com.group5.memegenerator.database.DatabaseResponse;

public interface IFollow {
    public DatabaseResponse follow(String userId1, String userId2);

    public DatabaseResponse unfollow(String userId1, String userId2);

    public boolean isFollowing(String userId1, String userId2);

    public String followerCount(String userId2);

    public String followingCount(String userId1);
}
