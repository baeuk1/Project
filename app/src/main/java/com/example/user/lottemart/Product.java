package com.example.user.lottemart;

/**
 * Created by user on 2016-07-20.
 */
public class Product{
    private int itemNum;
    private String name;
    private int price;
    private int image;
    private String category;

    public Product(int itemNum, String name, int price, int image, String category){
        this.itemNum = itemNum;
        this.name = name;
        this.price = price;
        this.image = image;
        this.category = category;
    }
    public int getItemNum() { return itemNum; }
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
