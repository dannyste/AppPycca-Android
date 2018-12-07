package com.pycca.pycca.ourshop;

import com.pycca.pycca.R;
import com.pycca.pycca.pojo.OurShop;
import com.pycca.pycca.pojo.OurShopDetail;
import com.pycca.pycca.restApi.EndpointsApi;
import com.pycca.pycca.restApi.RestApiAdapter;
import com.pycca.pycca.restApi.model.BaseResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OurShopActivityPresenter implements OurShopActivityMVP.Presenter, OurShopActivityMVP.TaskListener {

    private OurShopActivityMVP.View view;
    private OurShopActivityMVP.Model model;

    private Call<BaseResponse> getOurShopsCall;

    OurShopActivityPresenter(OurShopActivityMVP.Model model) {
        this.model  = model;
    }

    @Override
    public void setView(OurShopActivityMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadOurShopsArrayList() {
        view.hideRootView();
        view.showLoadingAnimation();
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
        getOurShopsCall = endpointsApi.getOurShops();
        getOurShopsCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        BaseResponse baseResponse = response.body();
                        if (baseResponse.getStatus()) {
                            if (baseResponse.getData().getStatus_error().getCo_error() == 0) {
                                ArrayList<OurShop> ourShopArrayList = model.getOurShopsArrayList(baseResponse);
                                view.hideLoadingAnimation();
                                view.showRootView();
                                view.updateDataRecyclerView(ourShopArrayList);
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
                if (!getOurShopsCall.isCanceled()) {
                    onError(R.string.error_default);
                }
            }
        });
    }

    @Override
    public void goNearestShopClicked() {
        view.goToNearestShopActivity();
    }

    @Override
    public void itemClicked(ArrayList<OurShopDetail> ourShopDetailArrayList) {
        view.goToOurShopsDetailsActivity(ourShopDetailArrayList);
    }

    @Override
    public void errorTouchRetryClicked() {
        view.hideErrorAnimation();
        loadOurShopsArrayList();
    }

    @Override
    public void cancelServiceCall() {
        if (getOurShopsCall != null) {
            getOurShopsCall.cancel();
        }
    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onError(int error) {
        view.hideRootView();
        view.hideLoadingAnimation();
        view.showErrorAnimation();
    }

}
