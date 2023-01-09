package com.group5.memegenerator.database;

import com.group5.memegenerator.model.IUserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUserDetailsDAO {
    String storeUserDetails(String firstname, String lastname, String gender, String dob, String email, String password);

    List<IUserDetails> getUser(String username);

    String encodeProfilePicture(MultipartFile memeTemplate);


    boolean editUserProfile(String firstName, String lastName, String gender, String dob, String userId);

    String getUserIdByUserName(String username);

    boolean updateProfilePic(MultipartFile image, String username);

    List<IUserDetails> loadAdminsFromDatabase();
}
