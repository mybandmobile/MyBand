package com.myband.myband.model;

import org.parceler.Parcel;

/**
 * Created by Escritorio on 22/05/2017.
 */

@Parcel
public class Rating {

    private Long id;
    private Event event;
    private boolean requester;
    private int rating;
    private String comment;

    public Rating() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isRequester() {
        return requester;
    }

    public void setRequester(boolean requester) {
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
