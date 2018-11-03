package com.pycca.pycca.host;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.pycca.pycca.R;
import com.pycca.pycca.buy.BuyFragment;
import com.pycca.pycca.clubpycca.ClubPyccaFragment;
import com.pycca.pycca.coupon.CouponFragment;
import com.pycca.pycca.home.HomeFragment;
import com.pycca.pycca.more.MoreFragment;

import java.util.HashMap;

public class HostActivity extends AppCompatActivity {

    public static Activity activity;

    private BottomNavigationView bottomNavigationView;

    private HashMap<Integer, Fragment> fragments;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            item.setChecked(true);
            setItemIcon();
            selectFragment(item.getItemId());
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        activity = HostActivity.this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        addFragments();
        setItemIcon();
        selectFragment(R.id.mi_home);
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

    private void selectFragment(int menuItem) {
        if (menuItem == R.id.mi_club_pycca) {
            AlertDialog.Builder builder = new AlertDialog.Builder(HostActivity.this);
            builder.setView(getLayoutInflater().inflate(R.layout.alert_dialog_club_pycca_partner, null));
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return;
        }
        Fragment fragment = fragments.get(menuItem);
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            //fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
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

}
