package com.pycca.pycca.coupon;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.pycca.pycca.R;
import com.pycca.pycca.picture.PictureActivity;
import com.pycca.pycca.pojo.CouponImageResource;
import com.pycca.pycca.pojo.ImageResource;
import com.pycca.pycca.root.App;
import com.pycca.pycca.util.Util;

import java.util.ArrayList;

import javax.inject.Inject;

public class CouponFragment extends Fragment implements CouponFragmentMVP.View {

    @Inject
    public CouponFragmentMVP.Presenter presenter;

    private RecyclerView rvCoupon;
    private ArrayList<CouponImageResource> coupons;
    private CouponFragmentAdapter couponFragmentAdapter;
    private LinearLayout ll_root_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);
        ((App) getActivity().getApplication()).getApplicationComponent().inject(CouponFragment.this);
        rvCoupon    = view.findViewById(R.id.rv_coupon);
        ll_root_view = view.findViewById(R.id.ll_root_view);
        initRecyclerView();
        return view;
    }

    @Override
    public void updateDataRecyclerView(ArrayList<CouponImageResource> coupons) {
        this.coupons.clear();
        this.coupons.addAll(coupons);
        couponFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public Context getAppContext() {
        return getActivity().getApplicationContext();
    }

    private void initRecyclerView(){
        coupons = new ArrayList<>();
        couponFragmentAdapter = new CouponFragmentAdapter(this, coupons);

        rvCoupon.setAdapter(couponFragmentAdapter);
        rvCoupon.setItemAnimator(new DefaultItemAnimator());
        LinearLayoutManager llm = new LinearLayoutManager(getAppContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvCoupon.setLayoutManager(llm);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(CouponFragment.this);
        presenter.loadCoupons();
    }

    @Override
    public void onStop() {
        super.onStop();
        coupons.clear();
        couponFragmentAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMessage(int errorCode) {
        Util.showMessage(ll_root_view, getResources().getString(errorCode));
    }

    @Override
    public void goToPictureActivity(CouponImageResource coupon, View view) {
        Intent intent = new Intent(getActivity(), PictureActivity.class);
        intent.putExtra("coupon", coupon);
        ActivityOptions activityOptions =
                ActivityOptions.makeSceneTransitionAnimation(getActivity(), view, "img");
        startActivity(intent, activityOptions.toBundle());

    }
}
