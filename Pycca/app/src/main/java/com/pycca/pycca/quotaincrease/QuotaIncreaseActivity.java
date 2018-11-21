package com.pycca.pycca.quotaincrease;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.pycca.pycca.R;
import com.pycca.pycca.root.App;

import javax.inject.Inject;

public class QuotaIncreaseActivity extends AppCompatActivity implements QuotaIncreaseActivityMVP.View {

    private static final String TAG = QuotaIncreaseActivity.class.getName();

    @Inject
    public QuotaIncreaseActivityMVP.Presenter presenter;

    private RadioButton rb_quota_increase_club_pycca_card, rb_quota_increase_parallel_super_credit;
    private TextInputLayout til_tell_us_requested_quota;
    private TextInputEditText tiet_tell_us_requested_quota;
    private Button btn_request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quota_increase);
        ((App) getApplication()).getApplicationComponent().inject(QuotaIncreaseActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rb_quota_increase_club_pycca_card       = findViewById(R.id.rb_quota_increase_club_pycca_card);
        rb_quota_increase_parallel_super_credit = findViewById(R.id.rb_quota_increase_parallel_super_credit);
        til_tell_us_requested_quota             = findViewById(R.id.til_tell_us_requested_quota);
        tiet_tell_us_requested_quota            = findViewById(R.id.tiet_tell_us_requested_quota);
        btn_request                             = findViewById(R.id.btn_request);
        btn_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

}
