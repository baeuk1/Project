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

/**
 * Created by user on 2016-07-06.
 */
public class LoginActivity extends Activity {
    private EditText text_id;
    private EditText text_pw;
    private Button loginBtn;
    private String id;
    private String pw;
    private String gender;
    private int age;
    private String area;
    private String job;
    private Context context;

    private CustomerDataLoader customerDataLoader;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        customerDataLoader = new CustomerDataLoader();
        text_id = (EditText) findViewById(R.id.editText_id);
        text_pw = (EditText) findViewById(R.id.editText_pw);
        loginBtn = (Button) findViewById(R.id.button);
        context = this;

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = text_id.getText().toString();
                pw = text_pw.getText().toString();

                int customerIndex = customerDataLoader.isIdValid(id);
                if(customerIndex >= 0){
                    gender = customerDataLoader.getGender(customerIndex);
                    age = customerDataLoader.getAge(customerIndex);
                    area = customerDataLoader.getArea(customerIndex);
                    job = customerDataLoader.getJob(customerIndex);
                    IntroActivity.analyzer.saveLoginInfo(id,gender,age,area,job,Build.DEVICE,Build.VERSION.SDK_INT,IntroActivity.analyzer.getDate());

                    Intent intent = new Intent(context,CategoryActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(context,"아이디를 확인하세요",Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    @Override
    protected void onResume(){
        super.onResume();
        IntroActivity.analyzer.timeCheckerStart(context);
    }
    @Override
    protected void onPause(){
        super.onPause();
        IntroActivity.analyzer.timeCheckerEnd(context);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        IntroActivity.analyzer.saveExitActivityInfo();
        Log.d("Baeuk",IntroActivity.analyzer.getExitActivity());
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