package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.model.IMeme;
import com.group5.memegenerator.model.factory.MemeFactory;

public class MockLikeDAO implements ILikeDAO {

    private IMeme meme;

    public MockLikeDAO() {
        IMeme meme = MemeFactory.instance().makeMeme();
        meme.setMemeId("321");
    }

    @Override
    public int getLikes(IMeme meme) {
        if (meme.getMemeId() == null) {
            return 0;
        }
        return 10;
    }

    @Override
    public boolean setLikes(String memeId, String userId) {
        if (memeId == null || userId == null) {
            return false;
        }
        return true;
    }
}
