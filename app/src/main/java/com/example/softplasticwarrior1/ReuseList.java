package com.example.softplasticwarrior1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ReuseList extends YouTubeBaseActivity {


    public static final String YoutubeAPIKey = "AIzaSyBavTZRqCGYCdakCoVaNrfDfggfh90BH9I";
    private static final String VIDEO_ID_KITE = "7qIUlAf7LO4";
    private static final String VIDEO_ID_FLOWER = "RH9WzvbNTSM";
    private static final String VIDEO_ID_PILLOW = "ineVaZ2Z_sw";
    String current_video = VIDEO_ID_FLOWER;
    YouTubePlayer myPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();

        setContentView(R.layout.reuse_list);

        // Initialise youtube player
        final YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_player);
        youTubeView.initialize(YoutubeAPIKey, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                myPlayer = youTubePlayer;
                myPlayer.cueVideo(VIDEO_ID_FLOWER);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(getApplicationContext(), "Oh no! " + youTubeInitializationResult.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // Initialise image buttons and set listeners
        ImageButton flowerButton = (ImageButton) findViewById(R.id.flowerbutton);
        flowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current_video != VIDEO_ID_FLOWER) {
                    current_video = VIDEO_ID_FLOWER;
                    myPlayer.cueVideo(current_video);
                }
                updateSharedPrefs();

            }
        });


        ImageButton kiteButton = (ImageButton) findViewById(R.id.kitebutton);
        kiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //current_video = VIDEO_ID_KITE;
                if (current_video != VIDEO_ID_KITE) {
                    current_video = VIDEO_ID_KITE;
                    myPlayer.cueVideo(current_video);
                }
                updateSharedPrefs();
            }
        });

        ImageButton pillowButton = (ImageButton) findViewById(R.id.pillowbutton);
        pillowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (current_video != VIDEO_ID_PILLOW) {
                    current_video = VIDEO_ID_PILLOW;
                    myPlayer.cueVideo(current_video);
                }
                updateSharedPrefs();

            }
        });
        //flowerButton.callOnClick();

    }
    public void updateSharedPrefs(){
        SharedPreferences sharedPreferences = getSharedPreferences("diverted", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("reuse", true);
        editor.commit();
    }
}
