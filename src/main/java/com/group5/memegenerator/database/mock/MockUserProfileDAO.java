package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.IUserProfileDAO;
import com.group5.memegenerator.model.IMeme;

import java.util.ArrayList;
import java.util.List;

public class MockUserProfileDAO implements IUserProfileDAO {
    @Override
    public List<IMeme> getMemeForUserProfile(String username) {

        if (username == null) {

            return null;
        }
        return new ArrayList<>();
    }
}
