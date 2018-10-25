package com.pycca.pycca.coupon;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.Coupon;
import com.pycca.pycca.root.App;

import java.util.ArrayList;

import javax.inject.Inject;

public class CouponFragment extends Fragment implements CouponFragmentMVP.View {

    @Inject
    public CouponFragmentMVP.Presenter presenter;

    private RecyclerView rvCoupon;
    private ArrayList<Coupon> coupons;
    private CouponFragmentAdapter couponFragmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coupon, container, false);
        ((App) getActivity().getApplication()).getApplicationComponent().inject(CouponFragment.this);
        rvCoupon    = view.findViewById(R.id.rv_coupon);
        initRecyclerView();
        return view;
    }

    @Override
    public void updateDataRecyclerView(ArrayList<Coupon> coupons) {
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
        couponFragmentAdapter = new CouponFragmentAdapter(getActivity(), coupons);

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
        presenter.loadCoupons(rvCoupon);
    }

    @Override
    public void onStop() {
        super.onStop();
        coupons.clear();
        couponFragmentAdapter.notifyDataSetChanged();
    }
}
