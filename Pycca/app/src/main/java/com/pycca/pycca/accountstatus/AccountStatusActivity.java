package com.pycca.pycca.accountstatus;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
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

    private LinearLayout ll_root_view, ll_loading, ll_error, ll_downloading_pdf;
    private TextView tv_available_credit, tv_used_quota, tv_aproved_quota, tv_pay_until;
    private LottieAnimationView lav_loading, lav_error, lav_downloading_pdf;
    private Button bt_download_pdf;
    private static final int RC_PERMISSION = 1000;

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
        ll_downloading_pdf     = findViewById(R.id.ll_downloading_pdf);
        lav_downloading_pdf    = findViewById(R.id.lav_downloading_pdf);

        bt_download_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPermission();
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
    public void setData(Balance balance) {
        tv_available_credit.setText("$".concat(String.valueOf(balance.getAvailableCredit())));
        tv_used_quota.setText("$".concat(String.valueOf(balance.getUsedCredit())));
        tv_aproved_quota.setText("$".concat(String.valueOf(balance.getAprovedQuota())));
        tv_pay_until.setText(Util.formatStringPayUntil(balance.getPayUntil(),String.valueOf(balance.getQuotaToPay())));
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

    @Override
    public void showDownloadAnimation() {
        ll_root_view.setVisibility(View.GONE);
        ll_downloading_pdf.setVisibility(View.VISIBLE);
        lav_downloading_pdf.playAnimation();
    }

    @Override
    public void hideDownloadAnimation() {
        lav_downloading_pdf.pauseAnimation();
        ll_downloading_pdf.setVisibility(View.GONE);
        ll_root_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RC_PERMISSION: {
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    presenter.downdloadPDF();
                }
                else {
                    Util.showMessage(ll_root_view, getString(R.string.permissions_necessary));
                }
            }
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE) ||
                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_PERMISSION);
            }
            else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, RC_PERMISSION);
            }
        }
        else {
            presenter.downdloadPDF();
        }
    }
}
