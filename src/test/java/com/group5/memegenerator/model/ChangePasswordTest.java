package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IChangePasswordDAO;
import com.group5.memegenerator.database.factory.ChangePasswordDAOFactory;
import com.group5.memegenerator.model.factory.ChangePasswordFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChangePasswordTest {

    @Test
    public void passwordChanged(){

        String email = "abc@dal.ca";

        String password = "abcdef";

        IChangePasswordDAO changePasswordDAO = ChangePasswordDAOFactory.instance().makeMockChangePasswordDAO();
        IChangePassword changePassword = ChangePasswordFactory.instance().makeChangePassword(changePasswordDAO);

        changePassword.setPassword(email, password);

        assertEquals(password, "abcdef");

    }

    @Test
    public void passwordNotChanged(){

        String email = "abc@dal.ca";

        String password = "abc";

        IChangePasswordDAO cChangePasswordDAO = ChangePasswordDAOFactory.instance().makeMockChangePasswordDAO();
        IChangePassword changePassword = ChangePasswordFactory.instance().makeChangePassword(cChangePasswordDAO);

        changePassword.setPassword(email, password);

        assertEquals(password, "abc");

    }
}
