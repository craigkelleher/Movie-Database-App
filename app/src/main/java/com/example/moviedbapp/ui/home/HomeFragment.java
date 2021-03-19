package com.example.moviedbapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviedbapp.MainActivity;
import com.example.moviedbapp.R;
import com.example.moviedbapp.ui.notifications.data.Client;
import com.example.moviedbapp.ui.notifications.data.Service;
import com.example.moviedbapp.ui.notifications.model.GenreMovieRes;
import com.example.moviedbapp.ui.notifications.model.Movie;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;
import com.example.moviedbapp.R;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private HomeAdapter homeAdapter;
    private MultiSnapRecyclerView popularRecyclerView;
    private ProgressBar loadingIndicatorPB1;


    public static Integer popularListId = R.id.popular_rv;
    public static Integer newReleaseId = R.id.new_release_rv;
    public static String sort_popularity = "popularity.desc";
    public static String sort_date = "release_date.desc";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });

        Integer popularListId = R.id.popular_rv;
        Integer newReleaseId = R.id.new_release_rv;
        String sort_popularity = "popularity.desc";
        String sort_date = "release_date.desc";

        GenerateRV(root, popularListId, sort_popularity);
        GenerateRV(root, newReleaseId, sort_date);
        return root;
    }

    private void GenerateRV(View root, Integer recyclerViewId, String sortCondition){
        try{
            Client client = new Client();
            Api api = Client.getClient().create(Api.class);
            loadingIndicatorPB1 = root.findViewById(R.id.pb_loading_indicator1);

            Call<GenreMovieRes> req = api.getMovies("ac1a3131b8737b85655bc6fe15c63e6d",sortCondition, "1");

            loadingIndicatorPB1.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if(response.isSuccessful() && response.body() != null){
                        loadingIndicatorPB1.setVisibility(View.INVISIBLE);

                        MultiSnapRecyclerView recyclerView  = (MultiSnapRecyclerView) root.findViewById(recyclerViewId);

                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(horizontalLayoutManager);
                        homeAdapter = new HomeAdapter(getContext(), movies);
                        recyclerView.setAdapter(homeAdapter);
                    }
                }

                @Override
                public void onFailure(Call<GenreMovieRes> call, Throwable t) {
                    Log.d("Error ", t.getMessage());
                }
            });
        } catch (Exception e){
            Log.d("Error ", e.getMessage());
        }
    }
}