package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IMemeDAO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Meme implements Comparable<Meme>, IMeme {
    @Getter
    @Setter
    private String memeId;
    @Getter
    @Setter
    private String memePicture;
    @Getter
    @Setter
    private long memeLikes;
    @Getter
    @Setter
    private String userId;
    @Getter
    @Setter
    private List<IComments> memeComments;
    @Getter
    @Setter
    private double memeRank;
    @Getter
    @Setter
    private String categoryId;

    private IMemeDAO memeDAO;

    private ILike like;

    public Meme() {

    }

    public Meme(IMemeDAO memeDAO, ILike like) {
        this.memeDAO = memeDAO;
        this.like = like;
    }

    @Override
    public int compareTo(Meme o) {
        return Double.compare(memeRank, o.memeRank);
    }

    @Override
    public boolean loadComments(IComments comments) {

        this.memeComments = comments.loadCommentsUsingMemeId(this.memeId);
        return true;
    }

    @Override
    public IMeme loadMeme(String id, IComments comments) {

        try {

            IMeme meme = memeDAO.loadMemeById(String.valueOf(id), like.getLikeDAO());
            meme.loadComments(comments);

            return meme;

        } catch (Exception e) {

            return null;
        }
    }
}
