package com.group5.memegenerator.model;

import java.util.List;

public interface IMeme {

    String getMemeId();

    void setMemeId(String memeId);

    String getMemePicture();

    void setMemePicture(String memePicture);

    long getMemeLikes();

    void setMemeLikes(long memeLikes);

    List<IComments> getMemeComments();

    void setMemeComments(List<IComments> comments);

    String getUserId();

    void setUserId(String userId);

    String getCategoryId();

    void setCategoryId(String categoryId);

    double getMemeRank();

    void setMemeRank(double memeRank);

    boolean loadComments(IComments comments);

    IMeme loadMeme(String id, IComments comments);
}

