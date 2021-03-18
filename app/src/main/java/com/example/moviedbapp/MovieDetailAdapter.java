package com.example.moviedbapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedbapp.model.Cast;
import com.example.moviedbapp.ui.notifications.GenreAdapter;
import com.example.moviedbapp.ui.notifications.model.Movie;

import java.util.List;

public class MovieDetailAdapter extends RecyclerView.Adapter<MovieDetailAdapter.MovieDetailViewHolder> {
    private List<Cast> castList;
    private Context context;

    public MovieDetailAdapter(Context context, List<Cast> castList){
        this.context = context;
        this.castList = castList;
    }


    @NonNull
    @Override
    public MovieDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.cast_item, parent,false);
        return new MovieDetailAdapter.MovieDetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieDetailViewHolder holder, int position) {
        holder.name.setText(castList.get(position).getName());
        holder.character.setText(castList.get(position).getCharacter());
        String cover = "https://image.tmdb.org/t/p/w500" + castList.get(position).getProfilePath();

        Glide.with(context).load(cover).into(holder.photo);
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class MovieDetailViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView character;
        public ImageView photo;

        public MovieDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tvCastName);
            character = (TextView) itemView.findViewById(R.id.tvCastCharacter);
            photo = (ImageView) itemView.findViewById(R.id.ivCastPhoto);
        }
    }
}
