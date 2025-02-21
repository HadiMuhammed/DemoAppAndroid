package com.example.demoapp;

public class UserPostsModel {
    private String imgUrl;
    private String description;
    private String userName;
    private String profilePic;
    private String userBio; // New field for user bio
    private String userLocation; // New field for user location

    // Updated constructor class to include userBio and userLocation
    public UserPostsModel(String imgUrl, String description, String userName, String profilePic, String userBio, String userLocation) {
        this.imgUrl = imgUrl;
        this.description = description;
        this.userName = userName;
        this.profilePic = profilePic;
        this.userBio = userBio;
        this.userLocation = userLocation;
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

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }
}
