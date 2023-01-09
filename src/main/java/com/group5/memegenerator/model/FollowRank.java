package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IFollowRankDAO;

public class FollowRank implements IFollowRank {
    private IFollowRankDAO followRankDao;

    public FollowRank(IFollowRankDAO followRankDao) {
        this.followRankDao = followRankDao;
    }

    public double getFollowRank(String userId1, String userId2) {
        UserRelation userRelation = followRankDao.getFollowRank(userId1, userId2);
        return followRankScore(userRelation);
    }

    private double followRankScore(UserRelation userRelation) {
        double a = 0.2;
        double b = 0.4;
        double c = 0.6;

        double weightedAverage = (a * userRelation.getProfileVisits() + b * userRelation.getLikes()
                + c * userRelation.getComments())
                / (a + b + c);
        return weightedAverage;
    }

}
