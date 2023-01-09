package com.group5.memegenerator.model;

import com.group5.memegenerator.database.ICommentsDAO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Comments implements IComments {

    private ICommentsDAO commentsDAO;

    @Getter
    @Setter
    private String commentTimeStamp;

    @Getter
    @Setter
    private String commentText;

    public Comments() {

    }

    public Comments(ICommentsDAO commentsDAO) {
        this.commentsDAO = commentsDAO;
    }

    @Override
    public List<IComments> loadCommentsUsingMemeId(String id) {

        return this.commentsDAO.getComments(id);

    }

    @Override
    public boolean setComment(String comment, String memeId, String userId) {

        return this.commentsDAO.setComment(comment, memeId, userId);

    }

}
