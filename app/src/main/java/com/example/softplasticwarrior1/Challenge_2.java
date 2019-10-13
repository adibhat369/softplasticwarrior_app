package com.example.softplasticwarrior1;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class Challenge_2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.taskvideo);

        VideoView videoView = (VideoView) findViewById(R.id.introvideoView);

        String videopath = "android.resource://com.example.softplasticwarrior1/" + R.raw.taskvideonew;
        Uri uri = Uri.parse(videopath);
        videoView.setVideoURI(uri);

        videoView.start();
        Button backbutton = (Button) findViewById(R.id.readybutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences sharedPreferences = getSharedPreferences("challenges", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean("challenge2", true);
                editor.commit();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
