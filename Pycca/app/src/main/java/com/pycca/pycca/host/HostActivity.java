package com.pycca.pycca.host;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.pycca.pycca.R;
import com.pycca.pycca.clubpycca.ClubPyccaFragment;
import com.pycca.pycca.home.HomeFragment;
import com.pycca.pycca.more.MoreFragment;
import com.pycca.pycca.promotions.PromotionsFragment;

import java.util.HashMap;

public class HostActivity extends AppCompatActivity {

    private HashMap<Integer, Fragment> fragments;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            item.setChecked(true);
            selectFragment(item.getItemId());
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        addFragments();
        selectFragment(R.id.mi_home);
    }

    @SuppressLint("UseSparseArrays")
    private void addFragments(){
        fragments = new HashMap<>();
        fragments.put(R.id.mi_home, new HomeFragment());
        fragments.put(R.id.mi_promotions, new PromotionsFragment());
        fragments.put(R.id.mi_club_pycca, new ClubPyccaFragment());
        fragments.put(R.id.mi_more, new MoreFragment());
    }

    private void selectFragment(int menuItem){
        Fragment fragment = fragments.get(menuItem);
        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container, fragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        }
    }

}
