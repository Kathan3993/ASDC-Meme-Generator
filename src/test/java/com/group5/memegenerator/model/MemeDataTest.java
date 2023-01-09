package com.group5.memegenerator.model;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IMemeDAO;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.model.factory.MemeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class MemeDataTest {
    IMemeDAO memeDao;
    IMemeData memeData;
    IMemeData memeData2;

    public MemeDataTest() {
        memeDao = MemeDAOFactory.instance().makeMockMemeDAO();
        memeData = MemeFactory.instance().makeMemeData("memeName", "white", "black", "topText", "bottomText", 10f, 100,
                150, 300, 150);
        memeData2 = MemeFactory.instance().makeMemeData(memeDao);
    }

    @Test
    public void getTopColourText() {
        Color c = memeData.getTopColour();
        Assertions.assertEquals(c, Color.WHITE);
    }

    @Test
    public void getBottomColourText() {
        Color c = memeData.getBottomColour();
        Assertions.assertEquals(c, Color.BLACK);
    }

    @Test
    public void addTextTest() {
        String imageText = "";
        try {
            imageText = memeData.addText();
            Assertions.assertTrue(imageText.length() > 0);
        } catch (Exception e) {
            Assertions.assertTrue(imageText.length() == 0);
        }
    }

    @Test
    public void saveMemeTest() {

        DatabaseResponse result = memeData2.saveMeme("", "memeCategory", "memeId", "memeImage");
        Assertions.assertEquals(result, DatabaseResponse.ERROR);

        result = memeData2.saveMeme("userName", "memeCategory", "memeId", "memeImage");
        Assertions.assertEquals(result, DatabaseResponse.SUCCESS);
    }
}
