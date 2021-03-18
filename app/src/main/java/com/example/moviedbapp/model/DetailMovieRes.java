package com.example.moviedbapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.moviedbapp.ui.notifications.model.Movie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailMovieRes implements Parcelable {
    @SerializedName("id")
    private int id;
    @SerializedName("cast")
    private List<Cast> cast;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Cast> getResults() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }


    protected DetailMovieRes(Parcel in) {
        this.id = in.readInt();
        this.cast = in.createTypedArrayList(Cast.CREATOR);
    }

    public static final Creator<DetailMovieRes> CREATOR = new Creator<DetailMovieRes>() {
        @Override
        public DetailMovieRes createFromParcel(Parcel in) {
            return new DetailMovieRes(in);
        }

        @Override
        public DetailMovieRes[] newArray(int size) {
            return new DetailMovieRes[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeTypedList(this.cast);
    }
}
