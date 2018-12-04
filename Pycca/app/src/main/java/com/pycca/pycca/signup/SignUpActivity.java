package com.pycca.pycca.signup;

import android.animation.Animator;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.host.HostActivity;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import javax.inject.Inject;

public class SignUpActivity extends AppCompatActivity implements SignUpActivityMVP.View {

    private static final String TAG = SignUpActivity.class.getName();

    @Inject
    public SignUpActivityMVP.Presenter presenter;

    private LinearLayout ll_club_pycca_partner, ll_root_view, ll_loading, ll_done, ll_failure;
    private TextInputLayout til_email, til_password, til_identification_card, til_club_pycca_card_number;
    private TextInputEditText tiet_email, tiet_password, tiet_identification_card, tiet_club_pycca_card_number;
    private Switch s_club_pycca_partner;
    private Button btn_sign_up;
    private LottieAnimationView lav_loading, lav_done, lav_failure;

    private boolean isClubPyccaMember = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ((App) getApplication()).getApplicationComponent().inject(SignUpActivity.this);
        ll_root_view                 = findViewById(R.id.ll_root_view);
        til_email                    = findViewById(R.id.til_email);
        tiet_email                   = findViewById(R.id.tiet_email);
        til_password                 = findViewById(R.id.til_password);
        tiet_password                = findViewById(R.id.tiet_password);
        s_club_pycca_partner         = findViewById(R.id.s_club_pycca_partner);
        ll_club_pycca_partner        = findViewById(R.id.ll_club_pycca_partner);
        til_identification_card      = findViewById(R.id.til_identification_card);
        tiet_identification_card     = findViewById(R.id.tiet_identification_card);
        til_club_pycca_card_number   = findViewById(R.id.til_club_pycca_card_number);
        tiet_club_pycca_card_number  = findViewById(R.id.tiet_club_pycca_card_number);
        btn_sign_up                  = findViewById(R.id.btn_sign_up);
        ll_loading                   = findViewById(R.id.ll_loading);
        lav_loading                  = findViewById(R.id.lav_loading);
        ll_done                      = findViewById(R.id.ll_done);
        lav_done                     = findViewById(R.id.lav_done);
        ll_failure                   = findViewById(R.id.ll_failure);
        lav_failure                  = findViewById(R.id.lav_failure);
        s_club_pycca_partner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isClubPyccaMember = b;
                if (b) {
                    Util.expand(ll_club_pycca_partner);
                }
                else {
                    Util.collapse(ll_club_pycca_partner);
                }
            }
        });
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.signUpClicked(SignUpActivity.this);
            }
        });
    }

    @Override
    public String getEmail() {
        return tiet_email.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return tiet_password.getText().toString().trim();
    }

    @Override
    public boolean isClubPyccaPartner() {
        return isClubPyccaMember;
    }

    @Override
    public String getIdentificationCard() {
        return tiet_identification_card.getText().toString().trim();
    }

    @Override
    public String getClubPyccaCardNumber() {
        return tiet_club_pycca_card_number.getText().toString().trim();
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
    public void showIdentificationCardRequired() {
        til_identification_card.startAnimation(Util.getTranslateAnimation());
        Util.showMessage(ll_root_view, getString(R.string.identification_card_required));
    }

    @Override
    public void showClubPyccaCardNumberRequired() {
        til_club_pycca_card_number.startAnimation(Util.getTranslateAnimation());
        Util.showMessage(ll_root_view, getString(R.string.club_pycca_card_number_required));
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
        Intent hostActivity = new Intent(SignUpActivity.this, HostActivity.class);
        startActivity(hostActivity);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(SignUpActivity.this);
    }

}
