package com.myband.myband.model;

import org.parceler.Parcel;

import static org.parceler.Parcel.Serialization.BEAN;

/**
 * Created by Ranieri Aguiar on 11 de mai.
 */

@Parcel(BEAN)
public class User {

    private Long id;
    private Category category;
    private String userName;
    private String login;
    private String password;
    private double latitude;
    private double longitude;
    private int statusCode;
    private boolean autoLogin;

    public static final int loginOk = 1;
    public static final int userAndPasswordDoesntMatch = 2;
    public static final int userDoesntExist = 3;
    public static final int userAlreadyExists = 4;
    public static final int serverError = 5;
    public static final int updateOk = 6;
    public static final int deleteOk = 7;

    // Codigos para AsyncTask
    public static final int loginAccount = 1;
    public static final int createAccount = 2;
    public static final int updateAccount = 3;
    public static final int deleteAccount = 4;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAutoLogin() {
        return autoLogin;
    }

    public void setAutoLogin(boolean autoLogin) {
        this.autoLogin = autoLogin;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
