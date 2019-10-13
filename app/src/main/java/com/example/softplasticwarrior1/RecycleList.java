package com.example.softplasticwarrior1;


import android.Manifest;
import android.content.DialogInterface;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.IOException;


public class RecycleList extends AppCompatActivity {

    Double longitude = 0.0;
    Double latitude = 0.0;
    TextView postcode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.recyclelist);

        // initialise the views
        String value_code = getIntent().getExtras().getString("pcode");
        postcode = (TextView) findViewById(R.id.postcode);
        postcode.setText("Your postcode - " + value_code);

        TextView name1 = (TextView) findViewById(R.id.name1);
        TextView address1line1 = (TextView) findViewById(R.id.address1line1);
        TextView address1line2 = (TextView) findViewById(R.id.address1line2);

        TextView name2 = (TextView) findViewById(R.id.name2);
        TextView address2line1 = (TextView) findViewById(R.id.address2line1);
        TextView address2line2 = (TextView) findViewById(R.id.address2line2);

        TextView name3 = (TextView) findViewById(R.id.name3);
        TextView address3line1 = (TextView) findViewById(R.id.address3line1);
        TextView address3line2 = (TextView) findViewById(R.id.address3line2);

        Button buttonMum = (Button) findViewById(R.id.button_mum);
        buttonMum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(RecycleList.this).setTitle("Recycled?")
                        .setMessage("Did you go with your mum and drop your soft plastics?")
                        .setPositiveButton("Yes, I did", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .create().show();
            }
        });

        // Read the csv file
        InputStream is = getResources().openRawResource(R.raw.postcodelgamapping);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8")));
        String line = "";


        try {
            //display the locations
            String headings = reader.readLine();
            System.out.println(headings);
            while ((line = reader.readLine()) != null) {
                // Split the line into different tokens (using the comma as a separator).
                String[] tokens = line.split(",");

                if (value_code.equals(tokens[0])) {

                    //System.out.println(line);
                    name1.setText("1. " + tokens[2]);
                    address1line1.setText("       " + tokens[3]);
                    address1line2.setText("       " + tokens[4]);

                    name2.setText("2. " + tokens[5]);
                    address2line1.setText("       " + tokens[6]);
                    address2line2.setText("       " + tokens[7]);

                    name3.setText("3. " + tokens[8]);
                    address3line1.setText("       " + tokens[9]);
                    address3line2.setText("       " + tokens[10]);
                    break;

                }

            }
        } catch (IOException e1) {
            // Log.e("MainActivity", "Error" + line, e1);
            e1.printStackTrace();
        }
    }

}

