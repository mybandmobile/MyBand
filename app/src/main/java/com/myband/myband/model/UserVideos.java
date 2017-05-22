package com.myband.myband.model;

import org.parceler.Parcel;

/**
 * Created by Escritorio on 22/05/2017.
 */

@Parcel
public class UserVideos {

    private long id;
    private User idUser;
    private String link;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
