package com.pycca.pycca.cardblocking;

import android.animation.Animator;
import android.content.res.Resources;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.pojo.Card;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import java.util.ArrayList;

import javax.inject.Inject;

public class CardBlockingActivity extends AppCompatActivity implements CardBlockingActivityMVP.View {

    private static final String TAG = CardBlockingActivity.class.getName();

    @Inject
    public CardBlockingActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view, ll_loading, ll_done, ll_failure, ll_error;
    private RadioGroup rg_cards;
    private RadioButton rb_principal_card;
    private TextView tv_additional_cards, tv_error_touch_retry;
    private Button btn_block;
    private LottieAnimationView lav_loading, lav_done, lav_failure, lav_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_blocking);
        ((App) getApplication()).getApplicationComponent().inject(CardBlockingActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        findViewById(R.id.tv_additional_cards).setVisibility(View.VISIBLE);
        ll_root_view         = findViewById(R.id.ll_root_view);
        rg_cards             = findViewById(R.id.rg_cards);
        rb_principal_card    = findViewById(R.id.rb_principal_card);
        btn_block            = findViewById(R.id.btn_block);
        ll_loading           = findViewById(R.id.ll_loading);
        lav_loading          = findViewById(R.id.lav_loading);
        ll_done              = findViewById(R.id.ll_done);
        lav_done             = findViewById(R.id.lav_done);
        ll_failure           = findViewById(R.id.ll_failure);
        lav_failure          = findViewById(R.id.lav_failure);
        ll_error             = findViewById(R.id.ll_error);
        lav_error            = findViewById(R.id.lav_error);
        tv_error_touch_retry = findViewById(R.id.tv_error_touch_retry);
        btn_block.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.blockClicked();
            }
        });
        tv_error_touch_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.errorTouchRetryClicked(CardBlockingActivity.this);
            }
        });
    }

    @Override
    public void showRootView() {
        ll_root_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRootView() {
        ll_root_view.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingAnimation() {
        ll_loading.setVisibility(View.VISIBLE);
        lav_loading.playAnimation();
    }

    @Override
    public void hideLoadingAnimation() {
        ll_loading.setVisibility(View.GONE);
        lav_loading.pauseAnimation();
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
    public void showFailureAnimation() {
        ll_failure.setVisibility(View.VISIBLE);
        lav_failure.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                presenter.finishedFailureAnimation();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        lav_failure.playAnimation();
    }

    @Override
    public void hideFailureAnimation() {
        ll_failure.setVisibility(View.GONE);
        lav_failure.pauseAnimation();
    }

    @Override
    public void showErrorMessage(int error) {
        Util.showMessage(ll_root_view, getString(error));
    }

    @Override
    public void showErrorAnimation() {
        ll_error.setVisibility(View.VISIBLE);
        lav_error.playAnimation();
    }

    @Override
    public void hideErrorAnimation() {
        ll_error.setVisibility(View.GONE);
        lav_error.pauseAnimation();
    }

    @Override
    public void updateDataRadioButton(ArrayList<Card> cardArrayList) {
        ViewGroup.LayoutParams layoutParams = rb_principal_card.getLayoutParams();
        for (Card card : cardArrayList) {
            RadioButton radioButton = new RadioButton(CardBlockingActivity.this);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setText(card.getClubPyccaCardNumber() + "\n" + card.getName());
            radioButton.setTextColor(getResources().getColor(R.color.colorBlack));
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_medium));
            radioButton.setTypeface(ResourcesCompat.getFont(CardBlockingActivity.this, R.font.montserrat));
            rg_cards.addView(radioButton);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(CardBlockingActivity.this);
        presenter.loadCardsArrayList(CardBlockingActivity.this);
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
