package com.example.softplasticwarrior1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends Activity {
    MediaPlayer ring = null;
    static boolean playmusic = true;
    public int count = 0, endingCount = 0;
    int tempInt = 0;
    Button b1, b2, b3, b4;
    ImageView s1, s2, s3, s4;
    ArrayList<Button> buttonArray = new ArrayList<Button>();
    ArrayList<ImageView> starArray = new ArrayList<ImageView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        setContentView(R.layout.activity_main);

        // Play intro video only the first time
        count = readSharedPreferenceInt("cntSP", "cntKey");
        if (count == 0) {
            initialiseChallenges();
            Intent intent = new Intent();
            intent.putExtra("isEnding", false);
            intent.setClass(MainActivity.this, AboutAppVideo.class);
            startActivity(intent);
            count++;
            writeSharedPreference(count, "cntSP", "cntKey");
        } else {
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
                intent.putExtra("isEnding", false);
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

        // Initialise all the views
        b1 = (Button) findViewById(R.id.chal1);
        s1 = (ImageView) findViewById(R.id.chal1_star);
        buttonArray.add(b1);
        starArray.add(s1);

        b2 = (Button) findViewById(R.id.chal2);
        s2 = (ImageView) findViewById(R.id.chal2_star);
        buttonArray.add(b2);
        starArray.add(s2);


        b3 = (Button) findViewById(R.id.chal3);
        s3 = (ImageView) findViewById(R.id.chal3_star);
        buttonArray.add(b3);
        starArray.add(s3);


        b4 = (Button) findViewById(R.id.chal4);
        s4 = (ImageView) findViewById(R.id.chal4_star);
        buttonArray.add(b4);
        starArray.add(s4);

        updateChallengeViews();

    }

    private void updateChallengeViews() {
        // Function to keep track of completed and incomplete challenges

        String[] challengeKeys = {"challenge1", "challenge2", "challenge3", "challenge4"};
        //String currentChallenge = null;
        SharedPreferences sharedPreferences = getSharedPreferences("challenges", Context.MODE_PRIVATE);
        boolean challengestatus = false;
        int numCompleted = 0;
        for (int i = 0; i < challengeKeys.length; i++) {
            challengestatus = sharedPreferences.getBoolean(challengeKeys[i], false);
            System.out.print("Output is");
            System.out.print(challengeKeys[i] + "-" + challengestatus);
            if (challengestatus == false) {
                // currentChallenge = challengeKeys[i];
                addOnclickListener(i + 1);
                highlightCurrentChallenge(i);
                break;
            } else {
                numCompleted += 1;
                addOnclickListener(i + 1);
                completeChallengeView(i);
            }
        }
       // if (numCompleted == 4) {
           // endingCount = readSharedPreferenceInt("cntSP", "cntKeyEnding");
            //if (endingCount == 0) {
//                Intent intent = new Intent();
//                intent.putExtra("isEnding", true);
//                intent.setClass(MainActivity.this, AboutAppVideo.class);
//                startActivity(intent);
              //  endingCount++;
                //writeSharedPreference(endingCount, "cntSP", "cntKeyEnding");
            //}
//            if (endingCount == 1) {
//                endingCount++;
//                writeSharedPreference(endingCount, "cntSP", "cntKeyEnding");
//                finish();
//                System.exit(0);
           // }

    //    }

    }

    // Add on click listener to current button
    private void addOnclickListener(int i) {
        if (i == 1) {

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

        } else if (i == 2) {
            Button b2 = (Button) findViewById(R.id.chal2);
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Challenge_2.class);
                    if (ring != null) {
                        ring.stop();
                    }
                    view.getContext().startActivity(intent);
                }
            });

        } else if (i == 3) {
            Button b3 = (Button) findViewById(R.id.chal3);
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Challenge_3.class);
                    if (ring != null) {
                        ring.stop();
                    }
                    view.getContext().startActivity(intent);
                }
            });

        } else {
            Button b4 = (Button) findViewById(R.id.chal4);
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), Challenge_4.class);
                    if (ring != null) {
                        ring.stop();
                    }
                    view.getContext().startActivity(intent);
                }
            });

        }
    }

    private void highlightCurrentChallenge(int i) {

        // Produce flickering effect for the current challenge button

        Button currentButton = buttonArray.get(i);
        currentButton.setBackground(getDrawable(R.drawable.roundbuttoncomplete));
        //currentButton.setBackgroundColor(Color.YELLOW);
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(400); //You can manage the blinking time with this parameter
        anim.setStartOffset(20);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        currentButton.startAnimation(anim);

    }

    private void completeChallengeView(int i) {

        // Show the completed challenge view and star
        Button completedButton = buttonArray.get(i);
        completedButton.clearAnimation();
        completedButton.setBackground(getDrawable(R.drawable.roundbuttoncomplete));
        ImageView starCompleted = starArray.get(i);
        starCompleted.setVisibility(View.VISIBLE);

    }

    private void initialiseChallenges() {

        // Write information to shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("challenges", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("challenge1", false);
        editor.putBoolean("challenge2", false);
        editor.putBoolean("challenge3", false);
        editor.putBoolean("challenge4", false);

        editor.commit();

    }


    public int readSharedPreferenceInt(String spName, String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
        return tempInt = sharedPreferences.getInt(key, 0);
    }

    //write shared preferences in integer
    public void writeSharedPreference(int amount, String spName, String key) {

        SharedPreferences sharedPreferences = getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(key, amount);
        editor.commit();
    }


    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Came inside pause");
        if (ring != null)
            ring.stop();
        // stop the music
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("Came inside resume");

        if (playmusic) {
            ring = MediaPlayer.create(MainActivity.this, R.raw.fantasyland);
            ring.start();
        }
        //Update views every time it comes here
        updateChallengeViews();

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (ring != null)
            ring.stop();
    }
}

