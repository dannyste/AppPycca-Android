package com.pycca.pycca.quotaincrease;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.pycca.pycca.R;
import com.pycca.pycca.root.App;

import javax.inject.Inject;

public class QuotaIncreaseActivity extends AppCompatActivity implements QuotaIncreaseActivityMVP.View {

    private static final String TAG = QuotaIncreaseActivity.class.getName();

    @Inject
    public QuotaIncreaseActivityMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quota_increase);
        ((App) getApplication()).getApplicationComponent().inject(QuotaIncreaseActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
