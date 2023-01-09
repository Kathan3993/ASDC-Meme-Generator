package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.ICommentsDAO;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.IMemeDAO;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.model.Comments;
import com.group5.memegenerator.model.IComments;
import com.group5.memegenerator.model.IMeme;
import com.group5.memegenerator.model.Meme;
import com.group5.memegenerator.model.factory.MemeFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MockMemeDAO implements IMemeDAO {

    public IMeme loadMemeById(String memeId) {
        if (memeId != "0") {
            IMeme meme = new Meme();
            int randomRange = 10 - 1;
            Random random = new Random();
            List<IComments> comments = new ArrayList<>();
            ICommentsDAO commentsDAO = MemeDAOFactory.instance().makeMockCommentsDAO();
            IComments comment = MemeFactory.instance().makeComment(commentsDAO);
            try {
                comment.setComment("test comment", memeId, "1");
                comment.setCommentTimeStamp("1234");
                comments.add(comment);

                meme.setMemeComments(comments);
                meme.setMemeId(memeId);
                meme.setMemeLikes(random.nextInt() * randomRange);
                meme.setMemePicture("meme-image-in-base64");
                meme.setUserId("1");
                meme.setMemeRank(0);
                return meme;

            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public IMeme getMemeById(String memeId) {
        if (memeId != "0") {
            Meme meme = new Meme();
            int randomRange = 10 - 1;
            Random random = new Random();
            List<IComments> comments = new ArrayList<>();
            IComments comment = new Comments();
            try {
                comment.setComment("test comment", memeId, "1");
                comment.setCommentTimeStamp("1234");
                comments.add(comment);

                meme.setMemeComments(comments);
                meme.setMemeId(memeId);
                meme.setMemeLikes(random.nextInt() * randomRange);
                meme.setMemePicture("meme-image-in-base64");
                meme.setUserId("1");
                meme.setMemeRank(0);
                return meme;

            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    @Override
    public DatabaseResponse saveMeme(String username, String memeCategory, String memeId, String memeImage) {
        if (username == "" || memeCategory == "" || memeId == "" || memeImage == "") {
            return DatabaseResponse.ERROR;
        }
        return DatabaseResponse.SUCCESS;
    }

    @Override
    public IMeme loadMemeById(String id, ILikeDAO likeDAO) throws Exception {
        if (id.equals("0")) {
            return null;
        }
        Meme meme = new Meme();
        int randomRange = 10 - 1;
        Random random = new Random();
        meme.setMemeId(id);
        meme.setMemeLikes(random.nextInt() * randomRange);
        meme.setMemePicture("meme-image-in-base64");
        meme.setUserId("1");
        meme.setMemeRank(0);
        meme.setCategoryId("d");
        return meme;
    }

}
