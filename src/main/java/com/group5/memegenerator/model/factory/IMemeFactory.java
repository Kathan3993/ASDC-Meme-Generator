package com.group5.memegenerator.model.factory;

import com.group5.memegenerator.database.ICommentsDAO;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.IMemeDAO;
import com.group5.memegenerator.model.*;

public interface IMemeFactory {

    IMeme makeMeme();

    IMeme makeMeme(IMemeDAO memeDAO, ILike like);

    ILike makeLike(ILikeDAO likeDAO);

    IComments makeComment(ICommentsDAO commentsDAO);

    IMemeData makeMemeData(IMemeDAO memeDAO);

    IMemeData makeMemeData(String memePicture, String topColour, String bottomColour,
            String topText, String bottomText,
            float fontSize, int topXCoordinate, int topYCoordinate, int bottomXCoordinate, int bottomYCoordinate);

}
