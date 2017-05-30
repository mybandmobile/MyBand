package com.myband.myband.model;

import org.parceler.Parcel;

/**
 * Created by ranie on 30 de mai.
 */

@Parcel
public class SubGenre {

    private Long id;
    private Genre genre;
    private String name;

    public SubGenre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
