package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.ICommentsDAO;
import com.group5.memegenerator.model.Comments;
import com.group5.memegenerator.model.IComments;
import com.group5.memegenerator.model.IMeme;
import com.group5.memegenerator.model.factory.MemeFactory;

import java.util.ArrayList;
import java.util.List;

public class MockCommentsDAO implements ICommentsDAO {

    private IMeme meme;

    public MockCommentsDAO() {

        meme = MemeFactory.instance().makeMeme();
        meme.setMemeId("1");
        meme.setMemeRank(2);
        meme.setMemeLikes(4);
        meme.setMemePicture("1234");
        meme.setUserId("5");
        meme.setCategoryId("10");

        List<IComments> comments = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            IComments comment = new Comments();
            comment.setCommentTimeStamp("1234");
            comment.setCommentText("test comment");

            comments.add(comment);
        }
        meme.setMemeComments(comments);
    }

    @Override
    public List<IComments> getComments(String id) {

        if (id == meme.getMemeId()) {
            return meme.getMemeComments();
        }
        return null;
    }

    @Override
    public boolean setComment(String comment, String memeId, String userId) {

        if (comment == null || memeId == null || userId == null) {

            return false;

        }

        return true;
    }
}
