package com.example.softplasticwarrior1;

public class Product {
    private final int name;
    //private final int author;
    private final int imageResource;
    private boolean isFavorite = false;
    //private final String imageUrl;

    public Product(int name, int imageResource) {
        this.name = name;
        //this.author = author;
        this.imageResource = imageResource;
        //this.imageUrl = imageUrl;
    }

    public int getName() {
        return name;
    }

//    public int getAuthor() {
//        return author;
//    }

    public int getImageResource() {
        return imageResource;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }
    public void setIsFavorite(boolean isFavorite) {
        this.isFavorite = isFavorite;
    }

    public void toggleFavorite() {
        isFavorite = !isFavorite;
    }

//    public String getImageUrl() {
//        return imageUrl;
//    }
}