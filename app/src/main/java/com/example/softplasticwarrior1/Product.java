package com.example.softplasticwarrior1;

public class Product {
    private final int name;
    private final int imageResource;
    private boolean isFavorite = false;

    public Product(int name, int imageResource) {
        this.name = name;
        this.imageResource = imageResource;
    }

    public int getName() {
        return name;
    }

    public int getImageResource() {
        return imageResource;
    }


}