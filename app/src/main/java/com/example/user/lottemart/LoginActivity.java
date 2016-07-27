package com.example.user.lottemart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by user on 2016-07-06.
 */
public class LoginActivity extends Activity {
    private EditText text_id;
    private EditText text_pw;
    private Button loginBtn;
    private String id;
    private String pw;
    private Context context;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        text_id = (EditText) findViewById(R.id.editText_id);
        text_pw = (EditText) findViewById(R.id.editText_pw);
        loginBtn = (Button) findViewById(R.id.button);
        context = this;

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String TAG = "DEVICE";
                Log.i(TAG, "BRAND = " + Build.BRAND);
                Log.i(TAG, "MODEL = " + Build.MODEL);
                Log.i(TAG, "VERSION.SDK_INT = " + Build.VERSION.SDK_INT);
                Log.i(TAG, "DEVICE" + Build.DEVICE);

                id = text_id.getText().toString();
                pw = text_pw.getText().toString();

                Intent intent = new Intent(context,CategoryActivity.class);
                startActivity(intent);
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    public void onResume(){
        super.onResume();
        IntroActivity.analyzer.timeCheckerStart();
    }

    public void onPause(){
        super.onPause();
        IntroActivity.analyzer.saveLoginInfo(id,Build.DEVICE,Build.VERSION.SDK_INT,IntroActivity.analyzer.getDate());
        IntroActivity.analyzer.saveSearchInfo("우유",4);
        IntroActivity.analyzer.saveSearchInfo("바지",2);
        IntroActivity.analyzer.saveSearchInfo("화장품",3);
        IntroActivity.analyzer.savePurchasedInfo("유기농사과",20,1000,"과일",IntroActivity.analyzer.getDate());
        IntroActivity.analyzer.savePurchasedInfo("클렌징폼",5,2000,"잡화",IntroActivity.analyzer.getDate());
        IntroActivity.analyzer.timeCheckerEnd();
        IntroActivity.analyzer.saveActivityInfo(context);
        IntroActivity.analyzer.sendData();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Login Page", // TODO: Define a title for the content shown.
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
                "Login Page", // TODO: Define a title for the content shown.
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
    public void onDestroy(){
        super.onDestroy();
        IntroActivity.analyzer.connectionDestroy();
    }
}