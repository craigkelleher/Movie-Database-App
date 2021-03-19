package com.example.moviedbapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class Video implements Parcelable {
    @SerializedName("key")
    private String key;

    public Video(String key){
        this.key = key;
    }

    public static final Comparator<Video> BY_NAME_ALPHABETICAL = new Comparator<Video>() {
        @Override
        public int compare(Video video, Video t1) {

            return video.key.compareTo(t1.key);
        }
    };

    public String getKey() {
        return  key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    protected Video(Parcel in) {
        this.key = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.key);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Video> CREATOR = new Creator<Video>() {
        @Override
        public Video createFromParcel(Parcel in) {
            return new Video(in);
        }

        @Override
        public Video[] newArray(int size) {
            return new Video[size];
        }
    };
}
