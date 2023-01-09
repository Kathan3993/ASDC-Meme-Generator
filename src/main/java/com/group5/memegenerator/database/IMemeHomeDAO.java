package com.group5.memegenerator.database;

import com.group5.memegenerator.model.IMeme;

import java.util.List;

public interface IMemeHomeDAO {

    List<IMeme> getMemeForHomePage(String UserID);
}
