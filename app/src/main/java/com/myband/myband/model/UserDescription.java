package com.myband.myband.model;

import org.parceler.Parcel;

import static org.parceler.Parcel.Serialization.BEAN;

/**
 * Created by Escritorio on 21/05/2017.
 */

@Parcel(BEAN)
public class UserDescription {

    private User idUser;
    private String description;

    public UserDescription() {
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
