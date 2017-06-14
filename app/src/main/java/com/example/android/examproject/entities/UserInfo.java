package com.example.android.examproject.entities;

/**
 * Created by Android on 6/12/2017.
 */

public class UserInfo {

    private String imageUrl;
    private String name;
    private String address;
    private String email;

    //empty constructor for testing
    public UserInfo() {
    }

    public UserInfo(String image, String name, String address, String email) {
        this.imageUrl = image;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
