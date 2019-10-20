package com.example.softplasticwarrior1;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutAppVideo extends AppCompatActivity {

    boolean endingVideo = false;
    boolean challenge2Video = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.videolayout);

        //Same screen used for intro and ending video
        endingVideo = getIntent().getBooleanExtra("isEnding", false);
        challenge2Video = getIntent().getBooleanExtra("isChallenge2", false);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        String videopath;
        Button backbutton = (Button) findViewById(R.id.letsbutton);
        if(challenge2Video) {
            videopath = "android.resource://com.example.softplasticwarrior1/" + R.raw.challenge2_taskvideo;
            backbutton.setText("UNDERSTOOD!");
        }
        else if (endingVideo) {
            videopath = "android.resource://com.example.softplasticwarrior1/" + R.raw.lastscreen;
            backbutton.setText("FINISH");
        } else {
            videopath = "android.resource://com.example.softplasticwarrior1/" + R.raw.newintro;
        }
        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);

        videoView.start();

        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}


