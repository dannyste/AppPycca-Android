package com.pycca.pycca.splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.pycca.pycca.R;
import com.pycca.pycca.host.HostActivity;
import com.pycca.pycca.multilogin.MultiLoginActivity;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Constants;

import javax.inject.Inject;

public class SplashActivity extends AppCompatActivity implements SplashActivityMVP.View {

    private static final String TAG = SplashActivity.class.getName();

    @Inject
    public SplashActivityMVP.Presenter presenter;

    private ImageView iv_pycca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ((App) getApplication()).getApplicationComponent().inject(SplashActivity.this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        iv_pycca = findViewById(R.id.iv_pycca);
    }

    @Override
    public void showPyccaAnimation() {
        Animation animation = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.animation_pycca);
        iv_pycca.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                presenter.getCurrentUser(SplashActivity.this);
            }
        }, Constants.SPLASH_TIME_OUT);
    }

    @Override
    public void goToMultiLoginActivity() {
        Intent multiLoginActivity = new Intent(SplashActivity.this, MultiLoginActivity.class);
        startActivity(multiLoginActivity);
        finish();
    }

    @Override
    public void goToHostActivity() {
        Intent hostActivity = new Intent(SplashActivity.this, HostActivity.class);
        startActivity(hostActivity);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(SplashActivity.this);
        presenter.startPyccaAnimation();
    }

}
