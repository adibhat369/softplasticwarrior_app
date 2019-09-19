package com.example.softplasticwarrior1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;

public class Challenge_2 extends AppCompatActivity {

    private Product[] products = {
            new Product(R.string.plastic_bag, R.drawable.plasticbag1),
            new Product(R.string.frozen_bag,  R.drawable.frozenfoodpackagingbag),
            new Product(R.string.Chips_cover, R.drawable.chips3),
            new Product(R.string.Choco_wrapper, R.drawable.wrapper1),
            new Product(R.string.candywrap, R.drawable.candywrap),
            new Product(R.string.icecream, R.drawable.icecream),
            new Product(R.string.pastabag,  R.drawable.pastabag),
            new Product(R.string.petfood,  R.drawable.petfoodbag),
            new Product(R.string.ricebag, R.drawable.ricebags),
            new Product(R.string.postbag, R.drawable.postbags),
            new Product(R.string.ziplock, R.drawable.ziplock)
    };
    final ProductAdapter productAdapter = new ProductAdapter(this, products);
    SharedPreferences sharedPreferences;
    AlertDialog.Builder builder1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.challenge_2);
        sharedPreferences = this.getSharedPreferences("SPW_Sharedpref",Context.MODE_PRIVATE);
        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(productAdapter);
        readValue();

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Product book = products[position];
                //book.toggleFavorite();
                productAdapter.updateCount();
                updateValue();

                // This tells the GridView to redraw itself
                // in turn calling your BooksAdapter's getView method again for each cell
                productAdapter.notifyDataSetChanged();
            }
        });

        builder1 = new AlertDialog.Builder(Challenge_2.this);
        //builder1.setMessage("Write your message here.");
        builder1.setCancelable(true);
        final View initialMsg = getLayoutInflater().inflate(R.layout.popup, null);
        TextView popuptextview = (TextView)initialMsg.findViewById(R.id.textpopup);
        popuptextview.setText("Try collecting 10 soft plastics at your home!");

        builder1.setView(initialMsg);
        AlertDialog alert1 = builder1.create();
        alert1.show();

    }

    public void readValue(){
        int val =  sharedPreferences.getInt(getString(R.string.total_plastics),0);
        productAdapter.setTotal(val);
        TextView totaltext = (TextView) findViewById(R.id.totalview);
        totaltext.setText(String.valueOf(val));
        //productAdapter.notifyDataSetChanged();

    }

    public void updateValue(){


        String totalVal = productAdapter.getTotalSaved();
        TextView totaltext = (TextView) findViewById(R.id.totalview);
        totaltext.setText(totalVal);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.total_plastics), Integer.parseInt(totalVal));
        editor.commit();


        if((Integer.parseInt(totalVal)%10) == 0 ){
//            builder1 = new AlertDialog.Builder(Challenge_2.this);
//            //builder1.setMessage("Write your message here.");
//            builder1.setCancelable(true);
           final View customLayout = getLayoutInflater().inflate(R.layout.popup, null);
            TextView popuptextview = (TextView)customLayout.findViewById(R.id.textpopup);
            popuptextview.setText("GREAT JOB!");

            builder1.setView(customLayout);
            AlertDialog alert2 = builder1.create();
            alert2.show();
        }

    }
}

