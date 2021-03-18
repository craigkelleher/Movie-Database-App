package com.example.moviedbapp.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviedbapp.MovieDetail;
import com.example.moviedbapp.R;
import com.example.moviedbapp.ui.notifications.model.Movie;

import java.text.BreakIterator;
import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private List<Movie> movieList;
    private Context context;

    public GenreAdapter(Context context, List<Movie> movieList){
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public GenreAdapter.GenreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.genre_item, parent,false);
        return new GenreViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final GenreAdapter.GenreViewHolder holder, int position) {
        holder.title.setText(movieList.get(position).getOriginalTitle());
        String cover = "https://image.tmdb.org/t/p/w500" + movieList.get(position).getPosterPath();

        Glide.with(context).load(cover).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class GenreViewHolder extends RecyclerView.ViewHolder {

        public TextView title;
        public ImageView thumbnail;

        public GenreViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.tvGenreMovieTitle);
            thumbnail = (ImageView) itemView.findViewById(R.id.ivGenreMovieCover);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Movie clickedMovie = movieList.get(position);
                    Intent intent = new Intent(context, MovieDetail.class);
                    intent.putExtra(MovieDetail.EXTRA_MOVIE, clickedMovie );
                    intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                    context.startActivity(intent);
                }
            });
        }
    }
}
