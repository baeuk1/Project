package com.example.user.lottemart;

import android.media.Image;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by user on 2016-07-20.
 */
public class Product{
    private String category;
    private String name;
    private int price;
    private int image;

    public Product(String name, int price, int image, String category){

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
