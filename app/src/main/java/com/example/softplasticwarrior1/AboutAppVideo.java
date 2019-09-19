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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.videolayout);
        //MediaController mp = new MediaController(this);


        VideoView videoView = (VideoView) findViewById(R.id.videoView);

        String videopath = "android.resource://com.example.softplasticwarrior1/" + R.raw.littleturtle;
        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);
        //videoView.setMediaController(mp);
        //mp.setAnchorView(videoView);
        videoView.start();
        Button backbutton = (Button) findViewById(R.id.letsbutton);
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


