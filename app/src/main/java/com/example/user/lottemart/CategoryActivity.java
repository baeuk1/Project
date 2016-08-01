package com.example.user.lottemart;

import android.app.*;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class CategoryActivity extends Activity {
    private EditText searchBar;
    private ImageButton searchButton;
    private Button cartButton;
    private ImageButton fruitButton;
    private ImageButton fashionButton;
    private ImageButton bookButton;
    private ImageButton himartButton;
    private ImageButton foodButton;
    private ImageButton kitchenButton;
    private Context context;
    private Intent listIntent;
    private Intent cartIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        context = this;
        listIntent = new Intent(context, com.example.user.lottemart.ListActivity.class);
        cartIntent = new Intent(context, com.example.user.lottemart.CartActivity.class);
        setFindViewById();
        setOnClickListener();
        IntroActivity.analyzer.saveActivityInfo(context);
    }
    private void setFindViewById(){
        searchBar = (EditText) findViewById(R.id.searchBar);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        cartButton = (Button) findViewById(R.id.cartButton);
        fruitButton = (ImageButton) findViewById(R.id.fruitButton);
        fashionButton = (ImageButton) findViewById(R.id.fashionButton);
        bookButton = (ImageButton) findViewById(R.id.bookButton);
        himartButton = (ImageButton) findViewById(R.id.himartButton);
        foodButton = (ImageButton) findViewById(R.id.foodButton);
        kitchenButton = (ImageButton) findViewById(R.id.kitchenButton);
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
        fruitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listIntent.putExtra("Keyword","과일");
                startActivity(listIntent);
            }
        });
        fashionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listIntent.putExtra("Keyword","잡화");
                startActivity(listIntent);
            }
        });
        bookButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listIntent.putExtra("Keyword","문구");
                startActivity(listIntent);
            }
        });
        himartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listIntent.putExtra("Keyword","가전");
                startActivity(listIntent);
            }
        });
        foodButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listIntent.putExtra("Keyword","식품");
                startActivity(listIntent);
            }
        });
        kitchenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                listIntent.putExtra("Keyword","청소");
                startActivity(listIntent);
            }
        });
    }
}