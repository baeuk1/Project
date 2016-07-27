package com.example.user.lottemart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class CartActivity extends AppCompatActivity {
    private EditText searchBar;
    private ImageButton searchButton;
    private Button cartButton;
    private Button buyButton;

    private Intent listIntent;
    private Intent purchasedIntent;
    private Intent thisIntent;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        context = this;
        listIntent = new Intent(context, com.example.user.lottemart.ListActivity.class);
        purchasedIntent = new Intent(context, com.example.user.lottemart.PurchasedActivity.class);
        setFindViewById();
        setOnClickListener();
    }
    private void setFindViewById(){
        searchBar = (EditText) findViewById(R.id.searchBar);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        cartButton = (Button) findViewById(R.id.cartButton);
        buyButton = (Button) findViewById(R.id.buyButton);
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
    }
}
