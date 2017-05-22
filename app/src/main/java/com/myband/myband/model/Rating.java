package com.myband.myband.model;

import org.parceler.Parcel;

/**
 * Created by Escritorio on 22/05/2017.
 */

@Parcel
public class Rating {

    private Long id;
    private Service idService;
    private Boolean requester;
    private int rating;
    private String comment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Service getIdService() {
        return idService;
    }

    public void setIdService(Service idService) {
        this.idService = idService;
    }

    public Boolean getRequester() {
        return requester;
    }

    public void setRequester(Boolean requester) {
        this.requester = requester;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
