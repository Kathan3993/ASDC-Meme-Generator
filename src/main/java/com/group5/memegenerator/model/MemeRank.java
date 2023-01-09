package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.IFollowRankDAO;

import java.util.Iterator;
import java.util.List;

public class MemeRank implements IMemeRank {
    private String userId;
    private IFollowRankDAO followRankDao;

    public MemeRank(String userId, IFollowRankDAO followRankDao) {
        this.userId = userId;
        this.followRankDao = followRankDao;
    }

    public List<IMeme> getMemeRank(List<IMeme> memes) {
        Iterator<IMeme> iter = memes.iterator();
        while (iter.hasNext()) {
            IMeme meme = iter.next();
            meme.setMemeRank(calculateMemeRank(meme));
        }
        List<IMeme> sortedMemes = sortMemesByMemeRank(memes);
        return sortedMemes;
    }

    private double calculateMemeRank(IMeme meme) {
        double a = 1;
        double b = 2;
        double c = 3;
        IFollowRank followRank = new FollowRank(followRankDao);
        double followRankScore = followRank.getFollowRank(userId, meme.getUserId());
        int comments = 0;
        if (meme.getMemeComments() != null) {
            comments = meme.getMemeComments().size();
        }
        double value = (a * meme.getMemeLikes()) + (b * comments)
                + (c * followRankScore) / (a + b + c);
        return value;
    }

    private List<IMeme> sortMemesByMemeRank(List<IMeme> memes) {
        for (int i = 0; i < memes.size(); i++) {
            for (int j = 1; j < (memes.size() - i); j++) {
                if (memes.get(j - 1).getMemeRank() > memes.get(j).getMemeRank()) {
                    IMeme temp = memes.get(j - 1);
                    memes.set(j - 1, memes.get(j));
                    memes.set(j, temp);
                }
            }
        }
        return memes;
    }

}
