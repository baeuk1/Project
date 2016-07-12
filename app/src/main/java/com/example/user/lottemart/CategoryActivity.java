package com.example.user.lottemart;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.ImageButton;

public class CategoryActivity extends Activity {
    private ImageButton fruitButton;
    private ImageButton fashionButton;
    private ImageButton bookButton;
    private ImageButton himartButton;
    private ImageButton foodButton;
    private ImageButton kitchenButton;

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
        Log.d("Tag","First commit");
    }
}