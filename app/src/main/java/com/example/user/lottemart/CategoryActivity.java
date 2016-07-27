package com.example.user.lottemart;

import android.app.*;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class CategoryActivity extends Activity {
    private ImageButton fruitButton;
    private ImageButton fashionButton;
    private ImageButton bookButton;
    private ImageButton himartButton;
    private ImageButton foodButton;
    private ImageButton kitchenButton;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        fruitButton = (ImageButton) findViewById(R.id.fruitButton);
        fashionButton = (ImageButton) findViewById(R.id.fashionButton);
        bookButton = (ImageButton) findViewById(R.id.bookButton);
        himartButton = (ImageButton) findViewById(R.id.himartButton);
        foodButton = (ImageButton) findViewById(R.id.foodButton);
        kitchenButton = (ImageButton) findViewById(R.id.kitchenButton);
        context = this;
        Log.d("Tag", "First commit");

        fruitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListActivity.class);
                intent.putExtra("Keyword","과일");
            }
        });
        fashionButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               Intent intent = new Intent(context, ListActivity.class);
               intent.putExtra("Keyword","잡화");
           }
        });
        bookButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, ListActivity.class);
                intent.putExtra("Keyword","문구");
            }
        });
        himartButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, ListActivity.class);
                intent.putExtra("Keyword","가전");
            }
        });
        foodButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, ListActivity.class);
                intent.putExtra("Keyword","식품");
            }
        });
        kitchenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(context, ListActivity.class);
                intent.putExtra("Keyword","청소");
            }
        });
    }
}