package com.group5.memegenerator.model;

import com.group5.memegenerator.database.IUserDetailsDAO;
import com.group5.memegenerator.database.factory.UserDetailsDAOFactory;
import com.group5.memegenerator.model.factory.UserDetailsFactory;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserDetailsTest {

    @Test
    public void loadUserPositiveTest() {

        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeMockUserDetailsDAOFactory();
        IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);

        String userId = "6";

        IUserDetails user = userDetails.loadUser(userId);
        System.out.println(user);
        Assertions.assertNotEquals(null, user);

    }

    @Test
    public void loadUserNegativeTest() {

        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeMockUserDetailsDAOFactory();
        IUserDetails userDetails = UserDetailsFactory.instance().makeUserDetails(userDetailsDAO);

        String userId = "999";

        IUserDetails user = userDetails.loadUser(userId);

        Assertions.assertEquals(null, user);

    }

    @Test
    public void getUserIdByNamePositiveTest() {
        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeMockUserDetailsDAOFactory();
        String userName = "kathan@dal.ca";
        Assertions.assertEquals("kathan@dal.ca", userDetailsDAO.getUserIdByUserName(userName));

    }

    @Test
    public void getUserIdByNameNegativeTest() {
        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeMockUserDetailsDAOFactory();
        String userName = "";
        Assertions.assertEquals(null, userDetailsDAO.getUserIdByUserName(userName));

    }

    @Test
    public void updateProfilePicturePositiveTest() {
        try {
            Resource resource = new ClassPathResource("static/images/test1.jpg");
            FileInputStream file = new FileInputStream(resource.getFile());
            MultipartFile multipartFile = new MockMultipartFile(
                    "resource",
                    resource.getFile().getName(),
                    "image/jpg",
                    IOUtils.toByteArray(file)
            );
            IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeMockUserDetailsDAOFactory();
            String userName = "6";
            boolean result = userDetailsDAO.updateProfilePic(multipartFile, userName);
            Assertions.assertEquals(true,result);


        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
    }

    @Test
    public void updateProfilePictureNegativeTest() {

            IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeMockUserDetailsDAOFactory();
            String userName = "";
            boolean result = userDetailsDAO.updateProfilePic(null, userName);

            Assertions.assertEquals(false,result);
    }

    @Test
    public void editProfilePositiveTest()
    {
        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeMockUserDetailsDAOFactory();
        String firstName = "kathan";
        String lastName = "patel";
        String gender = "Male";
        String dob = "2000-07-13";
        String userId="6";
        boolean result = userDetailsDAO.editUserProfile(firstName,lastName,gender,dob,userId);

        Assertions.assertEquals(true,result);
    }
    @Test
    public void editProfileNegativeTest()
    {
        IUserDetailsDAO userDetailsDAO = UserDetailsDAOFactory.instance().makeMockUserDetailsDAOFactory();
        String firstName = "";
        String lastName = "";
        String gender = "";
        String dob = "";
        String userId="";
        boolean result = userDetailsDAO.editUserProfile(firstName,lastName,gender,dob,userId);

        Assertions.assertEquals(false,result);
    }
}
