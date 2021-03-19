package com.example.moviedbapp.ui.home;

import com.example.moviedbapp.ui.notifications.model.GenreMovieRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("movie")
    Call<GenreMovieRes>getMovies(
        @Query("api_key") String key,
        @Query("sort_by") String sortBy,
        @Query("page") String page
    );
}
