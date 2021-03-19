package com.example.moviedbapp.ui.genres;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.moviedbapp.R;
import com.example.moviedbapp.SettingsActivity;
import com.example.moviedbapp.ui.genres.data.Client;
import com.example.moviedbapp.ui.genres.data.Service;
import com.example.moviedbapp.ui.genres.model.GenreMovieRes;
import com.example.moviedbapp.ui.genres.model.Movie;
import com.takusemba.multisnaprecyclerview.MultiSnapRecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GenresFragment extends Fragment
                implements SharedPreferences.OnSharedPreferenceChangeListener
{
    private GenresViewModel notificationsViewModel;
    private GenreAdapter adapter1;
    private GenreAdapter adapter2;
    private GenreAdapter adapter3;
    private GenreAdapter adapter4;
    private GenreAdapter adapter5;
    private GenreAdapter adapter6;
    private ProgressBar loadingIndicatorPB1;
    private ProgressBar loadingIndicatorPB2;
    private ProgressBar loadingIndicatorPB3;
    private ProgressBar loadingIndicatorPB4;
    private ProgressBar loadingIndicatorPB5;
    private ProgressBar loadingIndicatorPB6;

    private MultiSnapRecyclerView recyclerView1;
    private MultiSnapRecyclerView recyclerView2;
    private MultiSnapRecyclerView recyclerView3;
    private MultiSnapRecyclerView recyclerView4;
    private MultiSnapRecyclerView recyclerView5;
    private MultiSnapRecyclerView recyclerView6;

    private TextView actionView;
    private TextView dramaView;
    private TextView animeView;
    private TextView fantasyView;
    private TextView comedyView;
    private TextView scifiView;

    private SharedPreferences sharedPreferences;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        this.sharedPreferences.registerOnSharedPreferenceChangeListener(this);

    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =new ViewModelProvider(this).get(GenresViewModel.class);
        View root = inflater.inflate(R.layout.fragment_genres, container, false);
        this.actionView = root.findViewById(R.id.tv_action);
        this.dramaView = root.findViewById(R.id.tv_drama);
        this.animeView = root.findViewById(R.id.tv_anime);
        this.fantasyView = root.findViewById(R.id.tv_fantasy);
        this.comedyView = root.findViewById(R.id.tv_comedy);
        this.scifiView = root.findViewById(R.id.tv_scifi);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                //textView.setText(s);
            }
        });

        action(root);
        drama(root);
        animation(root);
        fantasy(root);
        comedy(root);
        adventure(root);

        return root;
    }

    private void action(View root){
        try{
            Client client = new Client();
            Service service = Client.getClient().create(Service.class);
            loadingIndicatorPB1 = root.findViewById(R.id.pb_loading_indicator1);
            Call<GenreMovieRes> req = service.getMovieByGenre("491cd63d741160db421cc987eb59ec33","en-US", "popularity.desc", "false", "true", "1", "28");
            loadingIndicatorPB1.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if(response.isSuccessful() && response.body() != null){
                        loadingIndicatorPB1.setVisibility(View.INVISIBLE);
                        recyclerView1 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list1);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView1.setLayoutManager(horizontalLayoutManager);
                        adapter1 = new GenreAdapter(getContext(), movies);
                        recyclerView1.setAdapter(adapter1);
                        if (!sharedPreferences.getBoolean(
                                getString(R.string.pref_action_key),true)) {
                            recyclerView1.setVisibility(View.GONE);
                            actionView.setVisibility(View.GONE);
                        }
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

    private void drama(View root){
        try{
            Client client = new Client();
            Service service = Client.getClient().create(Service.class);
            loadingIndicatorPB2 = root.findViewById(R.id.pb_loading_indicator2);
            Call<GenreMovieRes> req = service.getMovieByGenre("491cd63d741160db421cc987eb59ec33","en-US", "popularity.desc", "false", "true", "1", "18");
            loadingIndicatorPB2.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if(response.isSuccessful() && response.body() != null){
                        loadingIndicatorPB2.setVisibility(View.INVISIBLE);
                        recyclerView2 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list2);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView2.setLayoutManager(horizontalLayoutManager);
                        adapter2 = new GenreAdapter(getContext(), movies);
                        recyclerView2.setAdapter(adapter2);
                        if (!sharedPreferences.getBoolean(
                                getString(R.string.pref_drama_key),true)) {
                            recyclerView2.setVisibility(View.GONE);
                            dramaView.setVisibility(View.GONE);
                        }
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

    private void animation(View root){
        try{
            Client client = new Client();
            Service service = Client.getClient().create(Service.class);
            loadingIndicatorPB3 = root.findViewById(R.id.pb_loading_indicator3);
            Call<GenreMovieRes> req = service.getMovieByGenre("491cd63d741160db421cc987eb59ec33","en-US", "popularity.desc", "false", "true", "1", "16");
            loadingIndicatorPB3.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if(response.isSuccessful() && response.body() != null){
                        loadingIndicatorPB3.setVisibility(View.INVISIBLE);
                        recyclerView3 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list3);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView3.setLayoutManager(horizontalLayoutManager);
                        adapter3 = new GenreAdapter(getContext(), movies);
                        recyclerView3.setAdapter(adapter3);
                        if (!sharedPreferences.getBoolean(
                                getString(R.string.pref_animation_key),true)) {
                            recyclerView3.setVisibility(View.GONE);
                            animeView.setVisibility(View.GONE);
                        }
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

    private void fantasy(View root) {
        try {
            Client client = new Client();
            Service service = Client.getClient().create(Service.class);
            loadingIndicatorPB4 = root.findViewById(R.id.pb_loading_indicator4);
            Call<GenreMovieRes> req = service.getMovieByGenre("491cd63d741160db421cc987eb59ec33", "en-US", "popularity.desc", "false", "true", "1", "14");
            loadingIndicatorPB4.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if (response.isSuccessful() && response.body() != null) {
                        loadingIndicatorPB4.setVisibility(View.INVISIBLE);
                        recyclerView4 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list4);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView4.setLayoutManager(horizontalLayoutManager);
                        adapter4 = new GenreAdapter(getContext(), movies);
                        recyclerView4.setAdapter(adapter4);
                        if (!sharedPreferences.getBoolean(
                                getString(R.string.pref_fantasy_key),true)) {
                            recyclerView4.setVisibility(View.GONE);
                            fantasyView.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onFailure(Call<GenreMovieRes> call, Throwable t) {
                    Log.d("Error ", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("Error ", e.getMessage());
        }
    }

    private void comedy(View root){
        try{
            Client client = new Client();
            Service service = Client.getClient().create(Service.class);
            loadingIndicatorPB5 = root.findViewById(R.id.pb_loading_indicator5);
            Call<GenreMovieRes> req = service.getMovieByGenre("491cd63d741160db421cc987eb59ec33","en-US", "popularity.desc", "false", "true", "1", "35");
            loadingIndicatorPB5.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if(response.isSuccessful() && response.body() != null){
                        loadingIndicatorPB5.setVisibility(View.INVISIBLE);
                        recyclerView5 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list5);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView5.setLayoutManager(horizontalLayoutManager);
                        adapter5 = new GenreAdapter(getContext(), movies);
                        recyclerView5.setAdapter(adapter5);
                        if (!sharedPreferences.getBoolean(
                                getString(R.string.pref_comedy_key),true)) {
                            recyclerView5.setVisibility(View.GONE);
                            comedyView.setVisibility(View.GONE);
                        }
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

    private void adventure(View root){
        try {
            Client client = new Client();
            Service service = Client.getClient().create(Service.class);
            loadingIndicatorPB6 = root.findViewById(R.id.pb_loading_indicator6);
            Call<GenreMovieRes> req = service.getMovieByGenre("491cd63d741160db421cc987eb59ec33", "en-US", "popularity.desc", "false", "true", "1", "12");
            loadingIndicatorPB6.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if (response.isSuccessful() && response.body() != null) {
                        loadingIndicatorPB6.setVisibility(View.INVISIBLE);
                        recyclerView6 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list6);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView6.setLayoutManager(horizontalLayoutManager);
                        adapter6 = new GenreAdapter(getContext(), movies);
                        recyclerView6.setAdapter(adapter6);
                        if (!sharedPreferences.getBoolean(
                                getString(R.string.pref_scifi_key),true)) {
                            recyclerView6.setVisibility(View.GONE);
                            scifiView.setVisibility(View.GONE);
                        }
                    }
                }

                @Override
                public void onFailure(Call<GenreMovieRes> call, Throwable t) {
                    Log.d("Error ", t.getMessage());
                }
            });
        } catch (Exception e) {
            Log.d("Error ", e.getMessage());
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.genre_meu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.genre_settings:
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        int currVis;

        if (TextUtils.equals(key, getString(R.string.pref_action_key))) {
            currVis = recyclerView1.getVisibility();
            if (currVis == 0) {
                recyclerView1.setVisibility(View.GONE);
                actionView.setVisibility(View.GONE);
            }else{
                recyclerView1.setVisibility(View.VISIBLE);
                actionView.setVisibility(View.VISIBLE);
            }
        }
        if (TextUtils.equals(key, getString(R.string.pref_drama_key))) {
            System.out.println("DETECTED CHANGE IN DRAMA");
            currVis = recyclerView2.getVisibility();
            if (currVis == 0) {
                recyclerView2.setVisibility(View.GONE);
                dramaView.setVisibility(View.GONE);
            }else{
                recyclerView2.setVisibility(View.VISIBLE);
                dramaView.setVisibility(View.VISIBLE);
            }
        }
        if (TextUtils.equals(key, getString(R.string.pref_animation_key))) {
            System.out.println("DETECTED CHANGE IN ANIMATION" + recyclerView3.getVisibility());
            currVis = recyclerView3.getVisibility();
            if (currVis == 0) {
                recyclerView3.setVisibility(View.GONE);
                animeView.setVisibility(View.GONE);
            }else{
                recyclerView3.setVisibility(View.VISIBLE);
                animeView.setVisibility(View.VISIBLE);
            }
        }
        if (TextUtils.equals(key, getString(R.string.pref_fantasy_key))) {
            System.out.println("DETECTED CHANGE IN FANTASY");
            currVis = recyclerView4.getVisibility();
            if (currVis == 0) {
                recyclerView4.setVisibility(View.GONE);
                fantasyView.setVisibility(View.GONE);
            }else{
                recyclerView4.setVisibility(View.VISIBLE);
                fantasyView.setVisibility(View.VISIBLE);
            }
        }
        if (TextUtils.equals(key, getString(R.string.pref_comedy_key))) {
            System.out.println("DETECTED CHANGE IN COMEDY");
            currVis = recyclerView5.getVisibility();
            if (currVis == 0) {
                recyclerView5.setVisibility(View.GONE);
                comedyView.setVisibility(View.GONE);
            }else{
                recyclerView5.setVisibility(View.VISIBLE);
                comedyView.setVisibility(View.VISIBLE);
            }
        }
        if (TextUtils.equals(key, getString(R.string.pref_scifi_key))) {
            System.out.println("DETECTED CHANGE IN SCI" + recyclerView6.getVisibility());
            System.out.println(this.sharedPreferences.getBoolean(
                    getString(R.string.pref_scifi_key),true
            ));
            currVis = recyclerView6.getVisibility();
            if (currVis == 0) {
                recyclerView6.setVisibility(View.GONE);
                scifiView.setVisibility(View.GONE);
            }else{
                recyclerView6.setVisibility(View.VISIBLE);
                scifiView.setVisibility(View.VISIBLE);
            }
        }
    }

}