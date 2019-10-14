package com.example.softplasticwarrior1;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Challenge_3_Intro extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.challenge_3_intro);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        String videopath = "android.resource://com.example.softplasticwarrior1/" + R.raw.challenge;
        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);

        videoView.start();
        Button backbutton = (Button) findViewById(R.id.buttonok);
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
