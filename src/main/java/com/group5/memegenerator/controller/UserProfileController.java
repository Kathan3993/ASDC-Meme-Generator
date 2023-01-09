package com.group5.memegenerator.controller;

import com.group5.memegenerator.database.*;
import com.group5.memegenerator.database.factory.*;
import com.group5.memegenerator.model.*;
import com.group5.memegenerator.model.factory.FollowFactory;
import com.group5.memegenerator.model.factory.MemeFactory;
import com.group5.memegenerator.model.factory.MemeHomeFactory;
import com.group5.memegenerator.model.factory.UserDetailsFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserProfileController {

    @GetMapping("/user-profile")
    public ModelAndView loadHomePageToUser(@CookieValue(value = "id", defaultValue = "6") String id) {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory().makeDataBaseOperation();

        ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
        IUserProfileDAO userProfileDAO = MemeHomeDAOFactory.instance().makeUserProfileDAO(databaseOperation, likeDAO);

        IMemeUser memeUser = MemeHomeFactory.instance().makeMemeUser(userProfileDAO);

        List<IMeme> usersMemes = memeUser.loadMemesInUserProfile(id);
        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeUserDetailsDAO(databaseOperation);
        IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);
        IFollowDAO followDAO = FollowDAOFactory.instance().makeFollowDAO(databaseOperation);
        IFollow follow = FollowFactory.instance().makeFollow(followDAO);
        String followingCount = follow.followingCount(id);
        String followerCount = follow.followerCount(id);
        userDetails = userDetails.loadUser(id);

        userDetails.getFirstname();
        int postNum = usersMemes.size();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("detail", userDetails);
        modelAndView.addObject("images", usersMemes);
        modelAndView.addObject("post", postNum);
        modelAndView.addObject("followingCount", followingCount);
        modelAndView.addObject("followerCount", followerCount);
        modelAndView.addObject("isSelf", true);
        modelAndView.addObject("isFollowed", false);
        modelAndView.setViewName("user-profile");

        return modelAndView;

    }

    @GetMapping("/user-profiles/{id}")
    public ModelAndView loadProfilePageToUser(@CookieValue(value = "id", defaultValue = "6") String userId1,
                                              @PathVariable("id") int id) {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);
        IUserProfileDAO userProfileDAO = MemeHomeDAOFactory.instance().makeUserProfileDAO(databaseOperation,
                likeDAO);
        IMemeUser memeUser = MemeHomeFactory.instance().makeMemeUser(userProfileDAO);
        List<IMeme> usersMemes = memeUser.loadMemesInUserProfile(String.valueOf(id));

        int postNum = usersMemes.size();
        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeUserDetailsDAO(databaseOperation);
        IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);
        userDetails = userDetails.loadUser(String.valueOf(id));

        IFollowDAO followDAO = FollowDAOFactory.instance().makeFollowDAO(databaseOperation);
        IFollow follow = FollowFactory.instance().makeFollow(followDAO);
        String followingCount = follow.followingCount("" + id);
        String followerCount = follow.followerCount("" + id);
        boolean isFollowed = follow.isFollowing("" + userId1, "" + id);
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("userId", id);
        modelAndView.addObject("post", postNum);
        modelAndView.addObject("images", usersMemes);
        modelAndView.addObject("detail", userDetails);
        modelAndView.addObject("followingCount", followingCount);
        modelAndView.addObject("followerCount", followerCount);
        modelAndView.addObject("isSelf", "" + id == usersMemes.get(0).getUserId());
        modelAndView.addObject("isFollowed", isFollowed);

        modelAndView.setViewName("user-profile");

        return modelAndView;
    }

    @PostMapping("/upload-profile-picture")
    public ModelAndView updateProfilePicture(@CookieValue(value = "userId", defaultValue = "6") String username,
                                             @RequestParam("image") MultipartFile image) {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);

        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeUserDetailsDAO(databaseOperation);
        IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);
        userDetails.updateProfilePicture(image, username);

        return new ModelAndView("redirect:/user-profiles/" + username);
    }

    @PostMapping(value = "/meme-like/{id}")
    public ModelAndView addLikeToMeme(@PathVariable("id") int id, HttpServletRequest request) {

        String userId = request.getParameter("userId");

        //String requestURL = request.getParameter("url");
        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();

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

        String userprofileid = request.getParameter("userprofileid");

        if (userprofileid == null || userprofileid.equals("")) {

            return new ModelAndView("redirect:/user-profile");
        }

        return new ModelAndView("redirect:/user-profiles/" + userprofileid);

    }


    @GetMapping("/editProfile")
    public String editProfile() {
        return "editProfile.html";
    }

    @PostMapping("/edit-profile")
    public ModelAndView editProfile(@CookieValue(value = "userId", defaultValue = "6") String userId,
                                             @RequestParam("firstname") String firstName,@RequestParam("lastname") String lastName,@RequestParam("dob") String dob,@RequestParam("gender") String gender) {

        IDatabaseOperation databaseOperation = DatabaseOperationFactory.databaseOperationFactory()
                .makeDataBaseOperation();
        ILikeDAO likeDAO = MemeDAOFactory.instance().makeLikeDAO(databaseOperation);

        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeUserDetailsDAO(databaseOperation);
        IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);
        userDetails.editProfile(firstName, lastName, dob,gender,userId);

        return new ModelAndView("redirect:/user-profiles/" + userId);
    }

}

