package com.example.user.lottemart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class ListActivity extends Activity {
    private final int MAXITEMS = 4;
    private final int MAXCATEGORIES = 6;
    private EditText searchBar;
    private ImageButton searchButton;
    private Button cartButton;

    private Intent cartIntent;
    private Intent thisIntent;
    private Intent goodsIntent;
    private Context context;
    private String keyword;

    private ImageView[] imageViews;
    private int[] productImages;

    private TextView[] textViews_name;
    private String[] productNames;

    private TextView[] textViews_price;
    private int[] productPrices;

    private EditText[] editTexts;
    private ImageButton[] putCartButtons;

    private String[] categoryNames;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        context = this;
        imageViews = new ImageView[MAXITEMS];
        textViews_name = new TextView[MAXITEMS];
        textViews_price = new TextView[MAXITEMS];
        editTexts = new EditText[MAXITEMS];
        putCartButtons = new ImageButton[MAXITEMS];
        productImages = new int[MAXITEMS];
        productNames = new String[MAXITEMS];
        productPrices = new int[MAXITEMS];
        categoryNames = IntroActivity.productDataController.getCategoryNames();
        goodsIntent = new Intent(context, com.example.user.lottemart.GoodsActivity.class);
        cartIntent = new Intent(context, com.example.user.lottemart.CartActivity.class);
        setFindViewById();
        setOnClickListener();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onResume() {
        super.onResume();
        thisIntent = getIntent();
        keyword = thisIntent.getExtras().getString("Keyword");
        itemDataLoad();
    }

    private void itemDataLoad() {
        for (int i = 0; i < MAXITEMS; i++) {
            productImages[i] = 0;
            productNames[i] = "";
            productPrices[i] = 0;
        }
        if (keyword.equals("과일") || keyword.equals("잡화") || keyword.equals("문구")
                || keyword.equals("가전") || keyword.equals("식품") || keyword.equals("청소")) {
            int category = getCategoryNumber(keyword);
            Log.d("Baeuk", "Category : " + String.valueOf(category));
            if (category == -1) {
                Log.d("Baeuk", "getCategoryNumber returns -1");
            } else {
                productImages = IntroActivity.productDataController.getImageViews(category);
                productNames = IntroActivity.productDataController.getNames(category);
                productPrices = IntroActivity.productDataController.getPrices(category);
            }
        } else {
            productImages = IntroActivity.productDataController.getImageViews(keyword);
            productNames = IntroActivity.productDataController.getNames(keyword);
            productPrices = IntroActivity.productDataController.getPrices(keyword);
        }
        for (int i = 0; i < MAXITEMS; i++) {
            if (productImages[i] != 0) {
                imageViews[i].setImageResource(productImages[i]);
                textViews_name[i].setText(productNames[i]);
                textViews_price[i].setText(String.valueOf(productPrices[i]));
            }
        }
        for (int i = 0; i < MAXITEMS; i++) {
            final int temp = i;
            imageViews[i].setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    goodsIntent.putExtra("Item_Name",textViews_name[temp].getText());
                    startActivity(goodsIntent);
                }
            });
        }
    }

    private int getCategoryNumber(String keyword) {
        Log.d("Baeuk", "Length : " + categoryNames.length);
        for (int i = 0; i < categoryNames.length; i++)
            if (keyword.equals(categoryNames[i])) return i;
        return -1;
    }

    private void setFindViewById() {
        searchBar = (EditText) findViewById(R.id.searchBar);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        cartButton = (Button) findViewById(R.id.cartButton);
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
        putCartButtons[0] = (ImageButton) findViewById(R.id.imageButton0);
        putCartButtons[1] = (ImageButton) findViewById(R.id.imageButton1);
        putCartButtons[2] = (ImageButton) findViewById(R.id.imageButton2);
        putCartButtons[3] = (ImageButton) findViewById(R.id.imageButton3);
    }

    private void setOnClickListener() {
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thisIntent.putExtra("Keyword", searchBar.getText().toString());
                startActivity(thisIntent);
            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(cartIntent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "List Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.user.lottemart/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "List Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.example.user.lottemart/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
