package com.example.user.lottemart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by user on 2016-07-05.
 */
public class IntroActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        InnerClassHandler noLeakHandler = new InnerClassHandler(this);
        noLeakHandler.sendEmptyMessageDelayed(0, 2000); // 2초 뒤에 메시지 전달
    }
    private void handle(Message msg) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    class InnerClassHandler extends Handler
    {
        private final WeakReference<IntroActivity> mActivity;
        InnerClassHandler(IntroActivity activity) {
            mActivity = new WeakReference<IntroActivity>(activity);
        }

        @Override
        public void handleMessage(Message msg)
        {
            IntroActivity activity = mActivity.get();
            if(activity != null) {
                activity.handle(msg);
            }
        }
    }
}
