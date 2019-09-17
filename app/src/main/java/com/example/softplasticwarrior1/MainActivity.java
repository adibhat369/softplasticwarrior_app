package com.example.softplasticwarrior1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

public class MainActivity extends Activity {
     MediaPlayer ring = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ring= MediaPlayer.create(MainActivity.this,R.raw.fantasyland);
        ring.start();

        FloatingActionButton about_button = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        about_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AboutAppVideo.class);
                if(ring !=null) {
                    ring.stop();

                }
                view.getContext().startActivity(intent);
            }
        });

        Button b1 = (Button) findViewById(R.id.chal1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Challenge_1.class);
                if(ring !=null) {
                    ring.stop();
                }
                view.getContext().startActivity(intent);
            }
        });

        Button b2 = (Button) findViewById(R.id.chal2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), Challenge_2.class);
                view.getContext().startActivity(intent);
            }
        });

//        PathView path_view = (PathView)findViewById(R.id.testview);
//        path_view.init();
        //PlayVoice(this, R.raw.Fantasyland.mp3);

    }


    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Came inside pause");
        if(ring!=null)
            ring.stop();
        // stop the music
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Came inside resume");
        ring= MediaPlayer.create(MainActivity.this,R.raw.fantasyland);
        ring.start();
        // play the music here
    }
}

