package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.ICommentsDAO;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.IMemeDAO;
import com.group5.memegenerator.model.*;

public class MemeFactory implements IMemeFactory {

    private static IMemeFactory memeFactory;

    private MemeFactory() {

    }

    public static IMemeFactory instance() {
        if (memeFactory == null) {
            memeFactory = new MemeFactory();
        }
        return memeFactory;
    }

    @Override
    public IMeme makeMeme() {
        return new Meme();
    }

    @Override
    public IMeme makeMeme(IMemeDAO memeDAO, ILike like) {
        return new Meme(memeDAO, like);
    }

    @Override
    public ILike makeLike(ILikeDAO likeDAO) {
        return new Like(likeDAO);
    }

    @Override
    public IComments makeComment(ICommentsDAO commentsDAO) {
        return new Comments(commentsDAO);
    }

    @Override
    public IMemeData makeMemeData(IMemeDAO memeDAO) {
        return new MemeData(memeDAO);
    }

    @Override
    public IMemeData makeMemeData(String memePicture, String topColour, String bottomColour,
            String topText, String bottomText,
            float fontSize, int topXCoordinate, int topYCoordinate, int bottomXCoordinate, int bottomYCoordinate) {

        return new MemeData(memePicture, topColour, bottomColour,
                topText, bottomText,
                fontSize, topXCoordinate, topYCoordinate, bottomXCoordinate, bottomYCoordinate);
    }

}
