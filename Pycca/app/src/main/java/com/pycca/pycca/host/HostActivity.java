package com.pycca.pycca.host;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.pycca.pycca.R;
import com.pycca.pycca.clubpycca.ClubPyccaFragment;
import com.pycca.pycca.home.HomeFragment;

import java.util.HashMap;

public class HostActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private HashMap<Integer, Fragment> fragments;
    private int menuItem;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            menuItem = item.getItemId();
            selectFragment();
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        fragments = new HashMap<>();
        addFragments();
        menuItem = R.id.mi_home;
        selectFragment();
    }

    private HashMap<Integer, Fragment> addFragments(){
        fragments.put(R.id.mi_home, new HomeFragment());
        fragments.put(R.id.mi_club_pycca, new ClubPyccaFragment());
        return fragments;
    }

    private void selectFragment(){
        Fragment fragment = fragments.get(menuItem);
        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

}
