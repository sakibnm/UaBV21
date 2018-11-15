package com.example.mmiazi.uabv21;

public class User {
    String userName, userEmail;
    String userPhoto;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    User(String userName, String userEmail, String userPhoto) {
        this.userEmail=userEmail;
        this.userName=userName;
        this.userPhoto=userPhoto;
    }


    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }
}
