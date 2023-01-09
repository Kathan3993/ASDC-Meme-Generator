package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.ICommentsDAO;
import com.group5.memegenerator.database.IDatabaseOperation;
import com.group5.memegenerator.database.ILikeDAO;
import com.group5.memegenerator.database.IMemeDAO;
import com.group5.memegenerator.database.factory.DatabaseOperationFactory;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.model.IComments;
import com.group5.memegenerator.model.ILike;
import com.group5.memegenerator.model.IMeme;
import com.group5.memegenerator.model.factory.MemeFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MemeCommentLikeController {

    @GetMapping("/explore-section/{userId}/{memeId}")
    public ModelAndView loadMemeCommentsAndLikesToUser(@PathVariable("memeId") int id, @PathVariable("userId") String userId) {

        try {

            IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();

            IMemeDAO memeDAO = MemeDAOFactory.instance().makeMemeDAO(databaseOperation);
            ICommentsDAO commentsDAO = MemeDAOFactory.instance().makeCommentsDAO(databaseOperation);

            ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
            ILike like = MemeFactory.instance().makeLike(likeDAO);

            IMeme meme = MemeFactory.instance().makeMeme(memeDAO, like);
            IComments comments = MemeFactory.instance().makeComment(commentsDAO);


            meme = meme.loadMeme(String.valueOf(id), comments);

            if (meme == null) {

                ModelAndView modelAndView = new ModelAndView();

                modelAndView.setViewName("unable-to-load-meme");
                return modelAndView;

            }

            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("meme", meme);

            modelAndView.setViewName("explore-section-comment");
            return modelAndView;

        } catch (Exception e) {

            ModelAndView modelAndView = new ModelAndView();

            modelAndView.setViewName("server-down");
            return modelAndView;
        }
    }

    @PostMapping("/explore-section/comment/{userId}/{memeId}")
    public ModelAndView addCommentToMeme(@PathVariable("memeId") int id, @PathVariable("userId") String userId, HttpServletRequest request) {

        String comment = request.getParameter("comment");
//        String userId = request.getParameter("userId");

        String requestURL = request.getParameter("url");

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();

        IMemeDAO memeDAO = MemeDAOFactory.instance().makeMemeDAO(databaseOperation);
        ICommentsDAO commentsDAO = MemeDAOFactory.instance().makeCommentsDAO(databaseOperation);

        ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
        ILike like = MemeFactory.instance().makeLike(likeDAO);

        IMeme meme = MemeFactory.instance().makeMeme(memeDAO, like);
        IComments comments = MemeFactory.instance().makeComment(commentsDAO);


        meme = meme.loadMeme(String.valueOf(id), comments);

        if (meme == null) {

            ModelAndView modelAndView = new ModelAndView();

            modelAndView.setViewName("unable-to-load-meme");
            return modelAndView;

        }

        comments.setComment(comment, meme.getMemeId(), userId);

        return new ModelAndView("redirect:/explore-section/" + userId + "/" + meme.getMemeId());

    }

    @PostMapping("/explore-section/like/{userId}/{memeId}")
    public ModelAndView addLikeToMeme(@PathVariable("memeId") int id, @PathVariable("userId") String userId, HttpServletRequest request) {

        String requestURL = request.getParameter("url");

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();

        IMemeDAO memeDAO = MemeDAOFactory.instance().makeMemeDAO(databaseOperation);
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
        ICommentsDAO commentsDAO = MemeDAOFactory.instance().makeCommentsDAO(databaseOperation);

        ILike like = MemeFactory.instance().makeLike(likeDAO);
        IMeme meme = MemeFactory.instance().makeMeme(memeDAO, like);

        IComments comments = MemeFactory.instance().makeComment(commentsDAO);

        meme = meme.loadMeme(String.valueOf(id), comments);

        if (meme == null) {

            ModelAndView modelAndView = new ModelAndView();

            modelAndView.setViewName("unable-to-load-meme");
            return modelAndView;

        }

        like.addLikeToMeme(meme.getMemeId(), userId);

        if (requestURL.contains("explore-section")) {

            return new ModelAndView("redirect:/explore-section/" + userId + "/" + meme.getMemeId());
        }

        return new ModelAndView("redirect:/home");
    }

}
