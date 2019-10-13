package com.example.softplasticwarrior1;

import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductAdapter extends BaseAdapter {

    private final Context mContext;
    private final Product[] products;
    static private int count;

    // 1
    public ProductAdapter(Context context, Product[] products) {
        this.mContext = context;
        this.products = products;
    }

    // 2
    @Override
    public int getCount() {
        return products.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    public void updateCount() {
        this.count = this.count + 1;
    }

    public String getTotalSaved() {
        return String.valueOf(this.count);
    }

    public void setTotal(int val) {
        this.count = val;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Product product = products[position];

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.product_layout, null);
        }

        final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview_cover_art);
        final TextView nameTextView = (TextView) convertView.findViewById(R.id.textview_book_name);
        final ImageView imageViewFavorite = (ImageView) convertView.findViewById(R.id.imageview_favorite);

        imageView.setImageResource(product.getImageResource());
        nameTextView.setText(mContext.getString(product.getName()));
        return convertView;
    }

}
