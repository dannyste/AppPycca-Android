package com.pycca.pycca.quotaincrease;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import javax.inject.Inject;

public class QuotaIncreaseActivity extends AppCompatActivity implements QuotaIncreaseActivityMVP.View {

    private static final String TAG = QuotaIncreaseActivity.class.getName();

    @Inject
    public QuotaIncreaseActivityMVP.Presenter presenter;

    private TextInputEditText tiet_tell_us_requested_quota;
    private Button btn_request, bt_accept;
    private LinearLayout ll_form_view, ll_sended_request, ll_loading, ll_done, ll_error, ll_root_view;
    private LottieAnimationView lav_loading, lav_done, lav_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quota_increase);
        ((App) getApplication()).getApplicationComponent().inject(QuotaIncreaseActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tiet_tell_us_requested_quota            = findViewById(R.id.tiet_tell_us_requested_quota);
        btn_request                             = findViewById(R.id.btn_request);
        bt_accept = findViewById(R.id.bt_accept);
        ll_form_view = findViewById(R.id.ll_form_view);
        ll_sended_request = findViewById(R.id.ll_sended_request);
        ll_loading = findViewById(R.id.ll_loading);
        ll_done = findViewById(R.id.ll_done);
        ll_error = findViewById(R.id.ll_error);
        lav_loading = findViewById(R.id.lav_loading);
        lav_done = findViewById(R.id.lav_done);
        lav_error = findViewById(R.id.lav_error);
        ll_root_view = findViewById(R.id.ll_root_view);

        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.sendRequest();
            }
        });

        bt_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHostActivity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(QuotaIncreaseActivity.this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void showLoadingAnimation() {
        ll_form_view.setVisibility(View.GONE);
        ll_loading.setVisibility(View.VISIBLE);
        lav_loading.playAnimation();
    }

    @Override
    public void hideLoadingAnimation() {
        lav_loading.pauseAnimation();
        ll_loading.setVisibility(View.GONE);
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
    public void showErrorAnimation() {
        ll_error.setVisibility(View.VISIBLE);
        lav_error.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                presenter.finishedErrorAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        lav_error.playAnimation();
    }

    @Override
    public void showErrorMessage(int errorCode) {
        Util.showMessage(ll_root_view, getResources().getString(errorCode));
    }

    @Override
    public void goToHostActivity() {
        finish();
    }

    @Override
    public void showViewFormSended() {
        ll_done.setVisibility(View.GONE);
        ll_form_view.setVisibility(View.GONE);
        ll_sended_request.setVisibility(View.VISIBLE);
    }

    @Override
    public void showViewForm() {
        ll_error.setVisibility(View.GONE);
        ll_sended_request.setVisibility(View.GONE);
        ll_form_view.setVisibility(View.VISIBLE);
    }

    @Override
    public String getQuotaWanted() {
        return tiet_tell_us_requested_quota.getText().toString().trim();
    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }
}
