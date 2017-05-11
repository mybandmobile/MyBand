package com.myband.myband.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Ranieri Aguiar on 11 de mai.
 */

public class User implements Parcelable {

    private long id;
    private String name;
    private String password;
    private String location;

    public User(){
    }

    protected User(Parcel in) {
        id = in.readLong();
        name = in.readString();
        password = in.readString();
        location = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(name);
        dest.writeString(password);
        dest.writeString(location);
    }

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }
}
