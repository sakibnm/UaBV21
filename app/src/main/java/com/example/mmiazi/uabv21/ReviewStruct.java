package com.example.mmiazi.uabv21;

import java.io.Serializable;

public class ReviewStruct implements Serializable {
    String name;
    float rating;
    String productName;
    String comment;
    String userPhoto;
    String productPhoto;
    boolean nameIsChecked;
    boolean userPhotoIsChecked;

    ReviewStruct(){}

    public ReviewStruct(String name, float rating, String productName, String comment, String userPhoto, String productPhoto) {
        this.name = name;
        this.rating = rating;
        this.productName = productName;
        this.comment = comment;
        this.userPhoto = userPhoto;
        this.productPhoto = productPhoto;
    }

    @Override
    public String toString() {
        return "ReviewStruct{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", productName='" + productName + '\'' +
                ", comment='" + comment + '\'' +
                ", userPhoto='" + userPhoto + '\'' +
                ", productPhoto='" + productPhoto + '\'' +
                ", nameIsChecked=" + nameIsChecked +
                ", userPhotoIsChecked=" + userPhotoIsChecked +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public String getProductPhoto() {
        return productPhoto;
    }

    public void setProductPhoto(String productPhoto) {
        this.productPhoto = productPhoto;
    }

    public boolean isNameIsChecked() {
        return nameIsChecked;
    }

    public void setNameIsChecked(boolean nameIsChecked) {
        this.nameIsChecked = nameIsChecked;
    }

    public boolean isUserPhotoIsChecked() {
        return userPhotoIsChecked;
    }

    public void setUserPhotoIsChecked(boolean userPhotoIsChecked) {
        this.userPhotoIsChecked = userPhotoIsChecked;
    }
}
