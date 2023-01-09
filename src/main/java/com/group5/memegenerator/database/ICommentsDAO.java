package com.group5.memegenerator.database;

import com.group5.memegenerator.model.IComments;

import java.util.List;

public interface ICommentsDAO {

    List<IComments> getComments(String id);

    boolean setComment(String comment, String memeId, String userId);
}
