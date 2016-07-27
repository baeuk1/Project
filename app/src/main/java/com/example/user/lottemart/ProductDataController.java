package com.example.user.lottemart;

import android.media.Image;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by user on 2016-07-20.
 */
public class ProductDataController {
    private final int NUMOFITEMS = 24;
    private Product[] productList;
    private ImageView[] images;
    private String[] categories;
    private String[] categoryNames = {"과일","잡화","문구","가전","식품","청소"};

    public ProductDataController(){
        productList = new Product[NUMOFITEMS];
        productDataLoader();
    }
    public String[] getNames(int category){
        String[] productNames = new String[NUMOFITEMS];
        int count = 0;
        for(int i=0; i<NUMOFITEMS; i++)
            if(productList[i].getCategory() == categoryNames[category])
                productNames[count++] = productList[i].getName();
        return productNames;
    }
    public String[] getNames(String keyword){
        String[] productNames = new String[NUMOFITEMS];
        int count = 0;
        for(int i=0; i<NUMOFITEMS; i++)
            if(productList[i].getName().contains(keyword))
                productNames[count++] = productList[i].getName();
        return productNames;
    }
    public int[] getPrices(int category){
        int[] productPrices = new int[NUMOFITEMS];
        int count = 0;
        for(int i=0; i<NUMOFITEMS; i++)
            if(productList[i].getCategory() == categoryNames[category])
                productPrices[count++] = productList[i].getPrice();
        return productPrices;
    }
    public int[] getPrices(String keyword){
        int[] productPrices = new int[NUMOFITEMS];
        int count = 0;
        for(int i=0; i<NUMOFITEMS; i++){
            if(productList[i].getName().contains(keyword))
                productPrices[count++] = productList[i].getPrice();
        }
        return productPrices;
    }
    public int[] getImageViews(int category){
        int[] productImages = new int[NUMOFITEMS];
        int count = 0;
        for(int i=0; i<NUMOFITEMS; i++)
            if(productList[i].getCategory() == categoryNames[category])
                productImages[count++] = productList[i].getImage();
        return productImages;
    }
    public int[] getImageViews(String keyword){
        int[] productImages = new int[NUMOFITEMS];
        int count = 0;
        for(int i=0; i<NUMOFITEMS; i++){
            if(productList[i].getName().contains(keyword))
                productImages[count++] = productList[i].getImage();
        }
        return productImages;
    }
    public String[] getCategories(String keyword){
        String[] productCategories = new String[NUMOFITEMS];
        int count = 0;
        for(int i=0; i<NUMOFITEMS; i++){
            if(productList[i].getName().contains(keyword))
                productCategories[count++] = productList[i].getCategory();
        }
        return productCategories;
    }
    public String[] getCategoryNames(){
        return categoryNames;
    }
    private void productDataLoader(){
        productList[0] = new Product("유기농사과",1500,R.drawable.apple,"과일");
        productList[1] = new Product("유기농포도",2800,R.drawable.grapes,"과일");
        productList[2] = new Product("복숭아",1400,R.drawable.peach,"과일");
        productList[3] = new Product("수박",12000,R.drawable.watermelon,"과일");
        productList[4] = new Product("순면셔츠",10000,R.drawable.shirts,"잡화");
        productList[5] = new Product("순면바지",25000,R.drawable.pants,"잡화");
        productList[6] = new Product("벨트",20000,R.drawable.belt,"잡화");
        productList[7] = new Product("순면팬티",6000,R.drawable.underwear,"잡화");
        productList[8] = new Product("책먹는여우",8000,R.drawable.book,"문구");
        productList[9] = new Product("필통",3000,R.drawable.pencilcase,"문구");
        productList[10] = new Product("음반CD",15000,R.drawable.musiccd,"문구");
        productList[11] = new Product("볼펜",1000,R.drawable.pen,"문구");
        productList[12] = new Product("최신형냉장고",1200000,R.drawable.refridgerator,"가전");
        productList[13] = new Product("최신형세탁기",900000,R.drawable.washingmachine,"가전");
        productList[14] = new Product("TV",1300000,R.drawable.television,"가전");
        productList[15] = new Product("에어컨",700000,R.drawable.airconditioner,"가전");
        productList[16] = new Product("신라면",600,R.drawable.ramen,"식품");
        productList[17] = new Product("우유",2400,R.drawable.milk,"식품");
        productList[17] = new Product("참치통조림",2000,R.drawable.tuna,"식품");
        productList[19] = new Product("오렌지주스",8000,R.drawable.juice,"식품");
        productList[20] = new Product("주방세제",6000,R.drawable.kitchenwasher,"청소");
        productList[21] = new Product("고무장갑",3000,R.drawable.rubbergloves,"청소");
        productList[22] = new Product("빗자루",7000,R.drawable.broom,"청소");
        productList[23] = new Product("세탁세제",12000,R.drawable.detergent,"청소");
    }
}
