package com.pycca.pycca.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.pycca.pycca.R;
import com.pycca.pycca.login.LoginActivity;
import com.pycca.pycca.multilogin.MultiLoginActivity;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getName();

    private ImageView iv_pycca;

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        iv_pycca = findViewById(R.id.iv_pycca);
        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.animation);
        iv_pycca.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginActivity = new Intent(SplashActivity.this, MultiLoginActivity.class);
                startActivity(loginActivity);
                finish();
            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}
