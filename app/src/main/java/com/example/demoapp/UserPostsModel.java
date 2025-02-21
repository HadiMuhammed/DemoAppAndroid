package com.example.demoapp;
public class UserPostsModel {
    private String imgUrl;
    private String description;
    private String userName;
    private String profilePic;

    // constructor class.
    public UserPostsModel(String imgUrl, String description, String userName, String profilePic) {
        this.imgUrl = imgUrl;
        this.description = description;
        this.userName = userName;
        this.profilePic = profilePic;
    }

    // getter and setter methods.
    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}
