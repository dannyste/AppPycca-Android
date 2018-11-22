package com.pycca.pycca.accountstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.Balance;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import javax.inject.Inject;

public class AccountStatusActivity extends AppCompatActivity implements AccountStatusActivityMVP.View {

    @Inject
    public AccountStatusActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view, ll_loading, ll_error;;
    private TextView tv_card_number, tv_quota_to_pay, tv_pay_until;
    private LottieAnimationView lav_loading, lav_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_status);
        ((App) getApplication()).getApplicationComponent().inject(AccountStatusActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tv_card_number = findViewById(R.id.tv_card_number);
        tv_quota_to_pay = findViewById(R.id.tv_quota_to_pay);
        tv_pay_until = findViewById(R.id.tv_pay_until);
        ll_root_view = findViewById(R.id.ll_root_view);
        ll_loading   = findViewById(R.id.ll_loading);
        lav_loading  = findViewById(R.id.lav_loading);
        ll_error     = findViewById(R.id.ll_error);
        lav_error    = findViewById(R.id.lav_error);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(AccountStatusActivity.this);
        presenter.loadData(this);
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
    public void setData(Balance balance, String clubPyccaNumber) {
        tv_card_number.setText(clubPyccaNumber);
        tv_quota_to_pay.setText("$".concat(String.valueOf(balance.getQuotaToPay())));
        tv_pay_until.setText(balance.getPayUntil());
    }

    @Override
    public void showMessage(int strCode) {
        Util.showMessage(ll_root_view, getResources().getString(strCode));
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
    public void showErrorAnimation() {
        lav_loading.pauseAnimation();
        ll_loading.setVisibility(View.GONE);
        ll_error.setVisibility(View.VISIBLE);
        lav_error.playAnimation();
    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public AccountStatusActivity getActivity() {
        return this;
    }
}
