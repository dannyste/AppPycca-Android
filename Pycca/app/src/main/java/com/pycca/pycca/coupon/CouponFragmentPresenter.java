package com.pycca.pycca.coupon;

import android.support.annotation.Nullable;
import android.view.View;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.CouponImageResource;
import com.pycca.pycca.pojo.ImageResource;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.util.Util;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CouponFragmentPresenter implements CouponFragmentMVP.Presenter, CouponFragmentMVP.TaskListener {

    @Nullable
    private CouponFragmentMVP.View view;
    private CouponFragmentMVP.Model model;

    public CouponFragmentPresenter (CouponFragmentMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(CouponFragmentMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadCoupons() {
        model.getContentImages(this);
    }

    @Override
    public void onSuccess(ArrayList<CouponImageResource> list) {
        view.updateDataRecyclerView(list);
    }
}
