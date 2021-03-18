package com.example.moviedbapp.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        //final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                //textView.setText(s);
            }
        });
/*
        ArrayList<String> title = new ArrayList<>();
        title.add("title1");
        title.add("title2");
        title.add("title3");
        title.add("title4");
        title.add("title5");
        title.add("title6");

        MultiSnapRecyclerView recyclerView1 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list1);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(horizontalLayoutManager);
        adapter1 = new GenreAdapter(title);
        recyclerView1.setAdapter(adapter1);


        MultiSnapRecyclerView recyclerView2 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list2);
        LinearLayoutManager horizontalLayoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(horizontalLayoutManager2);
        adapter2 = new GenreAdapter(title);
        recyclerView2.setAdapter(adapter2);*/
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
            Call<GenreMovieRes> req = service.getMovieByGenre("491cd63d741160db421cc987eb59ec33","en-US", "popularity.desc", "false", "false", "1", "28");
            loadingIndicatorPB1.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if(response.isSuccessful() && response.body() != null){
                        loadingIndicatorPB1.setVisibility(View.INVISIBLE);
                        MultiSnapRecyclerView recyclerView1 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list1);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView1.setLayoutManager(horizontalLayoutManager);
                        adapter1 = new GenreAdapter(getContext(), movies);
                        recyclerView1.setAdapter(adapter1);
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
            Call<GenreMovieRes> req = service.getMovieByGenre("491cd63d741160db421cc987eb59ec33","en-US", "popularity.desc", "false", "false", "1", "18");
            loadingIndicatorPB2.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if(response.isSuccessful() && response.body() != null){
                        loadingIndicatorPB2.setVisibility(View.INVISIBLE);
                        MultiSnapRecyclerView recyclerView2 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list2);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView2.setLayoutManager(horizontalLayoutManager);
                        adapter2 = new GenreAdapter(getContext(), movies);
                        recyclerView2.setAdapter(adapter2);
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
            Call<GenreMovieRes> req = service.getMovieByGenre("491cd63d741160db421cc987eb59ec33","en-US", "popularity.desc", "false", "false", "1", "16");
            loadingIndicatorPB3.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if(response.isSuccessful() && response.body() != null){
                        loadingIndicatorPB3.setVisibility(View.INVISIBLE);
                        MultiSnapRecyclerView recyclerView3 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list3);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView3.setLayoutManager(horizontalLayoutManager);
                        adapter3 = new GenreAdapter(getContext(), movies);
                        recyclerView3.setAdapter(adapter3);
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
            Call<GenreMovieRes> req = service.getMovieByGenre("491cd63d741160db421cc987eb59ec33", "en-US", "popularity.desc", "false", "false", "1", "14");
            loadingIndicatorPB4.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if (response.isSuccessful() && response.body() != null) {
                        loadingIndicatorPB4.setVisibility(View.INVISIBLE);
                        MultiSnapRecyclerView recyclerView4 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list4);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView4.setLayoutManager(horizontalLayoutManager);
                        adapter4 = new GenreAdapter(getContext(), movies);
                        recyclerView4.setAdapter(adapter4);
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
            Call<GenreMovieRes> req = service.getMovieByGenre("491cd63d741160db421cc987eb59ec33","en-US", "popularity.desc", "false", "false", "1", "35");
            loadingIndicatorPB5.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if(response.isSuccessful() && response.body() != null){
                        loadingIndicatorPB5.setVisibility(View.INVISIBLE);
                        MultiSnapRecyclerView recyclerView5 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list5);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView5.setLayoutManager(horizontalLayoutManager);
                        adapter5 = new GenreAdapter(getContext(), movies);
                        recyclerView5.setAdapter(adapter5);
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
            Call<GenreMovieRes> req = service.getMovieByGenre("491cd63d741160db421cc987eb59ec33", "en-US", "popularity.desc", "false", "false", "1", "12");
            loadingIndicatorPB6.setVisibility(View.VISIBLE);
            req.enqueue(new Callback<GenreMovieRes>() {
                @Override
                public void onResponse(Call<GenreMovieRes> call, Response<GenreMovieRes> response) {
                    List<Movie> movies = response.body().getResults();
                    if (response.isSuccessful() && response.body() != null) {
                        loadingIndicatorPB6.setVisibility(View.INVISIBLE);
                        MultiSnapRecyclerView recyclerView6 = (MultiSnapRecyclerView) root.findViewById(R.id.rv_genre_list6);
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView6.setLayoutManager(horizontalLayoutManager);
                        adapter6 = new GenreAdapter(getContext(), movies);
                        recyclerView6.setAdapter(adapter6);
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
}