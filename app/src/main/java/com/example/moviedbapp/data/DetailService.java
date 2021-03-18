package com.example.moviedbapp.data;

import com.example.moviedbapp.model.DetailMovieRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DetailService {
    @GET("{movie_id}/credits")
    Call<DetailMovieRes> getCast(
            @Path("movie_id") String id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}
