package com.example.moviedbapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.example.moviedbapp.model.Video;

public class YoutubePlayer extends YouTubeBaseActivity {
    public static final String EXTRA_VIDEO = "YoutubePlayer.Video";
    private final String API_KEY = "AIzaSyCesuPxUeRV-b8XlbUOEeGE3CxxoyhZZ2A";
    private String key;
    private Video video = null;
    private boolean isFullScreen;
    //private final String VIDEO_CODE = "SUXWAEX2jlg";
    private YouTubePlayerView player;
    private static final String TAG = YoutubePlayer.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtuber_player);
        Intent intent = getIntent();

        if (intent != null && intent.hasExtra(EXTRA_VIDEO)) {
            this.video = (Video) intent.getParcelableExtra(EXTRA_VIDEO);

            player = (YouTubePlayerView) findViewById(R.id.youtube_player);
            player.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    if (!b) {
                        key = video.getKey();
                        youTubePlayer.loadVideo(key);
                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                        youTubePlayer.setFullscreen(true);
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                    Toast.makeText(YoutubePlayer.this, youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}