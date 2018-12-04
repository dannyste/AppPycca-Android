package com.pycca.pycca.quotacalculator;

import android.animation.Animator;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.restApi.model.QuotaCalculatorResponse;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import java.util.ArrayList;

import javax.inject.Inject;

public class QuotaCalculatorActivity extends AppCompatActivity implements QuotaCalculatorActivityMVP.View {

    private static final String TAG = QuotaCalculatorActivity.class.getName();

    @Inject
    public QuotaCalculatorActivityMVP.Presenter presenter;

    private TextInputEditText tiet_amount_to_differ;
    private TextView tv_amount_to_differ, tv_amount, tv_total;
    private Spinner sp_quotas_to_differ;
    private Button bt_calculate_again, btn_calculate;
    private LinearLayout ll_form_view_amount_to_differ, ll_form_view_quotas, ll_loading, ll_done, ll_error, ll_root_view;
    private LottieAnimationView lav_loading, lav_done, lav_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quota_calculator);
        ((App) getApplication()).getApplicationComponent().inject(QuotaCalculatorActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tiet_amount_to_differ            = findViewById(R.id.tiet_amount_to_differ);
        tv_amount_to_differ              = findViewById(R.id.tv_amount_to_differ);
        tv_amount                        = findViewById(R.id.tv_amount);
        tv_total                         = findViewById(R.id.tv_total);
        sp_quotas_to_differ              = findViewById(R.id.sp_quotas_to_differ);
        bt_calculate_again               = findViewById(R.id.bt_calculate_again);
        btn_calculate                    = findViewById(R.id.btn_calculate);
        ll_form_view_amount_to_differ    = findViewById(R.id.ll_form_view_amount_to_differ);
        ll_form_view_quotas              = findViewById(R.id.ll_form_view_quotas);
        ll_loading                       = findViewById(R.id.ll_loading);
        ll_done                          = findViewById(R.id.ll_done);
        ll_error                         = findViewById(R.id.ll_error);
        lav_loading                      = findViewById(R.id.lav_loading);
        lav_done                         = findViewById(R.id.lav_done);
        lav_error                        = findViewById(R.id.lav_error);
        ll_root_view                     = findViewById(R.id.ll_root_view);

        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.sendRequest();
            }
        });

        bt_calculate_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.calculateAgain();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(QuotaCalculatorActivity.this);
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
        ll_form_view_amount_to_differ.setVisibility(View.GONE);
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
    public void showViewFormQuotas() {
        ll_done.setVisibility(View.GONE);
        ll_form_view_amount_to_differ.setVisibility(View.GONE);
        ll_form_view_quotas.setVisibility(View.VISIBLE);
    }

    @Override
    public void showViewFormAmountToDiffer() {
        ll_error.setVisibility(View.GONE);
        ll_form_view_quotas.setVisibility(View.GONE);
        ll_form_view_amount_to_differ.setVisibility(View.VISIBLE);
    }

    @Override
    public String getAmountToDiffer() {
        return tiet_amount_to_differ.getText().toString().trim();
    }

    @Override
    public void resetFormQuotas() {
        tv_amount_to_differ.setText("");
        tv_amount.setText("");
        tv_total.setText("");
        sp_quotas_to_differ.setAdapter(null);
    }

    @Override
    public void resetFormAmountToDiffer() {
        tiet_amount_to_differ.setText("");
    }

    @Override
    public void setDataToFormQuotas(final ArrayList<QuotaCalculatorResponse> formQuotas, String amountToDiffer) {
        tv_amount_to_differ.setText(amountToDiffer);

        ArrayList<String> stringArrayList = Util.getStringListQuotas(formQuotas);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, stringArrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_item);
        sp_quotas_to_differ.setAdapter(adapter);

        QuotaCalculatorResponse quota = formQuotas.get(0);
        tv_amount.setText(String.valueOf(quota.getValorCuota()));
        tv_total.setText(String.valueOf(quota.getTotalAPagar()));

        sp_quotas_to_differ.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                QuotaCalculatorResponse quota = formQuotas.get(i);
                tv_amount.setText(String.valueOf(quota.getValorCuota()));
                tv_total.setText(String.valueOf(quota.getTotalAPagar()));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    @Override
    public Context getContext() {
        return this.getApplicationContext();
    }
}
