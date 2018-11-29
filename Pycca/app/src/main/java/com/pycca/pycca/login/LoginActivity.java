package com.pycca.pycca.login;

import android.animation.Animator;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.forgotpassword.ForgotPasswordActivity;
import com.pycca.pycca.host.HostActivity;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {

    private static final String TAG = LoginActivity.class.getName();

    @Inject
    public LoginActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view, ll_loading, ll_done, ll_failure;
    private TextInputLayout til_email, til_password;
    private TextInputEditText tiet_email, tiet_password;
    private Button btn_login;
    private TextView tv_forgot_password;
    private LottieAnimationView lav_loading, lav_done, lav_failure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ((App) getApplication()).getApplicationComponent().inject(LoginActivity.this);
        ll_root_view        = findViewById(R.id.ll_root_view);
        til_email           = findViewById(R.id.til_email);
        tiet_email          = findViewById(R.id.tiet_email);
        til_password        = findViewById(R.id.til_password);
        tiet_password       = findViewById(R.id.tiet_password);
        btn_login           = findViewById(R.id.btn_login);
        tv_forgot_password  = findViewById(R.id.tv_forgot_password);
        ll_loading          = findViewById(R.id.ll_loading);
        lav_loading         = findViewById(R.id.lav_loading);
        ll_done             = findViewById(R.id.ll_done);
        lav_done            = findViewById(R.id.lav_done);
        ll_failure          = findViewById(R.id.ll_failure);
        lav_failure         = findViewById(R.id.lav_failure);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginClicked(LoginActivity.this);
            }
        });
        tv_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.forgotPasswordClicked();
            }
        });
    }

    @Override
    public String getEmail() {
        return this.tiet_email.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return this.tiet_password.getText().toString().trim();
    }

    @Override
    public void showEmailRequired() {
        til_email.startAnimation(Util.getTranslateAnimation());
        Util.showMessage(ll_root_view, getString(R.string.email_required));
    }

    @Override
    public void showInvalidEmail() {
        til_email.startAnimation(Util.getTranslateAnimation());
        Util.showMessage(ll_root_view, getString(R.string.invalid_email));
    }

    @Override
    public void showPasswordRequired() {
        til_password.startAnimation(Util.getTranslateAnimation());
        Util.showMessage(ll_root_view, getString(R.string.password_required));
    }

    @Override
    public void showRootView() {
        ll_root_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRootView() {
        ll_root_view.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingAnimation() {
        ll_loading.setVisibility(View.VISIBLE);
        lav_loading.playAnimation();
    }

    @Override
    public void hideLoadingAnimation() {
        ll_loading.setVisibility(View.GONE);
        lav_loading.pauseAnimation();
    }

    @Override
    public void showDoneAnimation() {
        ll_done.setVisibility(View.VISIBLE);
        lav_done.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                presenter.finishedDoneAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        lav_done.playAnimation();
    }

    @Override
    public void showFailureAnimation() {
        ll_failure.setVisibility(View.VISIBLE);
        lav_failure.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                presenter.finishedFailureAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        lav_failure.playAnimation();
    }

    @Override
    public void hideFailureAnimation() {
        ll_failure.setVisibility(View.GONE);
        lav_failure.pauseAnimation();
    }

    @Override
    public void showErrorMessage(int error) {
        Util.showMessage(ll_root_view, getString(error));
    }

    @Override
    public void goToHostActivity() {
        Intent hostActivity = new Intent(LoginActivity.this, HostActivity.class);
        startActivity(hostActivity);
        finish();
    }

    @Override
    public void goToForgotPasswordActivity() {
        Intent forgotPasswordActivity = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
        startActivity(forgotPasswordActivity);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(LoginActivity.this);
    }

}
