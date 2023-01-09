package com.group5.memegenerator.database;

import com.group5.memegenerator.model.IMeme;

public interface ILikeDAO {
    int getLikes(IMeme meme);

    boolean setLikes(String memeId, String userId);
}
