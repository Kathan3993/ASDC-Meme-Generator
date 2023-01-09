package com.group5.memegenerator.model;

import java.awt.Color;

import com.group5.memegenerator.database.DatabaseResponse;

public interface IMemeData {
    public Color getTopColour();

    public Color getBottomColour();

    public String addText() throws Exception;

    public DatabaseResponse saveMeme(String username, String memeCategory, String memeId, String memeImage);
}
