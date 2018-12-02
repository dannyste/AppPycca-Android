package com.pycca.pycca.multilogin;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.login.widget.LoginButton;
import com.pycca.pycca.R;
import com.pycca.pycca.host.HostActivity;
import com.pycca.pycca.login.LoginActivity;
import com.pycca.pycca.root.App;
import com.pycca.pycca.signup.SignUpActivity;
import com.pycca.pycca.util.Util;

import javax.inject.Inject;

public class MultiLoginActivity extends AppCompatActivity implements MultiLoginActivityMVP.View {

    private static final String TAG = MultiLoginActivity.class.getName();

    @Inject
    public MultiLoginActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view, ll_loading, ll_done, ll_failure;
    private LoginButton lb_facebook;
    private Button btn_login_facebook, btn_login_google, btn_login_email, btn_new_here_register_now;
    private TextView tv_terms_use;
    private LottieAnimationView lav_loading, lav_done, lav_failure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_login);
        ((App) getApplication()).getApplicationComponent().inject(MultiLoginActivity.this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        ll_root_view               = findViewById(R.id.ll_root_view);
        lb_facebook                = findViewById(R.id.lb_facebook);
        btn_login_facebook         = findViewById(R.id.btn_login_facebook);
        btn_login_google           = findViewById(R.id.btn_login_google);
        btn_login_email            = findViewById(R.id.btn_login_email);
        btn_new_here_register_now  = findViewById(R.id.btn_new_here_register_now);
        tv_terms_use               = findViewById(R.id.tv_terms_use);
        ll_loading                 = findViewById(R.id.ll_loading);
        lav_loading                = findViewById(R.id.lav_loading);
        ll_done                    = findViewById(R.id.ll_done);
        lav_done                   = findViewById(R.id.lav_done);
        ll_failure                 = findViewById(R.id.ll_failure);
        lav_failure                = findViewById(R.id.lav_failure);
        btn_login_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginFacebookClicked();
            }
        });
        btn_login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginGoogleClicked(MultiLoginActivity.this);
            }
        });
        btn_login_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginEmailClicked();
            }
        });
        btn_new_here_register_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.newHereRegisterNowClicked();
            }
        });
        tv_terms_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.termsUseClicked();
            }
        });
    }

    @Override
    public String getNativePhoneNumber() {
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getLine1Number();
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
        Intent hostActivity = new Intent(MultiLoginActivity.this, HostActivity.class);
        startActivity(hostActivity);
        finish();
    }

    @Override
    public void goToLoginActivity() {
        Intent loginActivity = new Intent(MultiLoginActivity.this, LoginActivity.class);
        startActivity(loginActivity);
    }

    @Override
    public void goToSignUpActivity() {
        Intent signUpActivity = new Intent(MultiLoginActivity.this, SignUpActivity.class);
        startActivity(signUpActivity);
    }

    @Override
    public void termsUse() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.onActivityResultFacebook(requestCode, resultCode, data);
        presenter.onActivityResultGoogle(MultiLoginActivity.this, requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(MultiLoginActivity.this);
        presenter.configureFacebookSignIn(MultiLoginActivity.this, lb_facebook);
        presenter.configureGoogleSignIn(MultiLoginActivity.this);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
