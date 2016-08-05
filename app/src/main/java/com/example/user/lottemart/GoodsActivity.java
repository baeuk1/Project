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
import android.widget.Toast;

public class GoodsActivity extends Activity {
    private EditText searchBar;
    private ImageButton searchButton;
    private Button cartButton;

    private ImageView imageView;
    private TextView textView_name;
    private TextView textView_price;
    private TextView textView_total;
    private EditText numberPicker;
    private Button changeButton;
    private ImageButton putCartButton;

    private String itemKey;
    private int itemNum;
    private int itemPrice;
    private int itemImage;
    private String itemCategory;
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
        itemKey = getIntent().getExtras().getString("Item_Name");
        itemNum = IntroActivity.productDataController.getItemNum(itemKey);
        itemPrice = IntroActivity.productDataController.getPrice(itemKey);
        itemImage = IntroActivity.productDataController.getImage(itemKey);
        itemCategory = IntroActivity.productDataController.getCategory(itemKey);
        IntroActivity.analyzer.saveActivityInfo(context,IntroActivity.productDataController.getItemNum(itemKey));
        IntroActivity.analyzer.sendData();
    }

    @Override
    protected void onResume(){
        super.onResume();
        numberPicker.setText("1");
        textView_name.setText(itemKey);
        textView_price.setText(String.valueOf(itemPrice));
        imageView.setImageResource(itemImage);
        textView_total.setText(String.valueOf(Integer.parseInt(numberPicker.getText().toString())*itemPrice));
        IntroActivity.analyzer.timeCheckerStart(context,itemNum);
    }
    @Override
    protected void onPause(){
        super.onPause();
        IntroActivity.analyzer.timeCheckerEnd(context,itemNum);
    }
    private void setFindViewById(){
        searchBar = (EditText) findViewById(R.id.searchBar);
        searchButton = (ImageButton) findViewById(R.id.searchButton);
        cartButton = (Button) findViewById(R.id.cartButton);
        imageView = (ImageView) findViewById(R.id.goodsView);
        textView_name = (TextView) findViewById(R.id.textView_name);
        textView_price = (TextView) findViewById(R.id.textView_price);
        textView_total = (TextView) findViewById(R.id.textView_total);
        numberPicker = (EditText) findViewById(R.id.numberPicker);
        changeButton = (Button) findViewById(R.id.change);
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
        changeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                textView_total.setText(String.valueOf(Integer.parseInt(textView_price.getText().toString())
                        * Integer.parseInt(numberPicker.getText().toString())));
            }
        });
        putCartButton.setOnClickListener(new SetOnclickListener());
    }
    public class SetOnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View v){
            String itemName = textView_name.getText().toString();
            try {
                IntroActivity.productDataController.putItemIntoCart(itemImage, itemName,
                        Integer.parseInt(textView_price.getText().toString()), Integer.parseInt(numberPicker.getText().toString()),itemCategory);
            } catch(NumberFormatException e){
                Toast.makeText(context,"개수를 정확히 입력해주세요.",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
