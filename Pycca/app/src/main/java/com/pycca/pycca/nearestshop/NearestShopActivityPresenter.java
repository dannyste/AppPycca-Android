package com.pycca.pycca.nearestshop;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.OurShopDetail;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearestShopActivityPresenter implements NearestShopActivityMVP.Presenter, NearestShopActivityMVP.TaskListener {

    private NearestShopActivityMVP.View view;
    private NearestShopActivityMVP.Model model;

    NearestShopActivityPresenter(NearestShopActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(NearestShopActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadOurShopsDetailsArrayList() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
        Call<BaseResponse> getOurShopsCall = endpointsApi.getOurShops();
        getOurShopsCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        BaseResponse baseResponse = response.body();
                        if (baseResponse.getStatus()) {
                            if (baseResponse.getData().getStatus_error().getCo_error() == 0) {
                                ArrayList<OurShopDetail> ourShopDetailArrayList = model.getOurShopsDetailsArrayList(baseResponse);
                                view.showMarkersGoogleMap(ourShopDetailArrayList);
                            }
                            else {
                                onError(R.string.error_default);
                            }
                        }
                        else {
                            onError(R.string.error_default);
                        }
                    }
                }
                catch (Exception exception) {
                    onError(R.string.error_default);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable throwable) {
                onError(R.string.error_default);
            }
        });
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(int error) {
        view.showErrorMessage(R.string.error_default);
    }

}
