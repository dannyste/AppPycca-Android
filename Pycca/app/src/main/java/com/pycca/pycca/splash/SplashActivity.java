package com.pycca.pycca.splash;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

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

    private static final int PERMISSION_REQUEST = 1000;

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
                presenter.finishedPyccaAnimation();
            }
        }, Constants.SPLASH_TIME_OUT);
    }

    @Override
    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, Manifest.permission.BLUETOOTH) ||
                ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this, Manifest.permission.CALL_PHONE)) {
                ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST);
            }
            else {
                ActivityCompat.requestPermissions(SplashActivity.this, new String[]{Manifest.permission.BLUETOOTH, Manifest.permission.CALL_PHONE}, PERMISSION_REQUEST);
            }
        }
        else {
            presenter.getCurrentUser(SplashActivity.this);
        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST: {
                if (grantResults.length > 0 &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    presenter.getCurrentUser(SplashActivity.this);
                }
                else {
                    presenter.getCurrentUser(SplashActivity.this);
                }
            }
        }
    }
}
