package com.myband.myband.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Escritorio on 21/05/2017.
 */

public class UserDescription implements Parcelable{

    private Long userId;
    private String description;

    public UserDescription(){

    }

    protected UserDescription(Parcel in) {
        description = in.readString();
    }

    public static final Creator<UserDescription> CREATOR = new Creator<UserDescription>() {
        @Override
        public UserDescription createFromParcel(Parcel in) {
            return new UserDescription(in);
        }

        @Override
        public UserDescription[] newArray(int size) {
            return new UserDescription[size];
        }
    };

    public Long getUser_id() {
        return userId;
    }

    public void setUser_id(Long user_id) {
        this.userId = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
    }
}
