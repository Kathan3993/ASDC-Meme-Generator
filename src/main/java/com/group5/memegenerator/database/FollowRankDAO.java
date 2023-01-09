package com.group5.memegenerator.database;

import com.group5.memegenerator.model.UserRelation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class FollowRankDAO implements IFollowRankDAO {
    IDatabaseOperation databaseOperation;

    public FollowRankDAO(IDatabaseOperation dbo) {
        this.databaseOperation = dbo;
    }

    public UserRelation getFollowRank(String userId1, String userId2) {
        UserRelation userRelation = new UserRelation();
        userRelation.setProfileVisits(0);
        userRelation.setLikes(0);
        userRelation.setComments(0);



        ArrayList<HashMap<String, String>> results = new ArrayList<>();
        try {

            results = databaseOperation.select("*", "user_relation",
                    "userId1='" + userId1 + "' AND userId2='" + userId2+"'");

        } catch (Exception e) {
            return userRelation;

        }
        if(results==null){
            return userRelation;
        }
        Iterator<HashMap<String, String>> iter = results.iterator();
        while (iter.hasNext()) {
            HashMap<String, String> result = iter.next();
            Iterator<String> keyIter = result.keySet().iterator();
            while (keyIter.hasNext()) {
                String key = keyIter.next();
                switch (key) {
                    case "profile_visits": {
                        userRelation.setProfileVisits(Integer.parseInt(result.get(key)));
                        break;
                    }
                    case "likes": {
                        userRelation.setLikes(Integer.parseInt(result.get(key)));
                        break;
                    }
                    case "comments": {
                        userRelation.setComments(Integer.parseInt(result.get(key)));
                        break;
                    }
                }
            }

        }

        return userRelation;
    }

}
