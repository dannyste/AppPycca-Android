package com.pycca.pycca.home;

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

public class HomeFragmentPresenter implements HomeFragmentMVP.Presenter {

    @Nullable
    private HomeFragmentMVP.View view;
    private HomeFragmentMVP.Model model;

    public HomeFragmentPresenter (HomeFragmentMVP.Model model){
        this.model = model;
    }

    @Override
    public void setView(HomeFragmentMVP.View view) {
        this.view = view;
    }

    @Override
    public void loadPromotions(final View view1) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
        Call<BaseResponse> getListResponseCall = endpointsApi.getPromotionsList();

        getListResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.isSuccessful()){
                    BaseResponse baseResponse = response.body();
                    if(baseResponse.getStatus()){
                        view.setDataToBanner(model.castPromotionList(baseResponse.getData()));
                    }else {
                        Util.showMessage(view1, view.getAppContext().getResources().getString(R.string.error_load_images));
                    }
                }else {
                    Util.showMessage(view1, view.getAppContext().getResources().getString(R.string.error_load_images));
                }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Util.showMessage(view1, view.getAppContext().getResources().getString(R.string.error_load_images));

            }
        });


    }

    @Override
    public void loadDivisions(final View view1) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.setConnectionRestApiServer();
        Call<BaseResponse> getListResponseCall = endpointsApi.getDivisionsList();

        getListResponseCall.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.isSuccessful()){
                    BaseResponse baseResponse = response.body();
                    if(baseResponse.getStatus()){
                        view.updateDataRecyclerView(model.castDivisionList((baseResponse.getData())));
                    }else {
                        Util.showMessage(view1, view.getAppContext().getResources().getString(R.string.error_load_images));
                    }
                }else {
                    Util.showMessage(view1, view.getAppContext().getResources().getString(R.string.error_load_images));
                }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Util.showMessage(view1, view.getAppContext().getResources().getString(R.string.error_load_images));

            }
        });
    }
}
