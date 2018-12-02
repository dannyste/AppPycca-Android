package com.pycca.pycca.cardblocking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.root.App;

import javax.inject.Inject;

public class CardBlockingActivity extends AppCompatActivity implements CardBlockingActivityMVP.View {

    private static final String TAG = CardBlockingActivity.class.getName();

    @Inject
    public CardBlockingActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view;
    private RadioGroup rb_cards;
    private RadioButton rb_principal_card;
    private LottieAnimationView lav_loading, lav_error;
    private TextView tv_error_touch_retry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_blocking);
        ((App) getApplication()).getApplicationComponent().inject(CardBlockingActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(CardBlockingActivity.this);
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
