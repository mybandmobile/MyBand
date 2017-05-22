package com.myband.myband.model;

import org.parceler.Parcel;

/**
 * Created by Ranieri Aguiar on 11 de mai.
 */

@Parcel
public class User  {

    private Long id;
    private String userName;
    private String password;

    public User(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
