package com.example.user.lottemart;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class PurchasedActivity extends AppCompatActivity {
    private EditText searchBar;
    private ImageButton searchButton;
    private Button cartButton;
    private Button categoryButton;

    private Intent listIntent;
    private Intent cartIntent;
    private Intent categoryIntent;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased);
        context = this;
        listIntent = new Intent(context, com.example.user.lottemart.ListActivity.class);
        cartIntent = new Intent(context, com.example.user.lottemart.CartActivity.class);
        categoryIntent = new Intent(context, com.example.user.lottemart.CategoryActivity.class);
        setFindViewById();
        setOnClickListener();
    }
    private void setFindViewById(){
        searchBar = (EditText) findViewById(R.id.searchBar);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        cartButton = (Button) findViewById(R.id.cartButton);
        categoryButton = (Button) findViewById(R.id.categoryButton);
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
