package com.group5.memegenerator.database;

import com.group5.memegenerator.model.ILike;
import com.group5.memegenerator.model.IMeme;

public interface IMemeDAO {

    IMeme getMemeById(String memeId);

    public DatabaseResponse saveMeme(String username, String memeCategory, String memeId, String memeImage);

    IMeme loadMemeById(String id, ILikeDAO likeDAO) throws Exception;
}
