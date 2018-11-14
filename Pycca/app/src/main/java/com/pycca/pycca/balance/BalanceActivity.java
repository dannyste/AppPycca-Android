package com.pycca.pycca.balance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pycca.pycca.R;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import javax.inject.Inject;

public class BalanceActivity extends AppCompatActivity implements BalanceActivityMVP.View {

    @Inject
    public BalanceActivityMVP.Presenter presenter;

    LinearLayout linearLayout;
    TextView tv_card_number, tv_available_credit, tv_used_quota, tv_aproved_quota, tv_pay_until;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        //((App) getApplication()).getApplicationComponent().inject(BalanceActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tv_card_number = findViewById(R.id.tv_card_number);
        tv_available_credit = findViewById(R.id.tv_available_credit);
        tv_used_quota = findViewById(R.id.tv_used_quota);
        tv_aproved_quota = findViewById(R.id.tv_aproved_quota);
        tv_pay_until = findViewById(R.id.tv_pay_until);
        linearLayout = findViewById(R.id.ll_root_view);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(BalanceActivity.this);
        presenter.loadData();
    }

    @Override
    public void setData(String cardNumber, String availableCredit, String usedCredit, String aprovedQuota, String dateForPay) {
        tv_card_number.setText(cardNumber);
        tv_available_credit.setText(availableCredit);
        tv_used_quota.setText(usedCredit);
        tv_aproved_quota.setText(aprovedQuota);
        tv_pay_until.setText(dateForPay);
    }

    @Override
    public void showMessage(int strCode) {
        Util.showMessage(linearLayout, getResources().getString(strCode));
    }

    @Override
    public void showLoadingAnimation() {

    }

    @Override
    public void hideLoadingAnimation() {

    }

    @Override
    public void showDoneAnimation() {

    }

    @Override
    public void finishActivity() {
        this.finish();
    }

    @Override
    public BalanceActivity getActivity() {
        return this;
    }
}
