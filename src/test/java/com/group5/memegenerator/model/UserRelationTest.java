package com.group5.memegenerator.model;

import com.group5.memegenerator.database.DatabaseResponse;
import com.group5.memegenerator.database.IUserRelationDAO;
import com.group5.memegenerator.database.factory.UserRelationDAOFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserRelationTest {
    @Test
    public void incrementCountPositiveTest() {

        IUserRelationDAO userRelationDAO = UserRelationDAOFactory.instance().makeMockUserRelationDAO();

        String userId1 = "6";
        String userId2 = "1";
        UserRelationField field = UserRelationField.LIKES;
        Assertions.assertEquals(DatabaseResponse.SUCCESS, userRelationDAO.incrementCount(userId1, userId2, field));

    }

}
