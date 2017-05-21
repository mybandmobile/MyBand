package com.myband.myband.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Neylor on 21/05/2017.
 */

public class UserLocation implements Parcelable{
    private Long userId;
    private String coordinates;

    public UserLocation(){}

    protected UserLocation(Parcel in) {
        coordinates = in.readString();
    }

    public static final Creator<UserLocation> CREATOR = new Creator<UserLocation>() {
        @Override
        public UserLocation createFromParcel(Parcel in) {
            return new UserLocation(in);
        }

        @Override
        public UserLocation[] newArray(int size) {
            return new UserLocation[size];
        }
    };

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(coordinates);
    }
}
