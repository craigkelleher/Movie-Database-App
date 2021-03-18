package com.example.moviedbapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.example.moviedbapp.model.Cast;
import com.example.moviedbapp.model.DetailMovieRes;
import com.example.moviedbapp.ui.notifications.GenreAdapter;
import com.example.moviedbapp.ui.notifications.NotificationsFragment;
import com.example.moviedbapp.ui.notifications.data.Client;
import com.example.moviedbapp.ui.notifications.data.Service;
import com.example.moviedbapp.ui.notifications.model.GenreMovieRes;
import com.example.moviedbapp.ui.notifications.model.Movie;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetail extends AppCompatActivity {
    private static final String TAG = MovieDetail.class.getSimpleName();
    public static final String EXTRA_MOVIE = "MovieDetail.Movie";
    private Movie movie_detail = null;
    private ProgressBar loadingIndicatorPB;
    private MovieDetailAdapter adapter;

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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
