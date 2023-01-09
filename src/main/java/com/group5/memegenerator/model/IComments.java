package com.group5.memegenerator.model;

import java.util.List;

public interface IComments {

    String getCommentTimeStamp();

    void setCommentTimeStamp(String commentTimeStamp);

    String getCommentText();

    void setCommentText(String commentText);

    List<IComments> loadCommentsUsingMemeId(String id);

    boolean setComment(String comment, String memeId, String userId);
}
