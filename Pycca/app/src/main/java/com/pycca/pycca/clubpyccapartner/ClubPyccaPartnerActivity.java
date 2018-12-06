package com.pycca.pycca.clubpyccapartner;

import android.animation.Animator;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import java.util.Calendar;

import javax.inject.Inject;

public class ClubPyccaPartnerActivity extends AppCompatActivity implements ClubPyccaPartnerActivityMVP.View {

    private static final String TAG = ClubPyccaPartnerActivity.class.getName();

    @Inject
    public ClubPyccaPartnerActivityMVP.Presenter presenter;

    private TextInputEditText tiet_name, tiet_last_name, tiet_born_date, tiet_identification_card, tiet_email, tiet_phone_number, tiet_cellphone_number, tiet_address;
    private Button bt_request_card, bt_accept;
    private LinearLayout ll_root_view, ll_form_view, ll_sended_request, ll_loading, ll_done, ll_error;
    private LottieAnimationView lav_loading, lav_done, lav_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_pycca_partner);
        ((App) getApplication()).getApplicationComponent().inject(ClubPyccaPartnerActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tiet_name = findViewById(R.id.tiet_name);
        tiet_last_name = findViewById(R.id.tiet_last_name);
        tiet_born_date = findViewById(R.id.tiet_born_date);
        tiet_identification_card = findViewById(R.id.tiet_identification_card);
        tiet_email = findViewById(R.id.tiet_email);
        tiet_phone_number = findViewById(R.id.tiet_phone_number);
        tiet_cellphone_number = findViewById(R.id.tiet_cellphone_number);
        tiet_address = findViewById(R.id.tiet_address);
        bt_request_card = findViewById(R.id.bt_request_card);
        bt_accept = findViewById(R.id.bt_accept);
        ll_root_view = findViewById(R.id.ll_root_view);
        ll_form_view = findViewById(R.id.ll_form_view);
        ll_sended_request = findViewById(R.id.ll_sended_request);
        ll_loading = findViewById(R.id.ll_loading);
        ll_done = findViewById(R.id.ll_done);
        ll_error = findViewById(R.id.ll_error);
        lav_loading = findViewById(R.id.lav_loading);
        lav_done = findViewById(R.id.lav_done);
        lav_error = findViewById(R.id.lav_error);

        tiet_born_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        bt_request_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.sendRequestClubPyccaPartner();
            }
        });

        bt_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToHostActivity();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(ClubPyccaPartnerActivity.this);
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
        ll_form_view.setVisibility(View.GONE);
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
    public void showViewFormSended() {
        ll_done.setVisibility(View.GONE);
        ll_form_view.setVisibility(View.GONE);
        ll_sended_request.setVisibility(View.VISIBLE);
    }

    @Override
    public void showViewForm() {
        ll_error.setVisibility(View.GONE);
        ll_sended_request.setVisibility(View.GONE);
        ll_form_view.setVisibility(View.VISIBLE);

    }

    @Override
    public String getName() {
        return tiet_name.getText().toString().trim();
    }

    @Override
    public String getLastName() {
        return tiet_last_name.getText().toString().trim();
    }

    @Override
    public String getBornDate() {
        return tiet_born_date.getText().toString().trim();
    }

    @Override
    public String getIdentification() {
        return tiet_identification_card.getText().toString().trim();
    }

    @Override
    public String getEmail() {
        return tiet_email.getText().toString().trim();
    }

    @Override
    public String getPhoneNumber() {
        return tiet_phone_number.getText().toString().trim();
    }

    @Override
    public String getCellPhoneNumber() {
        return tiet_cellphone_number.getText().toString().trim();
    }

    @Override
    public String getAddress() {
        return tiet_address.getText().toString().trim();
    }

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero
                final String selectedDate = year + "-" + Util.twoDigits(month+1) + "-" + Util.twoDigits(day) ;
                tiet_born_date.setText(selectedDate);
            }
        });
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    public static class DatePickerFragment extends DialogFragment {

        private DatePickerDialog.OnDateSetListener listener;

        public static DatePickerFragment newInstance(DatePickerDialog.OnDateSetListener listener) {
            DatePickerFragment fragment = new DatePickerFragment();
            fragment.setListener(listener);
            return fragment;
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        @Override
        @NonNull
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            Dialog dialog = new DatePickerDialog(getActivity(), DatePickerDialog.THEME_DEVICE_DEFAULT_LIGHT ,listener, year, month, day);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            return dialog;
        }

    }
}
