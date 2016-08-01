package com.example.user.lottemart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PurchasedActivity extends Activity {
    private final int MAXITEMS = 4;
    private EditText searchBar;
    private ImageButton searchButton;
    private Button cartButton;
    private Button categoryButton;

    private Intent listIntent;
    private Intent cartIntent;
    private Intent categoryIntent;
    private Context context;

    private TextView purchasedTime;
    private TextView purchasedPrice;

    private ImageView[] imageViews;
    private int[] purchasedImages;

    private TextView[] textViews_name;
    private String[] purchasedNames;

    private TextView[] textViews_price;
    private int[] purchasedPrices;

    private TextView[] textViews_total;
    private int[] purchasedTotalPrices;

    private TextView[] textViews_count;
    private int[] purchasedCount;

    private int[] purchasedItemNum;
    private String[] purchasedCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased);
        context = this;
        imageViews = new ImageView[MAXITEMS];
        textViews_name = new TextView[MAXITEMS];
        textViews_price = new TextView[MAXITEMS];
        textViews_total = new TextView[MAXITEMS];
        textViews_count = new TextView[MAXITEMS];
        purchasedItemNum = new int[MAXITEMS];
        purchasedImages = new int[MAXITEMS];
        purchasedNames = new String[MAXITEMS];
        purchasedPrices = new int[MAXITEMS];
        listIntent = new Intent(context, com.example.user.lottemart.ListActivity.class);
        cartIntent = new Intent(context, com.example.user.lottemart.CartActivity.class);
        categoryIntent = new Intent(context, com.example.user.lottemart.CategoryActivity.class);
        setFindViewById();
        setOnClickListener();
        setPurchasedItemInfo();
        loadCartItem();

        for(int i=0; i<MAXITEMS; i++) {
            if(purchasedItemNum[i] != -1) IntroActivity.analyzer.savePurchasedInfo(purchasedItemNum[i], purchasedNames[i],
                    purchasedCount[i], purchasedPrices[i], purchasedCategory[i], IntroActivity.analyzer.getDate().toString());
        }
        IntroActivity.analyzer.sendData();
    }
    private void setFindViewById(){
        searchBar = (EditText) findViewById(R.id.searchBar);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        cartButton = (Button) findViewById(R.id.cartButton);
        categoryButton = (Button) findViewById(R.id.categoryButton);
        purchasedTime = (TextView) findViewById(R.id.purchasedTime);
        purchasedPrice = (TextView) findViewById(R.id.purchasedPrice);
        imageViews[0] = (ImageView) findViewById(R.id.imageView0);
        imageViews[1] = (ImageView) findViewById(R.id.imageView1);
        imageViews[2] = (ImageView) findViewById(R.id.imageView2);
        imageViews[3] = (ImageView) findViewById(R.id.imageView3);
        textViews_name[0] = (TextView) findViewById(R.id.textView0_name);
        textViews_name[1] = (TextView) findViewById(R.id.textView1_name);
        textViews_name[2] = (TextView) findViewById(R.id.textView2_name);
        textViews_name[3] = (TextView) findViewById(R.id.textView3_name);
        textViews_price[0] = (TextView) findViewById(R.id.textView0_price);
        textViews_price[1] = (TextView) findViewById(R.id.textView1_price);
        textViews_price[2] = (TextView) findViewById(R.id.textView2_price);
        textViews_price[3] = (TextView) findViewById(R.id.textView3_price);
        textViews_total[0] = (TextView) findViewById(R.id.textView0_total);
        textViews_total[1] = (TextView) findViewById(R.id.textView1_total);
        textViews_total[2] = (TextView) findViewById(R.id.textView2_total);
        textViews_total[3] = (TextView) findViewById(R.id.textView3_total);
        textViews_count[0] = (TextView) findViewById(R.id.purchasedCount0);
        textViews_count[1] = (TextView) findViewById(R.id.purchasedCount1);
        textViews_count[2] = (TextView) findViewById(R.id.purchasedCount2);
        textViews_count[3] = (TextView) findViewById(R.id.purchasedCount3);
    }
    private void loadCartItem(){
        for (int i = 0; i < MAXITEMS; i++) {
            purchasedImages[i] = 0;
            purchasedNames[i] = "";
            purchasedPrices[i] = 0;
        }
        purchasedItemNum = IntroActivity.productDataController.getCartItemNumbers();
        purchasedImages = IntroActivity.productDataController.getCartImageViews();
        purchasedNames = IntroActivity.productDataController.getCartItemNames();
        purchasedPrices = IntroActivity.productDataController.getCartItemPrices();
        purchasedCount = IntroActivity.productDataController.getCartItemNumbers();
        purchasedTotalPrices = IntroActivity.productDataController.getCartTotalPrices();
        purchasedCategory = IntroActivity.productDataController.getCartCategories();
        itemDataLoad();
    }
    private void itemDataLoad(){
        for(int i=0; i<MAXITEMS; i++){
            if(purchasedImages[i] != 0) {
                imageViews[i].setImageResource(purchasedImages[i]);
                textViews_name[i].setText(purchasedNames[i]);
                textViews_price[i].setText(String.valueOf(purchasedPrices[i]));
                textViews_total[i].setText(String.valueOf(purchasedTotalPrices[i]));
                textViews_count[i].setText(String.valueOf(purchasedCount[i]));
            }
        }
    }
    private void setPurchasedItemInfo(){
        purchasedTime.setText(IntroActivity.analyzer.getDate());
        purchasedPrice.setText(String.valueOf(IntroActivity.productDataController.getCartWholePrices()));
    }
    private void setOnClickListener(){
        searchButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listIntent.putExtra("Keyword",searchBar.getText().toString());
                startActivity(listIntent);
            }
        });
        cartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(cartIntent);
            }
        });
        categoryButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(categoryIntent);
            }
        });
    }
}
