package com.example.moviedbapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class Cast implements Parcelable{
    @SerializedName("name")
    private String name;
    @SerializedName("popularity")
    private Double popularity;
    @SerializedName("profile_path")
    private String profilePath;
    @SerializedName("character")
    private String character;

    public Cast(String name, Double popularity, String profilePath, String character){
        this.name = name;
        this.popularity = popularity;
        this.profilePath = profilePath;
        this.character = character;
    }

    public Cast(){

    }

    public static final Comparator<Cast> BY_NAME_ALPHABETICAL = new Comparator<Cast>() {
        @Override
        public int compare(Cast cast, Cast t1) {

            return cast.name.compareTo(t1.name);
        }
    };

    public String getName() {
        return  name;
    }

    public void setName(String posterPath) {
        this.name = name;
    }

    public Double getPopularity() {
        return  popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getProfilePath() {
        return  profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getCharacter() {
        return  character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeValue(this.popularity);
        dest.writeString(this.character);
        dest.writeString(this.profilePath);
    }

    protected Cast(Parcel in) {
        this.name = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.character = in.readString();
        this.profilePath = in.readString();
    }


    public static final Parcelable.Creator<Cast> CREATOR = new Parcelable.Creator<Cast>() {
        @Override
        public Cast createFromParcel(Parcel source) {
            return new Cast(source);
        }

        @Override
        public Cast[] newArray(int size) {
            return new Cast[size];
        }
    };

}
