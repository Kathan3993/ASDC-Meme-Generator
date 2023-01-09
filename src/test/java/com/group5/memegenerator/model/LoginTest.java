package com.group5.memegenerator.model;

import com.group5.memegenerator.database.ILoginDAO;
import com.group5.memegenerator.database.factory.LoginDAOFactory;
import com.group5.memegenerator.model.factory.LoginFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoginTest {

    @Test
    public void loginPositiveTest(){

        String username = "abc";

        String password = "abc";

        ILoginDAO loginDAO = LoginDAOFactory.instance().makeMockLoginDAO();
        ILogin login = LoginFactory.instance().makeLogin(loginDAO);

        String response = login.login(username, password);

        assertEquals("abc", username);
    }

    @Test
    public void loginNegativeTest(){

        String username = "abcdef";

        ILoginDAO loginDAO = LoginDAOFactory.instance().makeMockLoginDAO();
        ILogin login = LoginFactory.instance().makeLogin(loginDAO);

        String response = login.login(username, null);

        assertEquals("", response);
    }
}
