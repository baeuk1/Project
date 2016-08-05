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

public class CartActivity extends Activity {
    private final int MAXITEMS = 4;
    private EditText searchBar;
    private ImageButton searchButton;
    private Button cartButton;
    private Button buyButton;

    private Intent listIntent;
    private Intent purchasedIntent;
    private Intent thisIntent;
    private Context context;

    private ImageView[] imageViews;
    private int[] productImages;

    private TextView[] textViews_name;
    private String[] productNames;

    private TextView[] textViews_price;
    private int[] productPrices;

    private TextView[] textViews_total;
    private int[] productTotalPrices;

    private EditText[] numberPickers;
    private int[] productNumbers;

    private Button[] changeButtons;
    private TextView textView_whole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        context = this;
        imageViews = new ImageView[MAXITEMS];
        textViews_name = new TextView[MAXITEMS];
        textViews_price = new TextView[MAXITEMS];
        textViews_total = new TextView[MAXITEMS];
        productImages = new int[MAXITEMS];
        productNames = new String[MAXITEMS];
        productPrices = new int[MAXITEMS];
        productNumbers = new int[MAXITEMS];
        numberPickers = new EditText[MAXITEMS];
        changeButtons = new Button[MAXITEMS];
        listIntent = new Intent(context, com.example.user.lottemart.ListActivity.class);
        purchasedIntent = new Intent(context, com.example.user.lottemart.PurchasedActivity.class);
        setFindViewById();
        setOnClickListener();
        loadCartItem();
    }
    @Override
    protected void onResume(){
        super.onResume();
        IntroActivity.analyzer.timeCheckerStart(context);
    }
    @Override
    protected void onPause(){
        super.onPause();
        IntroActivity.analyzer.timeCheckerEnd(context);
        IntroActivity.analyzer.saveActivityInfo(context);
    }
    private void setFindViewById(){
        searchBar = (EditText) findViewById(R.id.searchBar);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        cartButton = (Button) findViewById(R.id.cartButton);
        buyButton = (Button) findViewById(R.id.buyButton);
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
        textViews_total[0] = (TextView) findViewById(R.id.textView_total0);
        textViews_total[1] = (TextView) findViewById(R.id.textView_total1);
        textViews_total[2] = (TextView) findViewById(R.id.textView_total2);
        textViews_total[3] = (TextView) findViewById(R.id.textView_total3);
        numberPickers[0] = (EditText) findViewById(R.id.editNum0);
        numberPickers[1] = (EditText) findViewById(R.id.editNum1);
        numberPickers[2] = (EditText) findViewById(R.id.editNum2);
        numberPickers[3] = (EditText) findViewById(R.id.editNum3);
        changeButtons[0] = (Button) findViewById(R.id.change0);
        changeButtons[1] = (Button) findViewById(R.id.change1);
        changeButtons[2] = (Button) findViewById(R.id.change2);
        changeButtons[3] = (Button) findViewById(R.id.change3);
        textView_whole = (TextView) findViewById(R.id.textView_whole);
    }
    private void loadCartItem(){
        for (int i = 0; i < MAXITEMS; i++) {
            productImages[i] = 0;
            productNames[i] = "";
            productPrices[i] = 0;
        }
        productImages = IntroActivity.productDataController.getCartImageViews();
        productNames = IntroActivity.productDataController.getCartItemNames();
        productPrices = IntroActivity.productDataController.getCartItemPrices();
        productNumbers = IntroActivity.productDataController.getCartItemNumbers();
        productTotalPrices = IntroActivity.productDataController.getCartTotalPrices();
        itemDataLoad();
    }
    private void itemDataLoad(){
        for(int i=0; i<MAXITEMS; i++){
            if(productImages[i] != 0) {
                imageViews[i].setImageResource(productImages[i]);
                textViews_name[i].setText(productNames[i]);
                textViews_price[i].setText(String.valueOf(productPrices[i]));
                numberPickers[i].setText(String.valueOf(productNumbers[i]));
                textViews_total[i].setText(String.valueOf(productTotalPrices[i]));
            }
        }
        textView_whole.setText(String.valueOf(IntroActivity.productDataController.getCartWholePrices()));
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
            }
        });
        buyButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(purchasedIntent);
            }
        });
        for(int i=0; i<MAXITEMS; i++) changeButtons[i].setOnClickListener(new SetOnclickListener(i));
    }
    public class SetOnclickListener implements View.OnClickListener{
        int index;
        SetOnclickListener(int index){
            this.index = index;
        }
        @Override
        public void onClick(View v){
            textViews_total[index].setText(String.valueOf(Integer.parseInt(textViews_price[index].getText().toString())
                    * Integer.parseInt(numberPickers[index].getText().toString())));
            IntroActivity.productDataController.setCartItemNumbers(index,Integer.parseInt(numberPickers[index].getText().toString()));
            textView_whole.setText(String.valueOf(IntroActivity.productDataController.getCartWholePrices()));
        }
    }
}
