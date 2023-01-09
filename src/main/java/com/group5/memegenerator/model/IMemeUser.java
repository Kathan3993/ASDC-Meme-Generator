package com.group5.memegenerator.model;

import java.util.List;

public interface IMemeUser {

    public List<IMeme> loadMemesInUserProfile(String userId);

}
