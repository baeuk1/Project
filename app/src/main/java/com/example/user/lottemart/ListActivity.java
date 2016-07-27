package com.example.user.lottemart;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends AppCompatActivity {
    private final int MAXITEMS = 4;
    private Intent intent;
    private String keyword;

    private ImageView[] imageViews;
    private int[] productImages;

    private TextView[] textViews_name;
    private String[] productNames;

    private TextView[] textViews_price;
    private int[] productPrices;

    private EditText[] editTexts;
    private ImageButton[] imageButtons;

    private String[] categoryNames;

    public static ProductDataController productDataController = new ProductDataController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        imageViews = new ImageView[MAXITEMS];
        categoryNames = productDataController.getCategoryNames();
        setFindViewById();
    }
    @Override
    protected void onResume(){
        super.onResume();
        intent = getIntent();
        keyword = intent.getExtras().getString("Keyword");
        itemDataLoad();
    }
    private void itemDataLoad() {
        for (int i = 0; i < MAXITEMS; i++) {
            productImages[i] = 0;
            productNames[i] = "";
            productPrices[i] = 0;
        }
        if(keyword == "과일" || keyword == "잡화" || keyword == "문구"
                || keyword == "가전" || keyword == "식품" || keyword == "청소") {
            int category = getCategoryNumber(keyword);
            if(category == -1) {
                Log.d("Debug","getCategoryNumber returns -1");
            } else {
                productImages = productDataController.getImageViews(category);
                productNames = productDataController.getNames(category);
                productPrices = productDataController.getPrices(category);
            }
        }
        else{
            productImages = productDataController.getImageViews(keyword);
            productNames = productDataController.getNames(keyword);
            productPrices = productDataController.getPrices(keyword);
        }
        for(int i=0; i<MAXITEMS; i++) {
            if(productImages[i] != 0)
            imageViews[i].setImageResource(productImages[i]);
        }
    }
    private int getCategoryNumber(String keyword){
        for(int i=0; i<MAXITEMS; i++)
            if(keyword == categoryNames[i]) return i;
        return -1;
    }
    private void setFindViewById(){
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
        editTexts[0] = (EditText) findViewById(R.id.editText0);
        editTexts[1] = (EditText) findViewById(R.id.editText1);
        editTexts[2] = (EditText) findViewById(R.id.editText2);
        editTexts[3] = (EditText) findViewById(R.id.editText3);
        imageButtons[0] = (ImageButton) findViewById(R.id.imageButton0);
        imageButtons[1] = (ImageButton) findViewById(R.id.imageButton1);
        imageButtons[2] = (ImageButton) findViewById(R.id.imageButton2);
        imageButtons[3] = (ImageButton) findViewById(R.id.imageButton3);
    }
}
