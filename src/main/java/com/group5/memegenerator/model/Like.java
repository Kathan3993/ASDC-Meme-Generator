package com.group5.memegenerator.model;

import com.group5.memegenerator.database.ILikeDAO;
import lombok.Getter;

public class Like implements ILike {

    @Getter
    private ILikeDAO likeDAO;

    public Like(ILikeDAO likeDAO) {
        this.likeDAO = likeDAO;
    }

    @Override
    public int loadLikesForMeme(IMeme meme) {

        return this.likeDAO.getLikes(meme);
    }

    @Override
    public boolean addLikeToMeme(String memeId, String userId) {

        return this.likeDAO.setLikes(memeId, userId);

    }
}
