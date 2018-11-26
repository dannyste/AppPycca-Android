package com.pycca.pycca.host;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.pycca.pycca.R;
import com.pycca.pycca.buy.BuyFragment;
import com.pycca.pycca.clubpycca.ClubPyccaFragment;
import com.pycca.pycca.clubpyccapartner.ClubPyccaPartnerActivity;
import com.pycca.pycca.coupon.CouponFragment;
import com.pycca.pycca.home.HomeFragment;
import com.pycca.pycca.more.MoreFragment;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import java.util.HashMap;

import javax.inject.Inject;

public class HostActivity extends AppCompatActivity implements HostActivityMVP.View {

    private static final String TAG = HostActivity.class.getName();

    @Inject
    public HostActivityMVP.Presenter presenter;

    public static Activity activity;

    private BottomNavigationView bottomNavigationView;

    private HashMap<Integer, Fragment> fragments;

    private View viewAlertDialog;
    private AlertDialog alertDialog;

    private LinearLayout ll_root_view, ll_loading, ll_done;
    private TextInputLayout til_identification_card, til_club_pycca_card_number;
    private TextInputEditText tiet_identification_card, tiet_club_pycca_card_number;
    private LottieAnimationView lav_loading, lav_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        ((App) getApplication()).getApplicationComponent().inject(HostActivity.this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        activity = HostActivity.this;
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                presenter.navigationItemSelected(HostActivity.this, menuItem);
                return false;
            }
        });
        addFragments();
        showFragment(bottomNavigationView.getMenu().getItem(0));
    }

    @SuppressLint("UseSparseArrays")
    private void addFragments(){
        fragments = new HashMap<>();
        fragments.put(R.id.mi_home, new HomeFragment());
        fragments.put(R.id.mi_coupon, new CouponFragment());
        fragments.put(R.id.mi_club_pycca, new ClubPyccaFragment());
        fragments.put(R.id.mi_buy, new BuyFragment());
        fragments.put(R.id.mi_more, new MoreFragment());
    }

    private void setItemIcon() {
        Menu menu = bottomNavigationView.getMenu();
        for (int i = 0; i < menu.size(); i++) {
            switch (menu.getItem(i).getItemId()) {
                case R.id.mi_home:
                    if (menu.getItem(i).isChecked()) {
                        menu.getItem(i).setIcon(R.drawable.ic_home_checked);
                    }
                    else {
                        menu.getItem(i).setIcon(R.drawable.ic_home);
                    }
                    break;
                case R.id.mi_coupon:
                    if (menu.getItem(i).isChecked()) {
                        menu.getItem(i).setIcon(R.drawable.ic_coupon_checked);
                    }
                    else {
                        menu.getItem(i).setIcon(R.drawable.ic_coupon);
                    }
                    break;
                case R.id.mi_club_pycca:
                    if (menu.getItem(i).isChecked()) {
                        menu.getItem(i).setIcon(R.drawable.ic_club_pycca_checked);
                    }
                    else {
                        menu.getItem(i).setIcon(R.drawable.ic_club_pycca);
                    }
                    break;
                case R.id.mi_buy:
                    if (menu.getItem(i).isChecked()) {
                        menu.getItem(i).setIcon(R.drawable.ic_buy_checked);
                    }
                    else {
                        menu.getItem(i).setIcon(R.drawable.ic_buy);
                    }
                    break;
                case R.id.mi_more:
                    if (menu.getItem(i).isChecked()) {
                        menu.getItem(i).setIcon(R.drawable.ic_more_checked);
                    }
                    else {
                        menu.getItem(i).setIcon(R.drawable.ic_more);
                    }
                    break;
            }
        }
    }

    @Override
    public void showFragment(MenuItem menuItem) {
        menuItem.setChecked(true);
        setItemIcon();
        Fragment fragment = fragments.get(menuItem.getItemId());
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fl_container, fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void showAlertDialogClubPyccaPartner() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HostActivity.this);
        viewAlertDialog = getLayoutInflater().inflate(R.layout.alert_dialog_club_pycca_partner, null);
        builder.setView(viewAlertDialog);
        alertDialog                 = builder.create();
        ll_root_view                = viewAlertDialog.findViewById(R.id.ll_root_view);
        ImageView iv_close          = viewAlertDialog.findViewById(R.id.iv_close);
        til_identification_card     = viewAlertDialog.findViewById(R.id.til_identification_card);
        tiet_identification_card    = viewAlertDialog.findViewById(R.id.tiet_identification_card);
        til_club_pycca_card_number  = viewAlertDialog.findViewById(R.id.til_club_pycca_card_number);
        tiet_club_pycca_card_number = viewAlertDialog.findViewById(R.id.tiet_club_pycca_card_number);
        Button btn_validate         = viewAlertDialog.findViewById(R.id.btn_validate);
        TextView tv_request_now     = viewAlertDialog.findViewById(R.id.tv_request_now);
        ll_loading                  = viewAlertDialog.findViewById(R.id.ll_loading);
        lav_loading                 = viewAlertDialog.findViewById(R.id.lav_loading);
        ll_done                     = viewAlertDialog.findViewById(R.id.ll_done);
        lav_done                    = viewAlertDialog.findViewById(R.id.lav_done);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        btn_validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.validateClicked(HostActivity.this);
            }
        });
        tv_request_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.requestNowClicked();
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    @Override
    public String getIdentificationCard() {
        return tiet_identification_card.getText().toString().trim();
    }

    @Override
    public String getClubPyccaCardNumber() {
        return tiet_club_pycca_card_number.getText().toString().trim();
    }

    @Override
    public void showIdentificationCardRequired() {
        til_identification_card.startAnimation(Util.getTranslateAnimation());
        Util.showMessage(viewAlertDialog, getString(R.string.identification_card_required));
    }

    @Override
    public void clubPyccaCardNumberRequired() {
        til_club_pycca_card_number.startAnimation(Util.getTranslateAnimation());
        Util.showMessage(viewAlertDialog, getString(R.string.club_pycca_card_number_required));
    }

    @Override
    public void showLoadingAnimation() {
        ll_root_view.setVisibility(View.INVISIBLE);
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
    public void showDoneAnimation() {
        lav_loading.pauseAnimation();
        ll_loading.setVisibility(View.GONE);
        ll_done.setVisibility(View.VISIBLE);
        lav_done.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                presenter.finishedDoneAnimation(bottomNavigationView.getMenu().getItem(2));
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
    public void hideAlertDialogClubPyccaPartner() {
        alertDialog.dismiss();
    }

    @Override
    public void goToClubPyccaPartnerActivity() {
        Intent clubPyccaPartnerActivity = new Intent(HostActivity.this, ClubPyccaPartnerActivity.class);
        startActivity(clubPyccaPartnerActivity);
    }

    @Override
    public void showErrorMessage(int error) {
        Util.showMessage(viewAlertDialog, getString(error));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(HostActivity.this);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}
