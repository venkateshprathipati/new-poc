package com.venky.wiprotask.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.venky.wiprotask.R;
import com.venky.wiprotask.ui.base.BaseActivity;
import com.venky.wiprotask.ui.main.MainActivity;
import com.venky.wiprotask.utils.NetworkUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Venkatesh on 03,December,2019
 */
public class SplashActivity extends BaseActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progressBar = findViewById(R.id.progressBar);
        Timer t = new Timer();
        boolean checkConnection = NetworkUtils.isNetworkConnected(this);
        if (checkConnection) {
            t.schedule(new splash(), 3000);
        } else {
            Toast.makeText(SplashActivity.this, "You are in Offline", Toast.LENGTH_LONG).show();
            //t.schedule(new splash(), 3000);

        }
    }

    @Override
    protected void setUp() {

    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    class splash extends TimerTask {
        @Override
        public void run() {
            Intent intent = MainActivity.getStartIntent(SplashActivity.this);
            startActivity(intent);
            finish();

        }

    }

}
