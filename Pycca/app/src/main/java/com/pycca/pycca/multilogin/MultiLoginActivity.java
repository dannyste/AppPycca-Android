package com.pycca.pycca.multilogin;

import android.animation.Animator;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import javax.inject.Inject;

public class MultiLoginActivity extends AppCompatActivity implements MultiLoginActivityMVP.View {

    private static final String TAG = MultiLoginActivity.class.getName();

    @Inject
    public MultiLoginActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view, ll_loading, ll_done;
    private LoginButton lb_facebook;
    private Button btn_login_facebook, btn_login_google, btn_login_twitter, btn_login_email, btn_new_here_register_now;
    //private TwitterLoginButton tlb_twitter;
    private TextView tv_terms_use;
    private LottieAnimationView lav_loading, lav_done;

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
        //tlb_twitter              = findViewById(R.id.tlb_twitter);
        btn_login_twitter          = findViewById(R.id.btn_login_twitter);
        btn_login_email            = findViewById(R.id.btn_login_email);
        btn_new_here_register_now  = findViewById(R.id.btn_new_here_register_now);
        tv_terms_use               = findViewById(R.id.tv_terms_use);
        ll_loading                 = findViewById(R.id.ll_loading);
        lav_loading                = findViewById(R.id.lav_loading);
        ll_done                    = findViewById(R.id.ll_done);
        lav_done                   = findViewById(R.id.lav_done);
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
        btn_login_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginTwitterClicked();
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
    public void loginTwitter() {

    }

    @Override
    public void loginEmail() {
        Intent loginActivity = new Intent(MultiLoginActivity.this, LoginActivity.class);
        startActivity(loginActivity);
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
    public void goToHostActivity() {
        Intent hostActivity = new Intent(MultiLoginActivity.this, HostActivity.class);
        startActivity(hostActivity);
        finish();
    }

    @Override
    public void registerNow() {
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
