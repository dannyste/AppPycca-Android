package com.pycca.pycca.coupon;

import android.support.annotation.Nullable;
import android.view.View;

import com.pycca.pycca.R;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;
import com.pycca.pycca.util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CouponFragmentPresenter implements CouponFragmentMVP.Presenter {

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
    public void loadCoupons(final View view1) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
        Call<BaseResponse> getListResponseCall = endpointsApi.getCouponsList();

        getListResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.isSuccessful()){
                    BaseResponse baseResponse = response.body();
                    if(baseResponse.getStatus()){
                        view.updateDataRecyclerView(model.castCouponList(baseResponse.getData()));
                    }else {
                        Util.showMessage(view1, view.getAppContext().getResources().getString(R.string.error_message_load_images));
                    }
                }else {
                    Util.showMessage(view1, view.getAppContext().getResources().getString(R.string.error_message_load_images));
                }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Util.showMessage(view1, view.getAppContext().getResources().getString(R.string.error_message_load_images));

            }
        });
    }
}
