package com.pycca.pycca.signup;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.login.LoginActivity;
import com.pycca.pycca.multilogin.MultiLoginActivity;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import javax.inject.Inject;

public class SignUpActivity extends AppCompatActivity implements SignUpActivityMVP.View {

    @Inject
    public SignUpActivityMVP.Presenter presenter;

    private TextInputEditText tiet_email, tiet_password, tiet_identity_card_number, tiet_club_pycca_card_number;
    private Switch s_club_pycca_member;
    private LinearLayout ll_club_pycca_member, ll_root_view, ll_loading, ll_done;;
    private Button btn_sign_up;
    private LottieAnimationView lav_loading, lav_done;
    private boolean isClubPyccaMember = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ((App) getApplication()).getApplicationComponent().inject(SignUpActivity.this);
        tiet_email                      = findViewById(R.id.tiet_email);
        tiet_password                   = findViewById(R.id.tiet_password);
        tiet_identity_card_number       = findViewById(R.id.tiet_identity_card_number);
        tiet_club_pycca_card_number     = findViewById(R.id.tiet_club_pycca_card_number);
        s_club_pycca_member             = findViewById(R.id.s_club_pycca_member);
        ll_club_pycca_member            = findViewById(R.id.ll_club_pycca_member);
        btn_sign_up                     = findViewById(R.id.btn_sign_up);
        ll_root_view                    = findViewById(R.id.ll_root_view);
        ll_loading                      = findViewById(R.id.ll_loading);
        lav_loading                     = findViewById(R.id.lav_loading);
        ll_done                         = findViewById(R.id.ll_done);
        lav_done                        = findViewById(R.id.lav_done);
        s_club_pycca_member.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isClubPyccaMember = b;
                if (b) {
                    Util.expand(ll_club_pycca_member);
                }
                else {
                    Util.collapse(ll_club_pycca_member);
                }

            }
        });
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.registerClicked();
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
    public String getIdentification() {
        return tiet_identity_card_number.getText().toString().trim();
    }

    @Override
    public String getCardNumber() {
        return tiet_club_pycca_card_number.getText().toString().trim();
    }

    @Override
    public boolean isClubPyccaMember() {
        return isClubPyccaMember;
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
    public void showPasswordRequired() {
        Util.showMessage(ll_root_view, getResources().getString(R.string.password_required));
    }

    @Override
    public void showIdentificationRequired() {
        Util.showMessage(ll_root_view, getResources().getString(R.string.identification_required));
    }

    @Override
    public void showCardNumberRequired() {
        Util.showMessage(ll_root_view, getResources().getString(R.string.card_number_required));
    }

    @Override
    public void goToLoginActivity() {
        Intent loginActivity = new Intent(SignUpActivity.this, LoginActivity.class);
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
    public void showErrorMessage(int errorCode) {
        Util.showMessage(ll_root_view, getResources().getString(errorCode));
    }

    @Override
    public SignUpActivity getActivity() {
        return this;
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(SignUpActivity.this);
    }

}
