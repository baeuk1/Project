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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by user on 2016-07-06.
 */
public class LoginActivity extends Activity {
    private EditText text_id;
    private EditText text_pw;
    private String id;
    private String pw;
    private Button loginBtn;
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
                id = text_id.getText().toString();
                pw = text_pw.getText().toString();

                String TAG = "DEVICE";
                Log.i(TAG, "BRAND = " + Build.BRAND);
                Log.i(TAG, "MODEL = " + Build.MODEL);
                Log.i(TAG, "VERSION.SDK_INT = " + Build.VERSION.SDK_INT);
                Log.i(TAG, "DEVICE" + Build.DEVICE);
                /*
                Intent intent = new Intent(context, CategoryActivity.class);
                startActivity(intent);
                */
                switch (v.getId()) {
                    case R.id.button:
                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    URL url = new URL("http://10.131.158.206:8038/test/TestServlet");
                                    URLConnection connection = url.openConnection();
                                    String inputString = id + "/" + pw;
                                    //inputString = URLEncoder.encode(inputString, "UTF-8");
                                    Log.d("inputString", inputString);
                                    connection.setDoOutput(true);
                                    OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
                                    out.write(inputString);
                                    out.close();

                                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                                    String returnString = "";
                                    String loginOk = "";
                                    while ((returnString = in.readLine()) != null) {
                                        loginOk = returnString;
                                    }

                                    Log.d("Tag", loginOk);
                                    if (loginOk != "OK") {
                                        Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(context, "Login Successful", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(context, CategoryActivity.class);
                                        startActivity(intent);
                                    }
                                    in.close();
                                } catch (Exception e) {
                                    Log.d("Exception", e.toString());
                                }
                            }
                        }).start();
                        break;
                }
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
}