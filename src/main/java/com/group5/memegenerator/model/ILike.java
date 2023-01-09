package com.group5.memegenerator.model;

import com.group5.memegenerator.database.ILikeDAO;

public interface ILike {

    ILikeDAO getLikeDAO();
    int loadLikesForMeme(IMeme meme);

    boolean addLikeToMeme(String memeId, String userId);
}
