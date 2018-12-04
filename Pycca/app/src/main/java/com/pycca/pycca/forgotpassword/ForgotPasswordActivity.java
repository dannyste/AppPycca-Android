package com.pycca.pycca.forgotpassword;

import android.animation.Animator;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.multilogin.MultiLoginActivity;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import javax.inject.Inject;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordActivityMVP.View {

    private static final String TAG = ForgotPasswordActivity.class.getName();

    @Inject
    public ForgotPasswordActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view, ll_loading, ll_done, ll_failure;
    private TextInputLayout til_email;
    private TextInputEditText tiet_email;
    private Button btn_reset_password;
    private LottieAnimationView lav_loading, lav_done, lav_failure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ((App) getApplication()).getApplicationComponent().inject(ForgotPasswordActivity.this);
        ll_root_view        = findViewById(R.id.ll_root_view);
        til_email           = findViewById(R.id.til_email);
        tiet_email          = findViewById(R.id.tiet_email);
        btn_reset_password  = findViewById(R.id.btn_reset_password);
        ll_loading          = findViewById(R.id.ll_loading);
        lav_loading         = findViewById(R.id.lav_loading);
        ll_done             = findViewById(R.id.ll_done);
        lav_done            = findViewById(R.id.lav_done);
        ll_failure          = findViewById(R.id.ll_failure);
        lav_failure         = findViewById(R.id.lav_failure);
        btn_reset_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.resetPasswordClicked();
            }
        });
    }


    @Override
    public String getEmail() {
        return tiet_email.getText().toString().trim();
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
    public void goToMultiLoginActivity() {
        Intent multiLoginActivity = new Intent(ForgotPasswordActivity.this, MultiLoginActivity.class);
        startActivity(multiLoginActivity);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(ForgotPasswordActivity.this);
    }

}
