package com.group5.memegenerator.database;

import java.util.ArrayList;
import java.util.HashMap;

public class FollowDAO implements IFollowDAO {
    private IDatabaseOperation databaseOperation;

    public FollowDAO(IDatabaseOperation databaseOperation) {
        this.databaseOperation = databaseOperation;
    }

    public DatabaseResponse follow(String userId1, String userId2) {
        String fields = "userId1,userId2";
        String values = "'" + userId1 + "','" + userId2 + "'";
        DatabaseResponse result;
        try {
            result = databaseOperation.insert("follow", fields, values);
        } catch (Exception exception) {
            return DatabaseResponse.ERROR;
        }
        return result;
    }

    public DatabaseResponse unfollow(String userId1, String userId2) {
        String condition = "userId1='" + userId1 + "' AND userId2='" + userId2 + "'";
        DatabaseResponse result = databaseOperation.delete("follow", condition);
        return result;
    }

    public boolean isFollowing(String userId1, String userId2) {
        ArrayList<HashMap<String, String>> response = new ArrayList<>();
        try {
            response = databaseOperation.select("*", "follow",
                    " WHERE userId1='" + userId1 + "' AND userId2='" + userId2 + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.size() > 0) {
            return true;
        }
        return false;
    }

    public String followerCount(String userId2) {
        ArrayList<HashMap<String, String>> response = new ArrayList<>();
        try {
            response = databaseOperation.select("*", "follow",
                    " WHERE userId2='" + userId2 + "'");
        } catch (Exception e) {
            return null;
        }
        return response.size() + "";
    }

    public String followingCount(String userId1) {
        ArrayList<HashMap<String, String>> response = new ArrayList<>();
        try {
            response = databaseOperation.select("*", "follow",
                    " WHERE userId1='" + userId1 + "'");
        } catch (Exception e) {
            return null;
        }
        return response.size() + "";
    }
}
