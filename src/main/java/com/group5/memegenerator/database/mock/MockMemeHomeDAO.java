package com.group5.memegenerator.database.mock;

import com.group5.memegenerator.database.IMemeHomeDAO;
import com.group5.memegenerator.model.IMeme;

import java.util.ArrayList;
import java.util.List;

public class MockMemeHomeDAO implements IMemeHomeDAO {

    @Override
    public List<IMeme> getMemeForHomePage(String userID) {

        if (userID == null) {

            return null;

        }
        return new ArrayList<>();
    }
}
