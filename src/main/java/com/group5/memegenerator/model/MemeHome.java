package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IFollowDAO;
import com.group5.memegenerator.database.IMemeHomeDAO;

import java.util.ArrayList;
import java.util.List;

public class MemeHome implements IMemeHome {

    private IMemeHomeDAO memeHomeDAO;

    public MemeHome(IMemeHomeDAO memeHomeDAO) {
        this.memeHomeDAO = memeHomeDAO;
    }

    @Override
    public List<IMeme> loadMemesInHomePage(String userId, IFollowDAO followDAO) {
        List<IMeme> myMemes = new ArrayList<>();
        int count = 0;
        List<IMeme> allMemes = this.memeHomeDAO.getMemeForHomePage(userId);
        for (IMeme meme : allMemes) {
            if (followDAO.isFollowing(userId, meme.getUserId())) {
                myMemes.add(meme);
            } else {
                if (count == 5) {
                    continue;
                }
                myMemes.add(meme);
                count++;
            }
        }
        return myMemes;
    }
}
