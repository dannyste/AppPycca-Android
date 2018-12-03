package com.pycca.pycca.accountstatus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.AccountStatus;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import javax.inject.Inject;

public class AccountStatusActivity extends AppCompatActivity implements AccountStatusActivityMVP.View {

    @Inject
    public AccountStatusActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view, ll_loading, ll_error;
    private TextView tv_available_credit, tv_used_quota, tv_aproved_quota, tv_pay_until;
    private LottieAnimationView lav_loading, lav_error;
    private Button bt_download_pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_status);
        ((App) getApplication()).getApplicationComponent().inject(AccountStatusActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tv_available_credit = findViewById(R.id.tv_available_credit);
        tv_used_quota = findViewById(R.id.tv_used_quota);
        tv_aproved_quota = findViewById(R.id.tv_aproved_quota);
        tv_pay_until = findViewById(R.id.tv_pay_until);
        ll_root_view = findViewById(R.id.ll_root_view);
        ll_loading   = findViewById(R.id.ll_loading);
        lav_loading  = findViewById(R.id.lav_loading);
        ll_error     = findViewById(R.id.ll_error);
        lav_error    = findViewById(R.id.lav_error);
        bt_download_pdf    = findViewById(R.id.bt_download_pdf);

        bt_download_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.downdloadPDF();
            }
        });
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
    public void setData(AccountStatus accountStatus) {
        tv_available_credit.setText("$".concat(String.valueOf(accountStatus.getAvailableCredit())));
        tv_used_quota.setText("$".concat(String.valueOf(accountStatus.getUsedCredit())));
        tv_aproved_quota.setText("$".concat(String.valueOf(accountStatus.getAprovedQuota())));
        tv_pay_until.setText(Util.formatStringPayUntil(accountStatus.getPayUntil(),String.valueOf(accountStatus.getQuotaToPay())));
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
