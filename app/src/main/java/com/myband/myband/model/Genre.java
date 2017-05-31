package com.myband.myband.model;

import org.parceler.Parcel;

import static org.parceler.Parcel.Serialization.BEAN;

/**
 * Created by ranie on 30 de mai.
 */

@Parcel(BEAN)
public class Genre {

    private Long id;
    private String name;

    public Genre() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
