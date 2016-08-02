package com.example.user.lottemart;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by user on 2016-07-20.
 */
public class ProductDataController {
    private final int NUMOFITEMS = 24;
    private final int MAXCARTITEMS = 4;
    private Product[] productList;
    private String[] categoryNames = {"과일","잡화","문구","가전","식품","청소"};
    private int searchCount = 0;

    private int[] cartImageViews;
    private String[] cartItemNames;
    private int[] cartItemPrices;
    private int[] cartItemNumbers;
    private int[] cartTotalPrices;
    private String[] cartItemCategories;
    private int cartWholePrice;
    private int cartCount = 0;
    private Context context;

    public ProductDataController(Context context){
        productList = new Product[NUMOFITEMS];
        this.context = context;
        productDataLoader();
        cartInitialize();
    }
    private void cartInitialize(){
        cartImageViews = new int[MAXCARTITEMS];
        cartItemNames = new String[MAXCARTITEMS];
        cartItemPrices = new int[MAXCARTITEMS];
        cartItemNumbers = new int[MAXCARTITEMS];
        cartTotalPrices = new int[MAXCARTITEMS];
        cartItemCategories = new String[MAXCARTITEMS];

        for(int i=0; i<MAXCARTITEMS; i++){
            cartItemNames[i] = "";
            cartItemPrices[i] = 0;
            cartItemNumbers[i] = -1;
            cartTotalPrices[i] = 0;
            cartItemCategories[i] = "";
        }
        cartWholePrice = 0;
    }
    public boolean putItemIntoCart(int image, String itemKey, int price, int num, String category){
        if(cartCount >= 4){
            Toast.makeText(context,"카트에 더 담을 수 없습니다.",Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            cartImageViews[cartCount] = image;
            cartItemNames[cartCount] = itemKey;
            cartItemPrices[cartCount] = price;
            cartItemNumbers[cartCount] = num;
            cartTotalPrices[cartCount] = price * num;
            cartItemCategories[cartCount] = category;
            cartCount++;
            Toast.makeText(context,itemKey + "(이)가 장바구니에 담겼습니다.",Toast.LENGTH_SHORT).show();
            return true;
        }
    }
    public void setCartItemNumbers(int index, int num){
        cartItemNumbers[index] = num;
        cartTotalPrices[index] = cartItemPrices[index] * num;
    }
    public int[] getCartImageViews(){
        return cartImageViews;
    }
    public String[] getCartItemNames(){
        return cartItemNames;
    }
    public int[] getCartItemPrices(){
        return cartItemPrices;
    }
    public int[] getCartItemNumbers(){
        return cartItemNumbers;
    }
    public int[] getCartTotalPrices(){
        return cartTotalPrices;
    }
    public String[] getCartCategories() { return cartItemCategories; }
    public int getCartWholePrices() {
        cartWholePrice = 0;
        for(int i=0; i<MAXCARTITEMS; i++){
            if(cartTotalPrices[i] != 0) cartWholePrice += cartTotalPrices[i];
        }
        return cartWholePrice;
    }
    public int getImage(String itemKey){
        for(int i=0; i<NUMOFITEMS; i++)
            if(productList[i].getName().equals(itemKey))
                return productList[i].getImage();
        return -1;
    }
    public int getPrice(String itemKey){
        for(int i=0; i<NUMOFITEMS; i++)
            if (productList[i].getName().equals(itemKey))
                return productList[i].getPrice();
        return -1;
    }
    public int getItemNum(String itemKey){
        for(int i=0; i<NUMOFITEMS; i++)
            if (productList[i].getName().equals(itemKey))
                return productList[i].getItemNum();
        return -1;
    }
    public String getCategory(String itemKey){
        for(int i=0; i<NUMOFITEMS; i++)
            if(productList[i].getName().equals(itemKey))
                return productList[i].getCategory();
        return "";
    }
    public String[] getNames(int category){
        String[] productNames = new String[NUMOFITEMS];
        int count = 0;
        try {
            for (int i = 0; i < NUMOFITEMS; i++)
                if (productList[i].getCategory() == categoryNames[category])
                    productNames[count++] = productList[i].getName();
        } catch(NullPointerException e){
        }
        searchCount = count;
        return productNames;
    }
    public String[] getNames(String keyword){
        String[] productNames = new String[NUMOFITEMS];
        int count = 0;
        try {
            for (int i = 0; i < NUMOFITEMS; i++)
                if (productList[i].getName().contains(keyword))
                    productNames[count++] = productList[i].getName();
        } catch(NullPointerException e){
        }
        searchCount = count;
        return productNames;
    }
    public int[] getPrices(int category){
        int[] productPrices = new int[NUMOFITEMS];
        int count = 0;
        try {
            for (int i = 0; i < NUMOFITEMS; i++)
                if (productList[i].getCategory() == categoryNames[category])
                    productPrices[count++] = productList[i].getPrice();
        } catch(NullPointerException e){
        }
        return productPrices;
    }
    public int[] getPrices(String keyword){
        int[] productPrices = new int[NUMOFITEMS];
        int count = 0;
        try {
            for (int i = 0; i < NUMOFITEMS; i++) {
                if (productList[i].getName().contains(keyword))
                    productPrices[count++] = productList[i].getPrice();
            }
        } catch(NullPointerException e){
        }
        return productPrices;
    }
    public int[] getImageViews(int category){
        int[] productImages = new int[NUMOFITEMS];
        int count = 0;
        try {
            for (int i = 0; i < NUMOFITEMS; i++)
                if (productList[i].getCategory() == categoryNames[category])
                    productImages[count++] = productList[i].getImage();
        } catch (NullPointerException e){
        }
        return productImages;
    }
    public int[] getImageViews(String keyword){
        int[] productImages = new int[NUMOFITEMS];
        int count = 0;
        try {
            for (int i = 0; i < NUMOFITEMS; i++) {
                if (productList[i].getName().contains(keyword))
                    productImages[count++] = productList[i].getImage();
            }
        } catch(NullPointerException e){
        }
        return productImages;
    }
    public String[] getCategories(int category){
        String[] productCategories = new String[NUMOFITEMS];
        int count = 0;
        try {
            for (int i = 0; i < NUMOFITEMS; i++) {
                if(productList[i].getCategory() == categoryNames[category])
                    productCategories[count++] = productList[i].getCategory();
            }
        } catch (NullPointerException e){
        }
        return productCategories;
    }
    public String[] getCategories(String keyword){
        String[] productCategories = new String[NUMOFITEMS];
        int count = 0;
        try {
            for (int i = 0; i < NUMOFITEMS; i++) {
                if (productList[i].getName().contains(keyword))
                    productCategories[count++] = productList[i].getCategory();
            }
        } catch(NullPointerException e){
        }
        return productCategories;
    }
    public String[] getCategoryNames(){
        return categoryNames;
    }
    public int getSearchCount() {
        return searchCount;
    }
    private void productDataLoader(){
        productList[0] = new Product(0,"유기농사과",1500,R.drawable.apple,"과일");
        productList[1] = new Product(1,"유기농포도",2800,R.drawable.grapes,"과일");
        productList[2] = new Product(2,"복숭아",1400,R.drawable.peach,"과일");
        productList[3] = new Product(3,"수박",12000,R.drawable.watermelon,"과일");
        productList[4] = new Product(4,"순면셔츠",10000,R.drawable.shirts,"잡화");
        productList[5] = new Product(5,"순면바지",25000,R.drawable.pants,"잡화");
        productList[6] = new Product(6,"벨트",20000,R.drawable.belt,"잡화");
        productList[7] = new Product(7,"순면팬티",6000,R.drawable.underwear,"잡화");
        productList[8] = new Product(8,"책먹는여우",8000,R.drawable.book,"문구");
        productList[9] = new Product(9,"필통",3000,R.drawable.pencilcase,"문구");
        productList[10] = new Product(10,"음반CD",15000,R.drawable.musiccd,"문구");
        productList[11] = new Product(11,"볼펜",1000,R.drawable.pen,"문구");
        productList[12] = new Product(12,"최신형냉장고",1200000,R.drawable.refridgerator,"가전");
        productList[13] = new Product(13,"최신형세탁기",900000,R.drawable.washingmachine,"가전");
        productList[14] = new Product(14,"TV",1300000,R.drawable.television,"가전");
        productList[15] = new Product(15,"에어컨",700000,R.drawable.airconditioner,"가전");
        productList[16] = new Product(16,"신라면",600,R.drawable.ramen,"식품");
        productList[17] = new Product(17,"우유",2400,R.drawable.milk,"식품");
        productList[18] = new Product(18,"참치통조림",2000,R.drawable.tuna,"식품");
        productList[19] = new Product(19,"오렌지주스",8000,R.drawable.juice,"식품");
        productList[20] = new Product(20,"주방세제",6000,R.drawable.kitchenwasher,"청소");
        productList[21] = new Product(21,"고무장갑",3000,R.drawable.rubbergloves,"청소");
        productList[22] = new Product(22,"빗자루",7000,R.drawable.broom,"청소");
        productList[23] = new Product(23,"세탁세제",12000,R.drawable.detergent,"청소");
    }
}
