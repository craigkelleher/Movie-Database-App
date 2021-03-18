package com.example.moviedbapp.ui.notifications.data;

import com.example.moviedbapp.ui.notifications.model.GenreMovieRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("movie")
    Call<GenreMovieRes> getMovieByGenre(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("sort_by") String sort,
            @Query("include_adult") String adult,
            @Query("include_video") String video,
            @Query("page") String page,
            @Query("with_genres") String genre
    );
}
