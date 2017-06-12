package com.myband.myband.model;

import org.parceler.Parcel;

import java.util.Date;

import static org.parceler.Parcel.Serialization.BEAN;

/**
 * Created by Escritorio on 22/05/2017.
 */

@Parcel(BEAN)
public class Event {

    private Long id;
    private User requester;
    private User provider;
    private SubGenre subGenre;
    private Date initialDate;
    private Date finalDate;

    // Codigos para AsyncTask
    public static final int createEvent = 1;
    public static final int updateEvent = 2;
    public static final int deleteEvent = 3;

    public Event() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public User getProvider() {
        return provider;
    }

    public void setProvider(User provider) {
        this.provider = provider;
    }

    public SubGenre getSubGenre() {
        return subGenre;
    }

    public void setSubGenre(SubGenre subGenre) {
        this.subGenre = subGenre;
    }

    public Date getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(Date initialDate) {
        this.initialDate = initialDate;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }
}
