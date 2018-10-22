package com.pycca.pycca.forgotpassword;

import android.animation.Animator;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
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

    @Inject
    public ForgotPasswordActivityMVP.Presenter presenter;

    private static final String TAG = ForgotPasswordActivity.class.getName();
    private TextInputEditText tiet_email;
    private Button btn_reset_password;
    private LottieAnimationView lav_loading, lav_done;
    private LinearLayout ll_root_view, ll_loading, ll_done;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ((App) getApplication()).getApplicationComponent().inject(ForgotPasswordActivity.this);
        tiet_email                      = findViewById(R.id.tiet_email);
        btn_reset_password                     = findViewById(R.id.btn_reset_password);
        ll_root_view                    = findViewById(R.id.ll_root_view);
        ll_loading                      = findViewById(R.id.ll_loading);
        lav_loading                     = findViewById(R.id.lav_loading);
        ll_done                         = findViewById(R.id.ll_done);
        lav_done                        = findViewById(R.id.lav_done);
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
    public void showInvalidEmail() {
        Util.showMessage(ll_root_view, getResources().getString(R.string.invalid_email));
    }

    @Override
    public void showEmailRequired() {
        Util.showMessage(ll_root_view, getResources().getString(R.string.email_required));
    }

    @Override
    public void showMessage(int code) {
        Util.showMessage(ll_root_view, getResources().getString(code));
    }

    @Override
    public void goToMultilogin() {
        Intent multiloginIntent = new Intent(ForgotPasswordActivity.this, MultiLoginActivity.class);
        startActivity(multiloginIntent);
    }

    @Override
    public void showLoadingAnimation() {
        ll_root_view.setVisibility(View.GONE);
        ll_loading.setVisibility(View.VISIBLE);
        lav_loading.playAnimation();
    }

    @Override
    public void hideLoadingAnimation() {
        lav_loading.pauseAnimation();
        ll_loading.setVisibility(View.GONE);
        ll_root_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void showDoneAnimation() {
        lav_loading.pauseAnimation();
        ll_loading.setVisibility(View.GONE);
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
    protected void onResume() {
        super.onResume();
        presenter.setView(ForgotPasswordActivity.this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }

}
