package com.myband.myband.model;

import org.parceler.Parcel;

/**
 * Created by Escritorio on 22/05/2017.
 */

@Parcel
public class UserAvailability {

    private Long id;
    private User idUser;
    private int day;

    public final static int segunda = 1;
    public final static int terca = 2;
    public final static int quarta = 3;
    public final static int quinta = 4;
    public final static int sexta = 5;
    public final static int sabado = 6;
    public final static int domingo = 7;

    public UserAvailability() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}
