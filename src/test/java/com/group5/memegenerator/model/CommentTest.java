package com.group5.memegenerator.model;

import com.group5.memegenerator.database.ICommentsDAO;
import com.group5.memegenerator.database.factory.MemeDAOFactory;
import com.group5.memegenerator.model.factory.MemeFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CommentTest {

    @Test
    public void loadCommentsUsingMemeIdPositiveTest() {

        ICommentsDAO commentsDAO = MemeDAOFactory.instance().makeMockCommentsDAO();
        IComments comments = MemeFactory.instance().makeComment(commentsDAO);

        List<IComments> commentsList = comments.loadCommentsUsingMemeId("1");
        Assertions.assertEquals(4, commentsList.size());

    }

    @Test
    public void loadCommentsUsingMemeIdNegativeTest() {

        ICommentsDAO commentsDAO = MemeDAOFactory.instance().makeMockCommentsDAO();
        IComments comments = MemeFactory.instance().makeComment(commentsDAO);

        List<IComments> commentsList = comments.loadCommentsUsingMemeId("2");
        Assertions.assertEquals(null, commentsList);

    }

    @Test
    public void setCommentPositiveTest() {

        ICommentsDAO commentsDAO = MemeDAOFactory.instance().makeMockCommentsDAO();
        IComments comments = MemeFactory.instance().makeComment(commentsDAO);

        boolean result;
        try {
            result = comments.setComment("1234", "123", "12");
        } catch (Exception e) {
            result = false;
        }

        Assertions.assertEquals(true, result);

    }

    @Test
    public void setCommentNegativeTest() {

        ICommentsDAO commentsDAO = MemeDAOFactory.instance().makeMockCommentsDAO();
        IComments comments = MemeFactory.instance().makeComment(commentsDAO);

        boolean result;
        try {
            result = comments.setComment(null, "123", "12");
        } catch (Exception e) {
            result = false;
        }

        Assertions.assertEquals(false, result);

    }

}
