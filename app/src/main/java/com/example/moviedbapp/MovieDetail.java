package com.example.moviedbapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedbapp.data.DetailClient;
import com.example.moviedbapp.data.DetailService;
import com.example.moviedbapp.data.VideoService;
import com.example.moviedbapp.model.Cast;
import com.example.moviedbapp.model.DetailMovieRes;
import com.example.moviedbapp.model.Video;
import com.example.moviedbapp.model.VideoRes;
import com.example.moviedbapp.ui.notifications.GenreAdapter;
import com.example.moviedbapp.ui.notifications.NotificationsFragment;
import com.example.moviedbapp.ui.notifications.data.Client;
import com.example.moviedbapp.ui.notifications.data.Service;
import com.example.moviedbapp.ui.notifications.model.GenreMovieRes;
import com.example.moviedbapp.ui.notifications.model.Movie;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetail extends AppCompatActivity {
    private static final String TAG = MovieDetail.class.getSimpleName();
    public static final String EXTRA_MOVIE = "MovieDetail.Movie";
    private final String API_KEY = "AIzaSyCesuPxUeRV-b8XlbUOEeGE3CxxoyhZZ2A";
    private Movie movie_detail = null;
    private ProgressBar loadingIndicatorPB;
    private MovieDetailAdapter adapter;
    YouTubePlayerView player;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail);

        Intent intent = getIntent();

        if(intent != null && intent.hasExtra(EXTRA_MOVIE)){
            this.movie_detail = (Movie)intent.getParcelableExtra(EXTRA_MOVIE);

            TextView titleTV = findViewById(R.id.tv_detailTitle);
            TextView releaseDataTV = findViewById(R.id.tv_releasedate);
            TextView ratingTV = findViewById(R.id.tv_rating);
            TextView overviewTV = findViewById(R.id.tv_overview);
            ImageView coverIV = findViewById(R.id.im_photocover);

            titleTV.setText(this.movie_detail.getOriginalTitle());
            releaseDataTV.setText(this.movie_detail.getReleaseDate());
            ratingTV.setText(Double.toString(this.movie_detail.getVoteAverage()));
            overviewTV.setText(this.movie_detail.getOverview());

            String url = "https://image.tmdb.org/t/p/w500" + movie_detail.getPosterPath();
            Glide.with(getApplicationContext()).load(url).into(coverIV);

            DetailClient client = new DetailClient();
            DetailService service = DetailClient.getClient().create(DetailService.class);
            loadingIndicatorPB = findViewById(R.id.pb_loading_indicator_cast);
            Call<DetailMovieRes> req = service.getCast(String.valueOf(this.movie_detail.getId()), "491cd63d741160db421cc987eb59ec33","en-US");
            loadingIndicatorPB.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<DetailMovieRes>(){
                @Override
                public void onResponse(Call<DetailMovieRes> call, Response<DetailMovieRes> response) {
                    List<Cast> cast = response.body().getResults();
                    if(response.isSuccessful() && response.body() != null){
                        loadingIndicatorPB.setVisibility(View.INVISIBLE);
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_cast_list);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(horizontalLayoutManager);
                        adapter = new MovieDetailAdapter(getApplicationContext(), cast);
                        recyclerView.setAdapter(adapter);
                    }
                }

                @Override
                public void onFailure(Call<DetailMovieRes> call, Throwable t) {
                    Log.d("Error ", t.getMessage());
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_trailer:
                playVideo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void playVideo(){
        DetailClient client = new DetailClient();
        VideoService service = DetailClient.getClient().create(VideoService.class);
        //loadingIndicatorPB = findViewById(R.id.pb_loading_indicator_cast);
        Call<VideoRes> req = service.getVideo(String.valueOf(this.movie_detail.getId()), "491cd63d741160db421cc987eb59ec33","en-US");
       // loadingIndicatorPB.setVisibility(View.VISIBLE);
        req.enqueue(new Callback<VideoRes>(){
            @Override
            public void onResponse(Call<VideoRes> call, Response<VideoRes> response) {
                List<Video> video = response.body().getResults();
                if(response.isSuccessful() && response.body() != null){
                   // loadingIndicatorPB.setVisibility(View.INVISIBLE);
                    //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_cast_list);
                    //LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
                    //recyclerView.setLayoutManager(horizontalLayoutManager);
                    //adapter = new MovieDetailAdapter(getApplicationContext(), video);
                    //recyclerView.setAdapter(adapter);
                    Intent intent = new Intent(getApplicationContext(), YoutubePlayer.class);
                    intent.putExtra(YoutubePlayer.EXTRA_VIDEO, video.get(0));
                    intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<VideoRes> call, Throwable t) {
                Log.d("Error ", t.getMessage());
            }
        });
    }
}
