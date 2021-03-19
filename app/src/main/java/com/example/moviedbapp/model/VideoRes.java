package com.example.moviedbapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideoRes implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("results")
    private List<Video> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setCast(List<Video> results) {
        this.results = results;
    }


    protected VideoRes(Parcel in) {
        this.id = in.readInt();
        this.results = in.createTypedArrayList(Video.CREATOR);
    }

    public static final Creator<VideoRes> CREATOR = new Creator<VideoRes>() {
        @Override
        public VideoRes createFromParcel(Parcel in) {
            return new VideoRes(in);
        }

        @Override
        public VideoRes[] newArray(int size) {
            return new VideoRes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeTypedList(this.results);
    }
}
