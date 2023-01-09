package com.group5.memegenerator.model;

import com.group5.memegenerator.database.*;

import java.util.List;

public class MemeUser implements IMemeUser{

    private IUserProfileDAO userProfileDAO;

    public MemeUser(IUserProfileDAO userProfileDAO){
        this.userProfileDAO = userProfileDAO;

    }
    @Override
    public List<IMeme> loadMemesInUserProfile(String userId){

            return userProfileDAO.getMemeForUserProfile(userId);

    }
}
