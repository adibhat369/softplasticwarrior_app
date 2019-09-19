package com.example.softplasticwarrior1;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class TaskVideo extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.taskvideo);
        //MediaController mp = new MediaController(this);


        VideoView videoView = (VideoView) findViewById(R.id.introvideoView);

        String videopath = "android.resource://com.example.softplasticwarrior1/" + R.raw.taskvideo;
        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);
        //videoView.setMediaController(mp);
        //mp.setAnchorView(videoView);
        videoView.start();
        Button backbutton = (Button) findViewById(R.id.readybutton);
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
