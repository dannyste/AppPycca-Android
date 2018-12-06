package com.pycca.pycca.cardlocking;

import android.animation.Animator;
import android.content.DialogInterface;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
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

public class CardLockingActivity extends AppCompatActivity implements CardLockingActivityMVP.View {

    private static final String TAG = CardLockingActivity.class.getName();

    @Inject
    public CardLockingActivityMVP.Presenter presenter;

    private LinearLayout ll_root_view, ll_confirmation_message, ll_loading, ll_done, ll_failure, ll_error;
    private RadioGroup rg_cards;
    private RadioButton rb_principal_card;
    private TextView tv_additional_cards, tv_confirmation_message, tv_error_touch_retry;
    private TextInputLayout til_reason;
    private TextInputEditText tiet_reason;
    private Button btn_lock;
    private LottieAnimationView lav_loading, lav_done, lav_failure, lav_error;

    private String clubPyccaCardNumber;
    private String clubPyccaCardName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_locking);
        ((App) getApplication()).getApplicationComponent().inject(CardLockingActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        findViewById(R.id.tv_additional_cards).setVisibility(View.VISIBLE);
        ll_root_view            = findViewById(R.id.ll_root_view);
        rg_cards                = findViewById(R.id.rg_cards);
        rb_principal_card       = findViewById(R.id.rb_principal_card);
        tv_additional_cards     = findViewById(R.id.tv_additional_cards);
        til_reason              = findViewById(R.id.til_reason);
        tiet_reason             = findViewById(R.id.tiet_reason);
        btn_lock                = findViewById(R.id.btn_lock);
        ll_confirmation_message = findViewById(R.id.ll_confirmation_message);
        tv_confirmation_message = findViewById(R.id.tv_confirmation_message);
        ll_loading              = findViewById(R.id.ll_loading);
        lav_loading             = findViewById(R.id.lav_loading);
        ll_done                 = findViewById(R.id.ll_done);
        lav_done                = findViewById(R.id.lav_done);
        ll_failure              = findViewById(R.id.ll_failure);
        lav_failure             = findViewById(R.id.lav_failure);
        ll_error                = findViewById(R.id.ll_error);
        lav_error               = findViewById(R.id.lav_error);
        tv_error_touch_retry    = findViewById(R.id.tv_error_touch_retry);
        tiet_reason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.reasonClicked();
            }
        });
        btn_lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.lockClicked();
            }
        });
        tv_error_touch_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.errorTouchRetryClicked(CardLockingActivity.this);
            }
        });
    }

    @Override
    public String getClubPyccaCardNumber() {
        return clubPyccaCardNumber;
    }

    @Override
    public String getReason() {
        return tiet_reason.getText().toString();
    }

    @Override
    public void setReason(String reason) {
        tiet_reason.setText(reason);
    }

    @Override
    public void showReasonRequired() {
        til_reason.startAnimation(Util.getTranslateAnimation());
        Util.showMessage(ll_root_view, getString(R.string.reason_required));
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
    public void showConfirmationMessage() {
        ll_confirmation_message.setVisibility(View.VISIBLE);
        tv_confirmation_message.setText("De acuerdo a su solicitud, su Tarjeta ClubPycca " + clubPyccaCardName + " fue bloqueada.\nLe pedimos visitar nuestras tiendas o comunicarse con nuestro Servicio de Atención Telefónico 1800-1PYCCA (179222) para coordinar el envío de su nueva Tarjeta ClubPycca");
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
    public void updateTextPrincipalCardRadioButton(ArrayList<Card> cardArrayList) {
        final Card card = cardArrayList.get(0);
        clubPyccaCardNumber = card.getClubPyccaCardNumber();
        clubPyccaCardName = card.getClubPyccaCardName();
        rb_principal_card.setText(card.getClubPyccaCardName());
        rb_principal_card.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    clubPyccaCardNumber = card.getClubPyccaCardNumber();
                    clubPyccaCardName = card.getClubPyccaCardName();
                }
            }
        });
    }

    @Override
    public void showAdditionalCardsTextView() {
        tv_additional_cards.setVisibility(View.VISIBLE);
    }

    @Override
    public void addAdditionalCardsRadioButton(final ArrayList<Card> cardArrayList) {
        ViewGroup.LayoutParams layoutParams = rb_principal_card.getLayoutParams();
        for (int i = 1; i < cardArrayList.size(); i++) {
            final Card card = cardArrayList.get(i);
            RadioButton radioButton = new RadioButton(CardLockingActivity.this);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setText(card.getClubPyccaCardName());
            radioButton.setTextColor(getResources().getColor(R.color.colorBlack));
            radioButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.text_medium));
            radioButton.setTypeface(ResourcesCompat.getFont(CardLockingActivity.this, R.font.montserrat));
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        clubPyccaCardNumber = card.getClubPyccaCardNumber();
                        clubPyccaCardName = card.getClubPyccaCardName();
                    }
                }
            });
            rg_cards.addView(radioButton);
        }
    }

    @Override
    public void showAlertDialogReason() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CardLockingActivity.this);
        builder.setTitle(R.string.reason)
                .setItems(R.array.reason_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int position) {
                        dialog.dismiss();
                        switch (position) {
                            case 0:
                                presenter.reasonItemClicked(getString(R.string.lost));
                                break;
                            case 1:
                                presenter.reasonItemClicked(getString(R.string.stole));
                                break;
                        }
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(true);
        alertDialog.show();
    }

    @Override
    public void showAlertDialogLock() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CardLockingActivity.this);
        builder.setMessage("¿" + getString(R.string.sure_lock_card) + " " + clubPyccaCardName + "?")
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        presenter.lockPositiveButtonClicked(CardLockingActivity.this);
                    }
                })
                .setNegativeButton(R.string.not, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        positiveButton.setTextColor(getResources().getColor(R.color.colorPrimary));
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        negativeButton.setTextColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(CardLockingActivity.this);
        presenter.loadCardsArrayList(CardLockingActivity.this);
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
