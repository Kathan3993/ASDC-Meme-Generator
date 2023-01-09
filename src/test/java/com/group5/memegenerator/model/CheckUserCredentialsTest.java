//package com.group5.memegenerator.model;
//
//import com.group5.memegenerator.database.ICheckUserCredentialsDAO;
//import com.group5.memegenerator.database.factory.CheckUserCredentialsDAOFactory;
//import com.group5.memegenerator.model.factory.CheckUserCredentialFactory;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@SpringBootTest
//public class CheckUserCredentialsTest {
//
//    @Test
//    public void checkEmailValidTest(){
//
//        String username = "abc";
//
//        ICheckUserCredentialsDAO  checkUserCredentialsDAO = CheckUserCredentialsDAOFactory.instance().makeMockCheckUserCredentialsDAO();
//        ICheckUserCredentials checkUserCredentials = CheckUserCredentialFactory.instance().makeCheckCredentials(checkUserCredentialsDAO);
//
//        boolean response = checkUserCredentials.checkEmail(username);
//
//        assertEquals(username, response);
////        ILoginDAO loginDAO = LoginDAOFactory.instance().makeMockLoginDAO();
////        ILogin login = LoginFactory.instance().makeLogin(loginDAO);
////
////        String response = login.login(username);
//
////        assertEquals(username, response);
//    }
//}
