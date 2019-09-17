package com.example.softplasticwarrior1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

public class Challenge_2 extends Activity {

    private Product[] products = {
            new Product(R.string.plastic_bag, R.drawable.plasticbag),
            new Product(R.string.frozen_bag,  R.drawable.frozenfoodpackagingbag),
            new Product(R.string.Chips_cover, R.drawable.chips),
            new Product(R.string.Choco_wrapper, R.drawable.cookie1)
//            new Product(R.string.hand_hand_fingers_thumb, R.drawable.handhandfingersthumb),
//            new Product(R.string.the_very_hungry_caterpillar,  R.drawable.theveryhungrycaterpillar),
//            new Product(R.string.the_going_to_bed_book,  R.drawable.thegoingtobedbook),
//            new Product(R.string.oh_baby_go_baby,  R.drawable.ohbabygobaby),
//            new Product(R.string.the_tooth_book, R.drawable.thetoothbook),
//            new Product(R.string.one_fish_two_fish_red_fish_blue_fish, R.drawable.onefish)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //getActionBar().hide();

        setContentView(R.layout.challenge_2);

        GridView gridView = (GridView)findViewById(R.id.gridview);
        final ProductAdapter productAdapter = new ProductAdapter(this, products);
        gridView.setAdapter(productAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Product book = products[position];
                book.toggleFavorite();

                // This tells the GridView to redraw itself
                // in turn calling your BooksAdapter's getView method again for each cell
                productAdapter.notifyDataSetChanged();
            }
        });
       // gridView.setNumColumns(2);
        //gridView.setColumnWidth(400);


    }
}

