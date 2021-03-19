package com.example.moviedbapp.data;

import com.example.moviedbapp.model.DetailMovieRes;
import com.example.moviedbapp.model.VideoRes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VideoService {
    @GET("{movie_id}/videos")
    Call<VideoRes> getVideo(
            @Path("movie_id") String id,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );
}