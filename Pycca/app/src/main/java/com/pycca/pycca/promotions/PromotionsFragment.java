package com.pycca.pycca.promotions;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.pycca.pycca.R;
import com.pycca.pycca.coupon.CouponFragment;
import com.pycca.pycca.promotion.PromotionFragment;

import java.util.ArrayList;

public class PromotionsFragment extends Fragment {

    private Toolbar toolbar;
    private TabLayout tab_layout;
    private ViewPager view_pager;

    public PromotionsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_promotions, container, false);
        toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        tab_layout =  view.findViewById(R.id.tab_layout);
        view_pager =  view.findViewById(R.id.view_pager);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new PromotionFragment());
        fragments.add(new CouponFragment());
        view_pager.setAdapter(new PagerAdapter(getFragmentManager(), fragments));
        tab_layout.setupWithViewPager(view_pager);
        tab_layout.getTabAt(0).setIcon(R.drawable.tab_layout_icon_promotion);
        tab_layout.getTabAt(1).setIcon(R.drawable.tab_layout_icon_coupon);
        return view;
    }

}
