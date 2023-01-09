package com.group5.memegenerator.model;

import org.springframework.web.multipart.MultipartFile;

public interface IUserDetails {
    void setProfilePicture(String profilePicture);

    int getId();

    void setId(int id);

    String getFirstname();

    void setFirstname(String firstname);

    String getLastname();

    void setLastname(String lastname);

    String getDob();

    void setDob(String dob);

    String getEmail();

    void setEmail(String email);

    String getPassword();

    void setPassword(String password);

    UserDetails.ROLE getRole();

    void setRole(UserDetails.ROLE role);

    IUserDetails loadUser(String userId);

    boolean updateProfilePicture(MultipartFile image, String userId);

    boolean editProfile(String firstName, String lastName, String gender, String dob,String userId);

    String getUserIdByUserName(String username);

}
