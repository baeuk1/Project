package com.example.user.lottemart;

import android.media.Image;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by user on 2016-07-20.
 */
public class Product{
    private String name;
    private int price;
    private int image;
    private String category;

    public Product(String name, int price, int image, String category){
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public int getImage(){
        return image;
    }
    public String getCategory(){
        return category;
    }
}
