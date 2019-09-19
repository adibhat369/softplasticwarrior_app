package com.example.softplasticwarrior1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;

public class MainActivity extends Activity {
     MediaPlayer ring = null;
     static boolean playmusic = true;
    public int count=0;
    int tempInt = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_main);

        count = readSharedPreferenceInt("cntSP","cntKey");
        if(count==0){
            Intent intent = new Intent();
            intent.setClass(MainActivity.this, AboutAppVideo.class);
            startActivity(intent);
            count++;
            writeSharedPreference(count,"cntSP","cntKey");
        }
        else {
            if (playmusic) {
                ring = MediaPlayer.create(MainActivity.this, R.raw.fantasyland);
                ring.start();
            }
        }

            FloatingActionButton about_button = (FloatingActionButton) findViewById(R.id.floatingActionButton);
            about_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), AboutAppVideo.class);
                    if (ring != null) {
                        ring.stop();

                    }
                    view.getContext().startActivity(intent);
                }
            });
            FloatingActionButton vol_button = (FloatingActionButton) findViewById(R.id.floatingActionButtonvol);
            vol_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    ring.stop();

                    playmusic = false;
                }
            });


            Button b1 = (Button) findViewById(R.id.chal1);
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Challenge_1.class);
                    if (ring != null) {
                        ring.stop();
                    }
                    view.getContext().startActivity(intent);
                }
            });

            Button b2 = (Button) findViewById(R.id.chal2);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), TaskVideo.class);
                    view.getContext().startActivity(intent);
                }
            });

            Button b3 = (Button) findViewById(R.id.chal3);
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Challenge_2.class);
                    view.getContext().startActivity(intent);
                }
            });


        }



    public int readSharedPreferenceInt(String spName,String key){
        SharedPreferences sharedPreferences = getSharedPreferences(spName,Context.MODE_PRIVATE);
        return tempInt = sharedPreferences.getInt(key, 0);
    }

    //write shared preferences in integer
    public void writeSharedPreference(int ammount,String spName,String key ){

        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(key, ammount);
        editor.commit();
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
        if(playmusic) {
            ring = MediaPlayer.create(MainActivity.this, R.raw.fantasyland);
            ring.start();
        }
        // play the music here
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(ring!=null)
            ring.stop();
    }
}

