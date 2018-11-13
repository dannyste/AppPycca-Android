package com.pycca.pycca.quotacalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.pycca.pycca.R;
import com.pycca.pycca.root.App;

import javax.inject.Inject;

public class QuotaCalculatorActivity extends AppCompatActivity implements QuotaCalculatorActivityMVP.View {

    private static final String TAG = QuotaCalculatorActivity.class.getName();

    @Inject
    public QuotaCalculatorActivityMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quota_calculator);
        ((App) getApplication()).getApplicationComponent().inject(QuotaCalculatorActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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

}
