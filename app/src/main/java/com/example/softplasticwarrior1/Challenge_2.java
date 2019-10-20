package com.example.softplasticwarrior1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Challenge_2 extends AppCompatActivity {
    int taskcount = 0;
    boolean bagFlag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.challenge_2);
        Intent intent = new Intent();
               intent.putExtra("isChallenge2", true);
                intent.setClass(Challenge_2.this, AboutAppVideo.class);
               startActivity(intent);

//        VideoView videoView = (VideoView) findViewById(R.id.introvideoView);
//
//        String videopath = "android.resource://com.example.softplasticwarrior1/" + R.raw.challenge2_taskvideo;
//        Uri uri = Uri.parse(videopath);
//        videoView.setVideoURI(uri);
//
//        videoView.start();

        final ImageButton bag1 = (ImageButton) findViewById(R.id.bag1);
        bag1.setBackground(getDrawable(R.drawable.border_black));
        final ImageButton bag2 = (ImageButton) findViewById(R.id.bag2);
        bag2.setBackground(getDrawable(R.drawable.border_black));

        bag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bagFlag) {
                    view.setBackground(getDrawable(R.drawable.border_red));
                    bagFlag = true;
                    taskcount++;
                }
                else {
                    view.setBackground(getDrawable(R.drawable.border_red));
                    bag2.setBackground(getDrawable(R.drawable.border_black));
                }
            }
        });

        bag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!bagFlag) {
                    view.setBackground(getDrawable(R.drawable.border_red));
                    bagFlag = true;
                    taskcount++;
                }
                else {
                    view.setBackground(getDrawable(R.drawable.border_red));
                    bag1.setBackground(getDrawable(R.drawable.border_black));
                }
            }
        });

        CheckBox c1 = (CheckBox) findViewById(R.id.checkBox1);
        CheckBox c2 = (CheckBox) findViewById(R.id.checkBox2);

        c1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    taskcount++;
                }
                else {
                    taskcount--;
                }
            }
        });


        c2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    taskcount++;
                }
                else {
                    taskcount--;
                }
            }
        });

//        c1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                taskcount++;
//            }
//        });

//        c2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                taskcount++;
//            }
//        });
        Button finishbutton = (Button) findViewById(R.id.button_chal2);
        final TextView erMsg = (TextView) findViewById(R.id.erMsg);
        finishbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(taskcount == 3) {

                SharedPreferences sharedPreferences = getSharedPreferences("challenges", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putBoolean("challenge2", true);
                editor.commit();
                    finish();
                }
                else {
                    erMsg.setText("Complete all the tasks!");
                    erMsg.setTextColor(Color.RED);
                }
            }
        });


        //ImageButton room1 = (ImageButton) findViewById(R.id.room1);
        //ImageButton room2 = (ImageButton) findViewById(R.id.room2);

//        Button backbutton = (Button) findViewById(R.id.readybutton);
//        backbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                SharedPreferences sharedPreferences = getSharedPreferences("challenges", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//
//                editor.putBoolean("challenge2", true);
//                editor.commit();
//                finish();
//            }
//        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
