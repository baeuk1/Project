package com.example.user.lottemart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class GoodsActivity extends AppCompatActivity {
    private EditText searchBar;
    private ImageButton searchButton;
    private Button cartButton;

    private ImageView imageView;
    private TextView textView_name;
    private TextView textView_price;
    private TextView textView_total;
    private NumberPicker numberPicker;
    private ImageButton putCartButton;

    private String itemKey;
    private int itemPrice;
    private int itemImage;
    private Intent listIntent;
    private Intent cartIntent;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        context = this;
        listIntent = new Intent(context, com.example.user.lottemart.ListActivity.class);
        cartIntent = new Intent(context, com.example.user.lottemart.CartActivity.class);
        setFindViewById();
        setOnClickListener();
    }

    @Override
    protected void onResume(){
        super.onResume();
        itemKey = getIntent().getExtras().getString("Item_Name");
        itemPrice = IntroActivity.productDataController.getPrice(itemKey);
        itemImage = IntroActivity.productDataController.getImage(itemKey);

        numberPicker.setValue(1);

        textView_name.setText(itemKey);
        textView_price.setText(String.valueOf(itemPrice));
        imageView.setImageResource(itemImage);
        textView_total.setText(String.valueOf(numberPicker.getValue()*itemPrice));
    }

    private void setFindViewById(){
        searchBar = (EditText) findViewById(R.id.searchBar);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        cartButton = (Button) findViewById(R.id.cartButton);
        imageView = (ImageView) findViewById(R.id.goodsView);
        textView_name = (TextView) findViewById(R.id.textView_name);
        textView_price = (TextView) findViewById(R.id.textView_price);
        textView_total = (TextView) findViewById(R.id.textView_total);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        putCartButton = (ImageButton) findViewById(R.id.putCartButton);
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
    }
}
